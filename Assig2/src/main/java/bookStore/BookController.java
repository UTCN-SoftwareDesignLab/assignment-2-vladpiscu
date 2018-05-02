package bookStore;

import bookStore.Mapper.BookMapper;
import bookStore.dto.BookDto;
import bookStore.entity.Book;
import bookStore.service.BookService;
import bookStore.service.GenreService;
import bookStore.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import bookStore.service.AuthorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BookController {
    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;
    @Autowired
    GenreService genreService;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    ReportService reportService;

    @RequestMapping("/crud-books")
    String home(Model model) {
        addAttributes(model);
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("newBook", new BookDto());
        return "book-form";
    }

    @RequestMapping("/operations-books")
    String operationsBooks(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("allBooks", bookService.getAll());
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("errors", new ArrayList<String>());
        return "book-operations";
    }

    @RequestMapping(value = "/operBook", params="action=Search by title", method = RequestMethod.POST)
    public String searchByTitle(@RequestParam Map<String, String> searchWord, Model model){
        model.addAttribute("books", bookService.findByTitle(searchWord.get("searchWord")));
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("errors", new ArrayList<String>());
        model.addAttribute("allBooks", bookService.getAll());
        return "book-operations";
    }

    @RequestMapping(value = "/operBook", params="action=Search by author", method = RequestMethod.POST)
    public String searchByAuthor(@RequestParam Map<String, String> searchWord, Model model){
        model.addAttribute("books",bookService.findByAuthor(searchWord.get("searchWord")));
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("errors", new ArrayList<String>());
        model.addAttribute("allBooks", bookService.getAll());
        return "book-operations";
    }

    @RequestMapping(value = "/operBook", params="action=Search by genre", method = RequestMethod.POST)
    public String searchByGenre(@RequestParam Map<String, String> searchWord, Model model){
        model.addAttribute("books", bookService.findByGenre(searchWord.get("searchWord")));
        model.addAttribute("allBooks", bookService.getAll());
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("errors", new ArrayList<String>());
        return "book-operations";
    }

    @RequestMapping(value = "/sellBook", method = RequestMethod.POST)
    public String sellBook(@RequestParam Map<String, String> information, Model model){
        String quantityString = information.get("quantity");
        if(quantityString.equals(""))
            quantityString = "0";
        Double quantity = Double.parseDouble(quantityString);
        if(!bookService.sellBook(bookService.findById(Integer.parseInt(information.get("id"))), quantity.intValue())) {
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Not enough books!");
            model.addAttribute("errors", errors);
        }
        else{
            model.addAttribute("errors", new ArrayList<String>());
        }
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("books", bookService.findById(Integer.parseInt(information.get("id"))));
        model.addAttribute("allBooks", bookService.getAll());
        return "book-operations";
    }


    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public String create(@ModelAttribute("newBook") @Valid BookDto bookDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            addAttributes(model);
            model.addAttribute("bookDto", new BookDto());
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "book-form";
        }
        bookService.create(bookMapper.toBook(bookDto));
        return "redirect:/crud-books";
    }

    @RequestMapping(value = "/manipulateBook", params="action=update", method = RequestMethod.POST)
    public String update(@ModelAttribute("bookDto") @Valid BookDto selectedBook, BindingResult bindingResult, Model model){
        if(selectedBook.getId() == 0){
            bindingResult.addError(new ObjectError("bookDto", "You can't update a book that doesn't exist."));
        }
        if(bindingResult.hasErrors()) {
            addAttributes(model);
            model.addAttribute("newBook", new BookDto());
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "book-form";
        }
        Book book = bookMapper.toBook(selectedBook);
        book.setId(selectedBook.getId());
        bookService.update(book);
        return "redirect:/crud-books";
    }

    @RequestMapping(value = "/manipulateBook", params="action=generate csv", method = RequestMethod.POST)
    public String generateReportCsv(){
        reportService.generateOutOfStockReport("CSV");
        return "redirect:/crud-books";
    }

    @RequestMapping(value = "/manipulateBook", params="action=generate pdf", method = RequestMethod.POST)
    public String generateReportPdf(){
        reportService.generateOutOfStockReport("PDF");
        return "redirect:/crud-books";
    }

    @RequestMapping(value = "/manipulateBook", params="action=delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute("bookDto") BookDto selectedBook, BindingResult bindingResult, Model model){
        if(selectedBook.getId() == 0){
            addAttributes(model);
            model.addAttribute("newBook", new BookDto());
            bindingResult.addError(new ObjectError("bookDto", "You can't delete a book that doesn't exist."));
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "book-form";
        }
        bookService.remove(selectedBook.getId());
        return "redirect:/crud-books";
    }

    @RequestMapping("/download/pdf/{fileName:.+}")
    public String pdfDownloader(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) {

        Path file = Paths.get("C:\\Users\\Vlad\\Documents\\BookReports\\", fileName);
        // Check if file exists
        if (Files.exists(file)) {
            // set content type
            response.setContentType("application/pdf");
            // add response header
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                //copies all bytes from a file to an output stream
                Files.copy(file, response.getOutputStream());
                //flushes output stream
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.out.println("Error :- " + e.getMessage());
            }

        } else {
            System.out.println("Sorry File not found!!!!");
        }
        return "redirect:/crud-books";
    }

    @RequestMapping("/download/csv/{fileName:.+}")
    public String cdvDownloader(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) {

        Path file = Paths.get("C:\\Users\\Vlad\\Documents\\BookReports\\", fileName);
        // Check if file exists
        if (Files.exists(file)) {
            // set content type
            response.setContentType("application/csv");
            // add response header
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                //copies all bytes from a file to an output stream
                Files.copy(file, response.getOutputStream());
                //flushes output stream
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.out.println("Error :- " + e.getMessage());
            }
        } else {
            System.out.println("Sorry File not found!!!!");
        }
        return "redirect:/crud-books";
    }

    private void addAttributes(Model model){
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("books", bookService.getAll());
    }
}
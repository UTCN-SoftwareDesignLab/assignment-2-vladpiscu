package bookStore;

import bookStore.dto.AuthorDto;
import bookStore.dto.BookDto;
import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.entity.Genre;
import bookStore.service.BookService;
import bookStore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import bookStore.service.AuthorService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
        return "book-operations";
    }

    @RequestMapping(value = "/operBook", params="action=Search by title", method = RequestMethod.POST)
    public String searchByTitle(@RequestParam Map<String, String> searchWord, Model model){
        model.addAttribute("books", bookService.findByTitle(searchWord.get("searchWord")));
        model.addAttribute("bookDto", new BookDto());
        return "book-operations";
    }

    @RequestMapping(value = "/operBook", params="action=Search by author", method = RequestMethod.POST)
    public String searchByAuthor(@RequestParam Map<String, String> searchWord, Model model){
        model.addAttribute("books",bookService.findByAuthor(searchWord.get("searchWord")));
        model.addAttribute("bookDto", new BookDto());
        return "book-operations";
    }

    @RequestMapping(value = "/operBook", params="action=Search by genre", method = RequestMethod.POST)
    public String searchByGenre(@RequestParam Map<String, String> searchWord, Model model){
        model.addAttribute("books", bookService.findByGenre(searchWord.get("searchWord")));
        model.addAttribute("bookDto", new BookDto());
        return "book-operations";
    }

    @RequestMapping(value = "/sellBook", params="action=Search by genre", method = RequestMethod.POST)
    public String sellBook(@RequestParam Map<String, Integer> searchWord, @ModelAttribute("bookDto") BookDto bookDto,  Model model){
        bookService.sellBook(bookDto, searchWord.get("quantity"));
        model.addAttribute("bookDto", new BookDto());
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
        bookService.create(bookDto);
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
        bookService.update(selectedBook);
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

    private void addAttributes(Model model){
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("books", bookService.getAll());
    }
}
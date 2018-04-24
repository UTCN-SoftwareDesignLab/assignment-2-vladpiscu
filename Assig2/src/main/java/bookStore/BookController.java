package bookStore;

import bookStore.dto.AuthorDto;
import bookStore.dto.BookDto;
import bookStore.entity.Author;
import bookStore.entity.Genre;
import bookStore.service.BookService;
import bookStore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import bookStore.service.AuthorService;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class BookController {
    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;
    @Autowired
    GenreService genreService;

    @RequestMapping("/")
    String home(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("bookDto", new BookDto());
        return "home";
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public String create(@ModelAttribute("bookDto") @Valid BookDto bookDto){
        bookService.create(bookDto);
        return "redirect:/";
    }
}
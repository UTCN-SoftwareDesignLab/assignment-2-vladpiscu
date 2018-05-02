package bookStore;

import bookStore.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @RequestMapping("/home-users")
    String userHome() {
        return "user-home";
    }

    @RequestMapping("/home-admins")
    String adminHome() {
        return "admin-home";
    }

    @RequestMapping(value = "/select-action", params="action=Book operations")
    public String operationsRedirect(){
        return "redirect:/operations-books";
    }

    @RequestMapping(value = "/admin-action", params="action=Crud books")
    public String bookCrudRedirect(){
        return "redirect:/crud-books";
    }

    @RequestMapping(value = "/admin-action", params="action=Crud users")
    public String userCrudRedirect(){
        return "redirect:/crud-users";
    }
}

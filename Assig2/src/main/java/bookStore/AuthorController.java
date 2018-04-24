package bookStore;

import bookStore.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import bookStore.service.AuthorService;

import javax.validation.Valid;

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("author", new AuthorDto());
        return "author-form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid AuthorDto authorDto) {
        authorService.create(authorDto.getName());
        return "redirect:/";
    }
}

package bookStore;

import bookStore.dto.BookDto;
import bookStore.dto.UserDto;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping("/crud-users")
    String home(Model model) {
        addAttributes(model);
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("newUser", new UserDto());
        return "user-form";
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public String create(@ModelAttribute("newUser") UserDto userDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("userDto", new UserDto());
            addAttributes(model);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "user-form";
        }
        userService.save(userDto);
        return "redirect:/crud-users";
    }

    @RequestMapping(value = "/manipulateUser", params="action=update", method = RequestMethod.POST)
    public String update(@ModelAttribute("userDto") UserDto selectedUser, BindingResult bindingResult, Model model){
        if(selectedUser.getId() == 0){
            bindingResult.addError(new ObjectError("userDto", "You can't update a user that doesn't exist."));
        }
        if(bindingResult.hasErrors()) {
            addAttributes(model);
            model.addAttribute("newUser", new UserDto());
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "user-form";
        }
        userService.update(selectedUser);
        return "redirect:/crud-users";
    }

    @RequestMapping(value = "/manipulateUser", params="action=delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute("userDto") UserDto selectedUser, BindingResult bindingResult, Model model){
        if(selectedUser.getId() == 0){
            addAttributes(model);
            model.addAttribute("newUser", new UserDto());
            bindingResult.addError(new ObjectError("userDto", "You can't delete a user that doesn't exist."));
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(r -> r.getDefaultMessage()).collect(Collectors.toList()));
            return "user-form";
        }
        userService.remove(selectedUser.getId());
        return "redirect:/crud-users";
    }

    private void addAttributes(Model model){
        model.addAttribute("users", userService.findAll());
        String[] roles = {"Administrator", "User"};
        model.addAttribute("roles", roles);
    }
}

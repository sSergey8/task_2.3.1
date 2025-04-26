package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users")
    public String listUsers(ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUserForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "new"; // Страница для создания нового юзера
    }

    @PostMapping("/users")
    public String saveUser(@RequestParam("name") String name,
                           @RequestParam("car") String car) {
        User user = new User();
        user.setName(name);
        user.setCar(car);
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit"; // Страница для редактирования
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") int id,
                             @RequestParam("name") String name,
                             @RequestParam("car") String car) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setCar(car);
            userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
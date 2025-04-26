package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @GetMapping(value = "/")
    public String print(ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
    @GetMapping(value = "/users")
    public String printUsers(ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
    @GetMapping(value = "/new")
    public String newUser(ModelMap model) {
        model.addAttribute("newUser", new User());
        return "new";
    }
    @PostMapping(value = "/users")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }
    @PatchMapping(value = "/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping(value = "/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping(value = "{/delete")
    public String isExistById(@PathVariable User user) {
        userService.delete(user.getId());
        return "redirect:/users";
    }
}
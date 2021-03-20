package com.example.sweater.controller;

import com.example.sweater.entities.Role;
import com.example.sweater.entities.User;
import com.example.sweater.repos.repos.UserRepo;
import com.example.sweater.service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ValidationService validationService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }
    @PostMapping("/admin/addNewUser")
    public String addNewUser(@RequestParam String name,
                             @RequestParam String password,
                             Model model){
        User user = new User(name, password);
        userRepo.save(user);
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }
    @GetMapping("/admin/deleteUser/{userId}")
    public String deleteUser(@PathVariable String userId,
                             Model model){
        User user;
        userId = validationService.validateId(userId);
        if(userRepo.findById(Long.valueOf(userId)).isPresent()){
            user = userRepo.findById(Long.valueOf(userId)).get();
        }else return "redirect:/user";
        userRepo.delete(user);
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }
}

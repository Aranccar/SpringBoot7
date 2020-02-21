package com.boot7.demo.controllers;

import com.boot7.demo.model.User;
import com.boot7.demo.service.RoleService;
import com.boot7.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class MainController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/list")
    public ModelAndView getList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.listUsers());
        modelAndView.addObject("roles", roleService.getRoles());
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @GetMapping("/admin/update/{id}")
    public ModelAndView getUpdate(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.getUserById(id));
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @PostMapping("/admin/update/{id}")
    public ModelAndView getUpdate2(@RequestParam(value = "role") String role, @PathVariable("id") int id, @Valid User user){
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRoles(Collections.singletonList(roleService.getRoleByName(role)));
        userService.updateUser(user);
        return new ModelAndView("redirect:/admin/list");
    }

    @PostMapping("/admin/create")
    public ModelAndView getCreate(@RequestParam(value = "role") String role, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password1){
        String password = passwordEncoder.encode(password1);
        User user = new User(username, password);
        user.setRoles(Collections.singletonList(roleService.getRoleByName(role)));
        userService.addUser(user);
        return new ModelAndView("redirect:/admin/list");
    }

    @GetMapping("/admin/delete/{id}")
    public ModelAndView getDelete(@PathVariable("id") int id){
        userService.removeUser(id);
        return new ModelAndView("redirect:/admin/list");
    }

    @GetMapping("/user")
    @ResponseBody
    public ModelAndView getUser(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByUserName(userDetails.getUsername());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user");
        return modelAndView;
    }

}

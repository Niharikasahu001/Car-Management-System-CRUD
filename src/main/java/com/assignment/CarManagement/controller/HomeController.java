package com.assignment.CarManagement.controller;

import com.assignment.CarManagement.UserDto;
import com.assignment.CarManagement.entities.User;
import com.assignment.CarManagement.helper.Message;
import com.assignment.CarManagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @GetMapping("/home")
    public String home()
    {
        return "home";
    }

    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            System.out.println("I have got an error");
            System.out.println(result);
            return "register";
        } else {
            System.out.println(userDto.getName());
            System.out.println(userDto.getEmail());
            System.out.println(userDto.getPassword());
            System.out.println(userDto);
            this.userService.saveUser(userDto);
//            return "redirect:/eyc/home";
//            return "Successfull";
            return "redirect:/signin";
        }
    }

    @PostMapping("/do_login")
    public String login(@Valid @RequestParam("email") String email, @RequestParam("password") String password,@RequestParam("role") String role, HttpSession session, Model model) {
        User user = userService.loginUser(email,password,role);
        if (user != null) {
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("logout Successfull!!", "alert-success"));
            session.setAttribute("current-user", user);
            session.setAttribute("user", user);
            Boolean loggedIn = true;
//            System.out.println(loggedIn);
//            model.addAttribute("loggedIn", loggedIn);
            if(role.equals("user"))
            {
                System.out.println("User");
                return "redirect:/view";
            }
            else
            {
                System.out.println("Admin");
                return "redirect:/admin";
            }

        } else {
            session.setAttribute("message", new Message("Invalid Email Id or Password !!", "alert-danger"));
            return "redirect:/signin";
        }

    }

    @GetMapping("/signup")
    public String signup(Model model)
    {
        model.addAttribute("title","Register -city guide for student");
        model.addAttribute("user",new User());
        System.out.println("signup");
        return "register";
    }

    @RequestMapping("/signin")
    public String signin(Model model)
    {
        model.addAttribute("title","Login -city guide for student");
        System.out.println("signin");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(Model model,HttpSession session)
    {
        session.removeAttribute("current-user");
        session.removeAttribute("user");
        System.out.println("logout");
        return "redirect:/signin";
    }

}

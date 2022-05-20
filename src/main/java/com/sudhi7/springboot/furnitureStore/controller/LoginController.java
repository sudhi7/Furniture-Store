package com.sudhi7.springboot.furnitureStore.controller;

import com.sudhi7.springboot.furnitureStore.utilities.CustomerSignIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(Model model) {
        model.addAttribute("customerSignIn", new CustomerSignIn());
        model.addAttribute("signedUp", false);
        return "customer-signin";
    }

}
/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageRedirectController {

    public HomePageRedirectController() {
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "redirect:/ui/home";
    }

}

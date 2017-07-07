package com.seereals.beidou.controller;

import com.seereals.beidou.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Administrator on 2017/7/4 0004.
 */
@Controller()
public class UserController {
    @RequestMapping("/register")
    public String Create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String Save(@ModelAttribute("form") User user, Model model) {
        model.addAttribute("user", user);
        return "detail";

    }
}

package com.fedorova.homework.Fedorova;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping("/hello")
    @ResponseBody
    public String helloWorld(){
        return "Hello, World!!!";
    }
}

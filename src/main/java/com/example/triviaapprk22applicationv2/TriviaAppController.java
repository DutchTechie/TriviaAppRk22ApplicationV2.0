package com.example.triviaapprk22applicationv2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TriviaAppController {
    @GetMapping(value = "/questions")
    public String showTriviaApp(Model model) {
        return "questions";
    }
}

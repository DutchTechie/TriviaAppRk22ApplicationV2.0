package com.example.triviaapprk22applicationv2;

import com.example.triviaapprk22applicationv2.model.triviadata.TriviaData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TriviaAppController {
    private static final String QUESTIONS_URI = "https://opentdb.com/api.php";
    private RestTemplate restTemplate;
    private TriviaAppRepository repository;
    private TriviaAppService service;

    TriviaAppController() {
        this.restTemplate = new RestTemplate();
        this.repository = new TriviaAppRepository(restTemplate);
        this.service = new TriviaAppService(repository);
        this.service.getQuestions(QUESTIONS_URI);
    }

    @GetMapping(value = "/questions")
    public String showTriviaApp(Model model) {
        return "questions";
    }
}

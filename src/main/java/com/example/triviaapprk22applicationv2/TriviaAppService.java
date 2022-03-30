package com.example.triviaapprk22applicationv2;

import com.example.triviaapprk22applicationv2.exceptions.ResourceUnavailableException;
import com.example.triviaapprk22applicationv2.model.triviadata.TriviaData;
import com.example.triviaapprk22applicationv2.model.triviadata.MultipleChoiceQuestion;
import org.springframework.stereotype.Service;

@Service
public class TriviaAppService {
    private TriviaAppRepository repository;

    public TriviaAppService(TriviaAppRepository repository) {
        this.repository = repository;
    }

    public MultipleChoiceQuestion[] getQuestions(String uri) {
        TriviaData triviaData = repository.fetch(uri);
        if (triviaData == null) {
            throw new ResourceUnavailableException("Resource 'TriviaData' not found at:\t" + uri);
        }
        return triviaData.getMultipleChoiceQuestions();
    }
}

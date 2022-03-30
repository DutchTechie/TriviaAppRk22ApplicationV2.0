package com.example.triviaapprk22applicationv2;

import com.example.triviaapprk22applicationv2.exceptions.ResourceUnavailableException;
import com.example.triviaapprk22applicationv2.model.triviadata.TriviaData;
import com.example.triviaapprk22applicationv2.model.triviadata.multiplechoicequestion.MultipleChoiceQuestion;
import com.example.triviaapprk22applicationv2.stubs.Stubs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TriviaAppRepositoryTest {
    private static final String DUMMY_URI = "";
    private RestTemplate template;
    private TriviaAppRepository repositoryUnderTest;
    private TriviaData dummyTriviaData;
    private static final int AMOUNT_OF_QUESTIONS = 3;
    private static final int NUMBER_OF_INCORRECT_ANSWERS_FOR_EACH_QUESTION = 4;

    @BeforeEach
    void setup() {
        MultipleChoiceQuestion[] dummyMultipleChoiceQuestions = Stubs.createDummyMultipleChoiceQuestions(AMOUNT_OF_QUESTIONS, NUMBER_OF_INCORRECT_ANSWERS_FOR_EACH_QUESTION);
        this.dummyTriviaData = Stubs.createDummyTriviaDataObject(dummyMultipleChoiceQuestions);
        this.template = Mockito.mock(RestTemplate.class);
        this.repositoryUnderTest = new TriviaAppRepository(template);
    }

    @Test
    void testRepositoryFetchesAnObject() {
        when(template.getForObject(DUMMY_URI, TriviaData.class)).thenReturn(dummyTriviaData);
        assertNotNull(repositoryUnderTest.fetch(DUMMY_URI));
    }
}
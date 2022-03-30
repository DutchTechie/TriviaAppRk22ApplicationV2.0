package com.example.triviaapprk22applicationv2;

import com.example.triviaapprk22applicationv2.exceptions.ResourceUnavailableException;
import com.example.triviaapprk22applicationv2.model.triviadata.TriviaData;
import com.example.triviaapprk22applicationv2.model.triviadata.multiplechoicequestion.MultipleChoiceQuestion;
import com.example.triviaapprk22applicationv2.stubs.Stubs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TriviaAppServiceTest {
    private TriviaAppService serviceUnderTest;
    private static final String DUMMY_URI = "www.dummyuri.com";
    private TriviaAppRepository repository;
    private TriviaData dummyTriviaData;
    private static final int AMOUNT_OF_QUESTIONS = 3;
    private static final int NUMBER_OF_INCORRECT_ANSWERS_FOR_EACH_QUESTION = 4;

    @BeforeEach
    void setup() {
        MultipleChoiceQuestion[] dummyMultipleChoiceQuestions = Stubs.createDummyMultipleChoiceQuestions(AMOUNT_OF_QUESTIONS, NUMBER_OF_INCORRECT_ANSWERS_FOR_EACH_QUESTION);
        this.dummyTriviaData = Stubs.createDummyTriviaDataObject(dummyMultipleChoiceQuestions);
        this.repository = Mockito.mock(TriviaAppRepository.class);
        this.serviceUnderTest = new TriviaAppService(repository);
    }

    @Test
    void testGetTriviaQuestions() {
        when(repository.fetch(DUMMY_URI)).thenReturn(dummyTriviaData);
        MultipleChoiceQuestion[] questions = serviceUnderTest.getQuestions(DUMMY_URI);
        assertEquals(questions.length, AMOUNT_OF_QUESTIONS);
    }

    @Test
    void testGetNonExistingTriviaDataByDefault() {
        String expectedExceptionMessage = "Resource 'TriviaData' not found at:\t" + DUMMY_URI;
        Exception resourceUnavailableException = assertThrows(ResourceUnavailableException.class, () ->
                serviceUnderTest.getQuestions(DUMMY_URI));
        assertEquals(expectedExceptionMessage, resourceUnavailableException.getMessage());
    }

    @Test
    void testGetNonExistingTriviaDataWhenNull() {
        when(repository.fetch(DUMMY_URI)).thenReturn(null);
        String expectedExceptionMessage = "Resource 'TriviaData' not found at:\t" + DUMMY_URI;
        Exception resourceUnavailableException = assertThrows(ResourceUnavailableException.class, () ->
                serviceUnderTest.getQuestions(DUMMY_URI));
        assertEquals(expectedExceptionMessage, resourceUnavailableException.getMessage());
    }

    @Test
    void testGetEmptyListOfQuestions() {
        dummyTriviaData.setMultipleChoiceQuestions(new MultipleChoiceQuestion[0]);
        when(repository.fetch(DUMMY_URI)).thenReturn(dummyTriviaData);
        MultipleChoiceQuestion[] multipleChoiceQuestions = serviceUnderTest.getQuestions(DUMMY_URI);
        assertEquals(multipleChoiceQuestions.length, 0);
    }
}
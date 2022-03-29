package com.example.triviaapprk22applicationv2;

import com.example.triviaapprk22applicationv2.model.triviadata.TriviaData;
import com.example.triviaapprk22applicationv2.model.triviadata.multiplechoicequestion.MultipleChoiceQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TriviaAppRepositoryTest {
    private static final String DUMMY_URI = "www.dummyuri.com";
    private RestTemplate template;
    private TriviaAppRepository repositoryUnderTest;
    private TriviaData dummyTriviaData;

    @BeforeEach
    void setup() {
        MultipleChoiceQuestion[] dummyMultipleChoiceQuestions = new MultipleChoiceQuestion[3];
        for (int i = 1; i < dummyMultipleChoiceQuestions.length + 1 ; i++) {
            dummyMultipleChoiceQuestions[i - 1] = createDummyMultipleChoiceQuestion(i);
        }
        this.dummyTriviaData = createDummyTriviaDataObject(dummyMultipleChoiceQuestions);
        this.template = Mockito.mock(RestTemplate.class);
        this.repositoryUnderTest = new TriviaAppRepository(template, TriviaData.class);
    }

    @Test
    void testRepositoryFetchesAnObject() {
        when(template.getForObject(DUMMY_URI, TriviaData.class)).thenReturn(dummyTriviaData);
        assertNotNull(repositoryUnderTest.fetch(DUMMY_URI));
    }

    private TriviaData createDummyTriviaDataObject(MultipleChoiceQuestion[] multipleChoiceQuestionsArray) {
        TriviaData dummyTriviaDataObject = new TriviaData();
        dummyTriviaDataObject.setMultipleChoiceQuestions(multipleChoiceQuestionsArray);
        return dummyTriviaDataObject;
    }

    private MultipleChoiceQuestion createDummyMultipleChoiceQuestion(int id) {
        String [] incorrectAnswers = new String[4];
        for (int i = 1 ; i < incorrectAnswers.length + 1 ; i++) {
            incorrectAnswers[i - 1] = "Incorrect answer " + i;
        }
        MultipleChoiceQuestion dummyMultipleChoiceQuestion = new MultipleChoiceQuestion();
        dummyMultipleChoiceQuestion.setQuestion("Question " + id);
        dummyMultipleChoiceQuestion.setCategory("Category " + id);
        dummyMultipleChoiceQuestion.setCorrectAnswer("Correct Answer " + id);
        dummyMultipleChoiceQuestion.setIncorrectAnswers(incorrectAnswers);
        dummyMultipleChoiceQuestion.setDifficulty("Difficulty " + id);
        return dummyMultipleChoiceQuestion;
    }
}
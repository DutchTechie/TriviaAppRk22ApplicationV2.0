package com.example.triviaapprk22applicationv2.builder;

import com.example.triviaapprk22applicationv2.model.triviadata.MultipleChoiceQuestion;
import com.example.triviaapprk22applicationv2.model.triviadata.PreparedMultipleChoiceQuestion;
import com.example.triviaapprk22applicationv2.stubs.Stubs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

class TriviaQuizBuilderTest {
    private static final int AMOUNT_OF_QUESTIONS = 3;
    private static final int NUMBER_OF_INCORRECT_ANSWERS_FOR_EACH_QUESTION = 4;
    private MultipleChoiceQuestion[] dummyMultipleChoiceQuestions;
    private TriviaQuizBuilder builderUnderTest;

    @BeforeEach
    void setup() {
        this.dummyMultipleChoiceQuestions = Stubs.createDummyMultipleChoiceQuestions(AMOUNT_OF_QUESTIONS, NUMBER_OF_INCORRECT_ANSWERS_FOR_EACH_QUESTION);
        this.builderUnderTest = new TriviaQuizBuilder(this.dummyMultipleChoiceQuestions);
    }

    @Test
    void testCanPrepareASingleMultipleChoiceQuestion() {
        // Arrange
        String expectedQuestion = "What is this type of test?";
        String correctAnswer = "Unit test";
        String [] incorrectAnswers = new String[3];
        for (int i = 1 ; i < incorrectAnswers.length + 1 ; i++) {
            incorrectAnswers[i - 1] = "Incorrect answer " + i;
        }
        MultipleChoiceQuestion dummyMultipleChoiceQuestion = new MultipleChoiceQuestion();
        dummyMultipleChoiceQuestion.setQuestion(expectedQuestion);
        dummyMultipleChoiceQuestion.setCorrectAnswer(correctAnswer);
        dummyMultipleChoiceQuestion.setIncorrectAnswers(incorrectAnswers);

        String[] expectedAllPossibleAnswers = new String[incorrectAnswers.length + 1];
        for (int i = 0 ; i < incorrectAnswers.length; i++) {
            expectedAllPossibleAnswers[i] = incorrectAnswers[i];
        }
        expectedAllPossibleAnswers[incorrectAnswers.length] = dummyMultipleChoiceQuestion.getCorrectAnswer();

        // Act
        PreparedMultipleChoiceQuestion preparedMultipleChoiceQuestion = builderUnderTest.getPreparedMultipleChoiceQuestion(dummyMultipleChoiceQuestion);
        String [] preped = preparedMultipleChoiceQuestion.getAllPossibleAnswers();
        for (int i=0; i < preped.length; i++) {
            System.out.println(preped[i]);
        }

        // Assert
        assertNotNull(preparedMultipleChoiceQuestion);
        assertEquals(expectedQuestion, preparedMultipleChoiceQuestion.getQuestion());
        assertEquals(preparedMultipleChoiceQuestion.getAllPossibleAnswers().length, incorrectAnswers.length + 1);
        assertThat(containsInAnyOrder(preparedMultipleChoiceQuestion.getAllPossibleAnswers(), incorrectAnswers[0], incorrectAnswers[1], incorrectAnswers[2], correctAnswer));
    }
}
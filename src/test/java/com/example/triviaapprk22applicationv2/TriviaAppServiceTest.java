package com.example.triviaapprk22applicationv2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// system can add correct and incorrect answers together
//system can shuffle answers
//system can evaluate submitted answers
//system should detect unanswered questions
//system should detect incorrect answers
//system should detect correct answer

class TriviaAppServiceTest {
    private TriviaAppService serviceUnderTest;

    @BeforeEach
    @Disabled
    void setup() {
        this.serviceUnderTest = new TriviaAppService();
    }

    @Test
    @Disabled
    void testGetBothCorrectAndIncorrectAnswers() {

    }
}
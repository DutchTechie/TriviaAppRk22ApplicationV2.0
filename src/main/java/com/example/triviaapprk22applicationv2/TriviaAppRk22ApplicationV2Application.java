package com.example.triviaapprk22applicationv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TriviaAppRk22ApplicationV2Application {

    public static void main(String[] args) {
        SpringApplication.run(TriviaAppRk22ApplicationV2Application.class, args);
    }

}

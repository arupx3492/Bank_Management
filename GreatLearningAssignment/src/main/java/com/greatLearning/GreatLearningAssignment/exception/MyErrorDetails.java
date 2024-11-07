package com.greatLearning.GreatLearningAssignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MyErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;


}

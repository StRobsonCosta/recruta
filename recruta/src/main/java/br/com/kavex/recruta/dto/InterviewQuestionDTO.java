package br.com.kavex.recruta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewQuestionDTO {

    private Long id;
    private String category;
    private String question;
}

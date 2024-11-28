package com.example.festival.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    private Long bno;


    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    @Size(max = 50)
    private LocalDate schedule;

    @NotBlank
    @Size(max = 50)
    private String location;

    @NotBlank
    @Size(max = 20)
    private int pay;

    @Size(max = 50)
    private String parking;

    @NotBlank
    private String detail;

    @NotBlank
    @Size(max = 255)
    private String content;

    @Size(max = 20)
    private int tel;

    private LocalDate updatedate;

    @NotBlank
    @Size(max = 255)
    private String tagtitle;


}

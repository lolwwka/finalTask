package com.example.finalproject.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Validated
public class TeamDto {
    @Pattern(regexp = "[A-Z][a-zA-Z]" + "+")
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamDto() {
    }

    public TeamDto(String name) {
        this.name = name;
    }
}

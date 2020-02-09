package com.csv.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phone;
}

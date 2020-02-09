package com.csv.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;

    @CsvBindByName(column = "first_name", required = true, capture = "^\\s*(\\w+)$")
    private String name;

    @CsvBindByName(column = "last_name", required = true, capture = "^\\s*(\\w+)$")
    private String surname;

    @CsvBindByName(column = "birth_date", required = true)
    @CsvDate("yyyy.MM.dd")
    private LocalDate dateOfBirth;

    @CsvBindByName(column = "phone_no")
    private String phone;

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phone='" + phone + '\'' +
                '}';
    }
}

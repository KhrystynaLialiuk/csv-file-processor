package com.csv.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "User.retrieveUsersByAge",
                query = "SELECT * FROM USER ORDER BY DATE_OF_BIRTH",
                resultClass = User.class
        ),

        @NamedNativeQuery(
                name = "User.retrieveTheOldestUserWithPhoneNumber",
                query = "SELECT * FROM USER WHERE PHONE IS NOT NULL ORDER BY DATE_OF_BIRTH",
                resultClass = User.class
        )
})

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "PHONE", unique = true)
    private String phone;
}

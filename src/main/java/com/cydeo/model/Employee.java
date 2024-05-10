package com.cydeo.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor //we need to create an empty object
@Data
//@Data include getter, setter, to string

public class Employee {

    // @NotNull   ---> field shouldn't be null
    // @NotEmpty  ---> field shouldn't be ""
    // @NotBlank  --->field shouldn't be "     "

    //@NotNull  --> @NotNull
    //@NotEmpty --> @NotNull + @NotEmpty
    //@NotBlank --> @NotNull + @NotEmpty + @NotBlank

    @NotBlank
    @Size(max=12, min=2)
    private String firstname;

    @NotBlank
    @Size(max=12, min=2)
    private String lastname;

    @Email
    private String email;

    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")//regular expression
    private String password;


    //thymeleaf accepts yyyy/mm/dd format, but localDate generates in mm/dd/yyyy or dd/mm/yyyy format
    //So, we need convert it to thymeleaf format
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate brithDay;

    private String address;
    private String address2;
    private String state;
    private String city;
    private String zipCode;
}

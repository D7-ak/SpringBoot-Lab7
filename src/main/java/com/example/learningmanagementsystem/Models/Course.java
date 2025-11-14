package com.example.learningmanagementsystem.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "id must be filled")
    private String id;
    @NotEmpty(message = "name must be filled")
    @Pattern(regexp = "^CP(IS|IT|CS)-[0-9]{3}$",message = "name must be CPxx-NNN, where x is the major(IS,IT,CS)and N is a number")
    private String name;
    @NotNull(message = "section must be filled")
    private int section;
    @Pattern(regexp = "^[A-Z a-z]*$",message = "Teacher name must contain only litters, no special characters and no numbers")
    private String TeacherName;// teacher name can be null, since there is an endpoint that can fill it
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "time must be filled")
    private LocalTime time;
    private boolean isOpen;
    private int seatsAvailable;

}

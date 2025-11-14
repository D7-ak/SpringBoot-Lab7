package com.example.learningmanagementsystem.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Assignment {
    @NotEmpty(message = "title must not be empty")
    @Size(min = 5,message = "title must be 5 litters at least")
    private String title;
    @NotEmpty(message = "ID must not be empty")
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadLine;
    private boolean isOpen;
    @NotEmpty
    @Pattern(regexp = "^http",message = "URL should start with http")
    private String contentURL;

}

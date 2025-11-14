package com.example.learningmanagementsystem.Controllers;

import com.example.learningmanagementsystem.ApiResponse.ApiResponse;
import com.example.learningmanagementsystem.Models.Course;
import com.example.learningmanagementsystem.service.CourseService;
import com.sun.jdi.event.StepEvent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;


    @GetMapping("/get")
    public ResponseEntity<?> getAll(){
        ArrayList<Course> all = courseService.getAll();
        if(all.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no courses yet"));
        }
        return ResponseEntity.status(200).body(all);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String message = courseService.addNew(course);
        if(!message.equals("added")){
            return ResponseEntity.status(400).body(message);
        }
        return ResponseEntity.status(200).body(new ApiResponse("course added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody @Valid Course course,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String message = courseService.update(id,course);
        if(!message.equals("updated")){
            return ResponseEntity.status(400).body(message);
        }
        return ResponseEntity.status(200).body(new ApiResponse("course added successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id){
        boolean isDeleted = courseService.delete(id);
        if(!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("course deleted successfully"));
    }

    @PutMapping("/open/{id}/{seats}")
    public ResponseEntity<?> open(@PathVariable String id, @PathVariable @Positive(message = "seats must be positive") int seats){
        boolean isOpen = courseService.openCourse(id,seats);
        if(!isOpen){
            return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("course opened successfully"));
    }

    @PutMapping("/set-name/{id}/{name}")
    public ResponseEntity<?> setTeacherName(@PathVariable String id, @PathVariable String name){
        boolean isSet = courseService.setTeacherName(id,name);
        if(!isSet){
            return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("name set successfully"));
    }

    @GetMapping("/get-open")
    public ResponseEntity<?> getOpen(){
        ArrayList<Course> open = courseService.getOpenSections();
        if(open.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no open courses was not found"));
        }
        return ResponseEntity.status(200).body(open);
    }
    @GetMapping("/get-section/{name}")
    public ResponseEntity<?> getSections(@PathVariable String name){
        ArrayList<Course> sections = courseService.getAllSections(name);
        if(sections.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no courses was not found"));
        }
        return ResponseEntity.status(200).body(sections);
    }

}

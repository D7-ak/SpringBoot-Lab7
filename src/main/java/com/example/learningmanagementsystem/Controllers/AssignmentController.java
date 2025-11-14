package com.example.learningmanagementsystem.Controllers;

import com.example.learningmanagementsystem.ApiResponse.ApiResponse;
import com.example.learningmanagementsystem.Models.Assignment;
import com.example.learningmanagementsystem.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/assignment")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll(){
        ArrayList<Assignment> all = assignmentService.getAll();
        if(all.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no assignments yet"));
        }
        return ResponseEntity.status(200).body(all);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody @Valid Assignment assignment, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        assignmentService.addNew(assignment);
        return ResponseEntity.status(200).body(new ApiResponse("assignment added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable String id,@RequestBody @Valid Assignment assignment,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = assignmentService.update(id,assignment);
        if(!isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("assignment was not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("assignment updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable String id){
        boolean isDeleted = assignmentService.delete(id);
        if(!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("assignment was not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("assignment deleted successfully"));
    }

    @GetMapping("/get-by-date/{date}")
    public ResponseEntity<?> getByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")LocalDateTime date){
        ArrayList<Assignment> byDate = assignmentService.getByDate(date);
        if(byDate.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no assignments in "+date+" was not found"));
        }
        return ResponseEntity.status(200).body(byDate);
    }

    @PutMapping("/open-assignment/{id}/{deadLine}")
    public ResponseEntity<?> openAssignment(@PathVariable String id,@PathVariable LocalDateTime deadLine){
        boolean isOpen = assignmentService.openAssignment(id,deadLine);
        if(!isOpen){
            return ResponseEntity.status(400).body(new ApiResponse("assignment was not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("assignment opened successfully"));
    }

    @PutMapping("/extend/{id}/{days}")
    public ResponseEntity<?> extend(@PathVariable String id,@PathVariable int days){
        boolean isExtend = assignmentService.extendDeadLine(id,days);
        if(!isExtend){
            return ResponseEntity.status(400).body(new ApiResponse("assignment was not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("assignment deleted successfully"));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title){
        ArrayList<Assignment> byTitle = assignmentService.getByTitle(title);
        if(byTitle.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no assignments yet"));
        }
        return ResponseEntity.status(200).body(byTitle);
    }
}

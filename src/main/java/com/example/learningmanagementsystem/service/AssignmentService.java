package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.Models.Assignment;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Service
public class AssignmentService {
    ArrayList<Assignment> assignments = new ArrayList<>();

    public ArrayList<Assignment> getAll(){
        return assignments;
    }

    public void addNew(Assignment assignment){
        assignments.add(assignment);
    }

    public boolean update(String id,Assignment assignment){
        for (int i=0;i<assignments.size();i++){
            if(assignments.get(i).getId().equals(id)){
                assignments.set(i,assignment);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id){
        for (int i=0;i<assignments.size();i++){
            if(assignments.get(i).getId().equals(id)){
                assignments.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Assignment> getByDate(LocalDateTime time){
        ArrayList<Assignment> byDate = new ArrayList<>();
        for (int i=0;i<assignments.size();i++){
            if(assignments.get(i).getDeadLine().equals(time)){
                byDate.add(assignments.get(i));
            }
        }
        return byDate;
    }
    public boolean openAssignment(String id,LocalDateTime deadLine){
        for (int i=0;i<assignments.size();i++){
            if(assignments.get(i).getId().equals(id)){
                assignments.get(i).setOpen(true);
                assignments.get(i).setDeadLine(deadLine);
                return true;
            }
        }
        return false;
    }
    public boolean extendDeadLine(String id,int days){
        for (int i=0;i<assignments.size();i++){
            if(assignments.get(i).getId().equals(id)){
                    assignments.get(i).getDeadLine().plusDays(days);
                    return true;
            }
        }
        return false;
    }
    public ArrayList<Assignment> getByTitle(String title){
        ArrayList<Assignment> byTitle = new ArrayList<>();
        for(Assignment a:assignments){
            if(a.getTitle().equals(title)){
                byTitle.add(a);
            }
        }
        return byTitle;
    }


}

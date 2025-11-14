package com.example.learningmanagementsystem.service;

import com.example.learningmanagementsystem.Models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getAll(){
        return courses;
    }

    public String addNew(Course course){
        if (course.isOpen()){
            return "course must be false";
        }
        if(course.getSeatsAvailable()!=0){
            return "seats must be 0";
        }
        String name = course.getName().replace("-","");
        course.setId(course.getId()+name+course.getSection());
        courses.add(course);
        return "added";
    }


    public String update(String id,Course course){
        if (course.isOpen()){
            return "course status must be false";
        }
        if(course.getSeatsAvailable()!=0){
            return "seats must be 0";
        }
        for (int i =0;i<courses.size();i++){
            if(courses.get(i).getId().equals(id)){
                course.setOpen(courses.get(i).isOpen());
                course.setSeatsAvailable(courses.get(i).getSeatsAvailable());
                String name = course.getName().replace("-","");
                course.setId(course.getId()+name+course.getSection());
                courses.set(i,course);
                return "updated";
            }
        }
        return "id not found";
    }

    public boolean delete(String id){
        for(int i =0;i<courses.size();i++){
            if(courses.get(i).equals(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }
    /************************************ END of CRUD **********************************/

    public boolean openCourse(String id,int seats){
        for(int i =0;i<courses.size();i++){
            if(courses.get(i).getId().equals(id)){
                courses.get(i).setOpen(true);
                courses.get(i).setSeatsAvailable(seats);
                return true;
            }
        }
        return false;
    }

    public boolean setTeacherName(String id, String name){
        for(int i =0;i<courses.size();i++){
            if(courses.get(i).getId().equals(id)){
                courses.get(i).setTeacherName(name);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> getOpenSections(){
        ArrayList<Course> open = new ArrayList<>();
        for(Course c :courses){
            if(c.isOpen()){
                open.add(c);
            }
        }
        return open;
    }

    public ArrayList<Course> getAllSections(String name){
        ArrayList<Course> sections = new ArrayList<>();
        for(Course c:courses){
            if(c.getName().equals(name)){
                sections.add(c);
            }
        }
        return sections;
    }


}

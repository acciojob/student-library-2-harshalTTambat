package com.driver.controller;

import com.driver.models.Student;
import com.driver.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
//Add required annotations
public class StudentController {

    @Autowired
    StudentService studentService;
    //Add required annotations
    @GetMapping("/getStudentByEmail/{email}")
    public ResponseEntity<String> getStudentByEmail(@RequestParam("email") String email){

        return new ResponseEntity<>("Student details printed successfully ", HttpStatus.OK);
    }

    //Add required annotations
    public ResponseEntity getStudentById(@RequestParam("id") int id){

        return new ResponseEntity<>("Student details printed successfully ", HttpStatus.OK);
    }

    //Add required annotations
    @PostMapping("/createStudent")
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        studentService.createStudent(student);
        return new ResponseEntity<>("the student is successfully added to the system", HttpStatus.CREATED);
    }

    //Add required annotations
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student){
        studentService.updateStudent(student);
        return new ResponseEntity<>("student is updated", HttpStatus.ACCEPTED);
    }

    //Add required annotations
    public ResponseEntity deleteStudent(@RequestParam("id") int id){

        return new ResponseEntity<>("student is deleted", HttpStatus.ACCEPTED);
    }

}

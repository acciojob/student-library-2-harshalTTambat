package com.driver.services;

import com.driver.models.Card;
import com.driver.models.Student;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    CardService cardService4;

    @Autowired
    StudentRepository studentRepository4;

    public Student getDetailsByEmail(String email){
        Student student = null;

        return student;
    }

    public Student getDetailsById(int id){
        Student student = null;

        return student;
    }

    public void createStudent(Student student){
        Card card = cardService4.createAndReturn(student);
        student.setCard(card);
        studentRepository4.save(student);
    }

    public void updateStudent(Student student){
        Student student1 = studentRepository4.findById(student.getId()).get();
        student1.setAge(student.getAge());
        student1.setEmailId(student.getEmailId());
        student1.setCountry(student.getCountry());
        student1.setName(student.getName());
        student1.setCard(student.getCard());
        studentRepository4.save(student1);
    }

    public void deleteStudent(int id){
        //Delete student and deactivate corresponding card
    }
}

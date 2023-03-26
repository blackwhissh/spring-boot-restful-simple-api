package com.blackwhissh.student.service;

import com.blackwhissh.student.model.Student;
import com.blackwhissh.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        boolean email = true;

        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email is already in use");
        }

        if(student.getEmail().endsWith("@gmail.com")  || student.getEmail().endsWith("@yahoo.com") || student.getEmail().endsWith("@outlook.com")) {
            studentRepository.save(student);
        }else {
            throw new IllegalStateException("Wrong Email provider");
        }



    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with provided ID (" + studentId + ") doesn't exists");
        }else{
            studentRepository.deleteById(studentId);

            System.out.println("Student with ID - " + studentId + " has been successfully deleted");
        }
    }

    public Optional<Student> getStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with provided ID (" + studentId + ") doesn't exists");
        }else{
            return studentRepository.findById(studentId);

        }
    }
}

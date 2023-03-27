package com.blackwhissh.student.service;

import com.blackwhissh.student.model.Student;
import com.blackwhissh.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with provided ID (" + studentId
                        + ") doesn't exists"));

        if(name !=null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
            studentRepository.save(student);
        }

        if(email !=null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("E-Mail is taken!");
            }
            student.setEmail(email);

            studentRepository.save(student);
        }

    }
}

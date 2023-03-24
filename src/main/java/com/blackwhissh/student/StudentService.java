package com.blackwhissh.student;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudents(){
        return List.of(
                new Student(
                        1L,
                        "Nicolas",
                        "blackwhissh@gmail.com",
                        LocalDate.of(2003, Month.MAY, 5),
                        20
                )
        );
    }
}

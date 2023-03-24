package com.blackwhissh.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.MAY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student nicolas = new Student(
                    "Nicolas",
                    "blackwhissh@gmail.com",
                    LocalDate.of(2003, MAY, 5)
            );

            Student george = new Student(
                    "George",
                    "george@gmail.com",
                    LocalDate.of(2003, MAY, 26)
            );

            Student lucas = new Student(
                    "Lucas",
                    "lucas@gmail.com",
                    LocalDate.of(2003, MAY, 5)
            );

            studentRepository.saveAll(
                    List.of(nicolas, george, lucas)
            );
        };
    }
}

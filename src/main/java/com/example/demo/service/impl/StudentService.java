package com.example.demo.service.impl;

import com.example.demo.domain.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    //methodes get
    List<Student> getAllStudents();
    Optional<Student> getStudentByEmail(String email);
    List<Student> getAllStudentByFirstName(String firstName);
    List<Student> getAllStudentOlderThan20();

    //methode pour sauvegarder la base
    Student save(Student student);

    //methodes pour modifier la base
    void changeEmail(Integer id, String email);
    void changeLastName(String email, String lastName);
    void incrementAges();

    //methode pour supprimer ligne donnee en parametres
    void deleteStudent(Student student);
}

package com.example.demo.service.impl;

import com.example.demo.domain.entities.Student;
import com.example.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getAllStudents() {
        log.debug("Request find all students");
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Student> getStudentByEmail(String email) {
        log.debug("Request find student by email : {}", email);
        return studentRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getAllStudentByFirstName(String firstName) {
        log.debug("Request find all students by firstName : {}", firstName);
        return studentRepository.findAllByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getAllStudentOlderThan20() {
        log.debug("Request find all students older than 20");
        return studentRepository.findOlder20();
    }

    @Override
    public Student save(Student student) {
        log.debug("Request save table");
        return studentRepository.save(student);
    }

    @Override
    public void changeEmail(Integer id, String email) {
        log.debug("Request change email for id : {}, email : {}", id, email);
        studentRepository.changeEmailById(id, email);
    }

    @Override
    public void changeLastName(String email, String lastName) {
        log.debug("Request change lastName for email : {}, lastName : {}", email, lastName);
        studentRepository.changeLastNameByEmail(email, lastName);
    }

    @Override
    public void incrementAges() {
        log.debug("Request increment Age");
        studentRepository.incrementAge();
    }

    @Override
    public void deleteStudent(Student student) {
        log.debug("Request delete Student : {}", student);
        studentRepository.delete(student);
    }
}

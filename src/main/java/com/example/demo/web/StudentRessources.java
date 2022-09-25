package com.example.demo.web;

import com.example.demo.domain.entities.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.impl.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class StudentRessources {

    private static final Logger log = LoggerFactory.getLogger(StudentRessources.class);

    private final StudentService studentService;

    public StudentRessources(StudentService studentService) {
        this.studentService = studentService;
    }

    // nos getters
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        log.debug("Request find all students");
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping(value = "/studentsByEmail", params = {"email"})
    public ResponseEntity<Student> getStudentByEmail(@RequestParam String email) {
        log.debug("Request find student by email : {}", email);
        Student student = studentService.getStudentByEmail(email).orElse(null);
        return ResponseEntity.ok(student);
    }

    @GetMapping(value = "/studentsByFirstName", params = {"firstName"})
    public ResponseEntity<List<Student>> getAllStudentByFirstName(@RequestParam String firstName) {
        log.debug("Request find all students by firstName : {}", firstName);
        List<Student> students = studentService.getAllStudentByFirstName(firstName);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/studentsOlder20")
    public ResponseEntity<List<Student>> getAllStudentOlderThan20() {
        log.debug("Request find all students older than 20");
        List<Student> students = studentService.getAllStudentOlderThan20();
        return ResponseEntity.ok(students);
    }

    //nos methodes post

    @PostMapping("/students")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        log.debug("Request save table");
        Student etudiant = studentService.save(student);
        return ResponseEntity.ok(etudiant);
    }

    @PatchMapping(value = "/studentsChangeMail", params = {"id","email"})
    public ResponseEntity<Void> changeEmail(@RequestParam Integer id, @RequestParam String email) {
        log.debug("Request change email for id : {}, email : {}", id, email);
        studentService.changeEmail(id, email);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/studentsChangeLastName", params = {"email","lastName"})
    public ResponseEntity<Void> changeLastName(@RequestParam String email, @RequestParam String lastName) {
        log.debug("Request change lastName for email : {}, lastName : {}", email, lastName);
        studentService.changeLastName(email, lastName);
        return ResponseEntity.ok().build();
    }

    //incrementation de l age
    @PostMapping("/incrementAges")
    public ResponseEntity<Void> incrementAges() {
        log.debug("Request increment Age");
        studentService.incrementAges();
        return ResponseEntity.ok().build();
    }

    //suppression de la table
    @DeleteMapping("/students")
    public ResponseEntity<Void> deleteStudent(@RequestBody Student student) {
        log.debug("Request delete Student : {}", student);
        studentService.deleteStudent(student);
        return ResponseEntity.ok().build();
    }
}

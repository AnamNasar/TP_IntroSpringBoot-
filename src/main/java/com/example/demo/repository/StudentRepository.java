package com.example.demo.repository;

import com.example.demo.domain.entities.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    //nous n'ajouterons pas la méthode findAll car héritée de JpaRepository, idem pour FindById

    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Student> findByEmail(String email);

    List<Student> findAllByFirstName(String firstName);

    @Query("select student from Student student where student.age > 20")
    List<Student> findOlder20();

    @Transactional
    @Modifying
    @Query("Update Student std set std.email = :email where std.id= :id")
    void changeEmailById(@Param("id") Integer id, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("Update Student std set std.lastName = :lastName where std.email= :email")
    void changeLastNameByEmail(@Param("email") String email, @Param("lastName") String lastName);

    @Transactional
    @Modifying
    @Query("Update Student std set std.age = std.age+1")
    void incrementAge();

}

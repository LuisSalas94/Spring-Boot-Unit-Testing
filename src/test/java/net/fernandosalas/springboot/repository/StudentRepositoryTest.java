package net.fernandosalas.springboot.repository;

import net.fernandosalas.springboot.entity.Student;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    // JUnit test for save student operation | given-when-then
    @DisplayName("JUnit test for save student operation")
    @Test
    public void givenStudentObject_whenSavingStudent_thenReturnStudentObject() {
        //given - precondition or setup
        Student student = Student.builder()
                .firstName("Fernando")
                .lastName("Salas")
                .email("fernando@gmail.com")
                .build();
        // when - action or the behavior we are going to test
        Student savedStudent = studentRepository.save(student);

        // then - verify the output
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isGreaterThan(0);
        assertThat(savedStudent.getFirstName()).isEqualTo("Fernando");
    }

    //JUnit test for getting al students operation
    @DisplayName("JUnit test for getting al students operation")
    @Test
    public void givenStudentList_whenFindAllStudents_thenReturnStudentList() {
        //given - precondition or setup
        Student student = Student.builder()
                .firstName("Fernando")
                .lastName("Salas")
                .email("fernando@gmail.com")
                .build();

        Student student2 = Student.builder()
                .firstName("Claudia")
                .lastName("Ramos")
                .email("claudia@gmail.com")
                .build();

        List<Student> studentList = Arrays.asList(student, student2);
        studentRepository.saveAll(studentList);

        // when - action or the behavior we are going to test
        List<Student> retrievedStudents = studentRepository.findAll();

        // then - verify the output
        assertThat(retrievedStudents.size()).isGreaterThan(0);
        assertThat(retrievedStudents).contains(student, student2);
        assertThat(retrievedStudents).isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(student, student2);
    }

    //JUnit test for get student by id
    @DisplayName("JUnit test for get student by id")
    @Test
    public void givenStudentId_whenFindStudentById_thenReturnStudentObject() {
        //given - precondition or setup
        Student student = Student.builder()
                .id(1L)
                .firstName("Fernando")
                .lastName("Salas")
                .email("fernando@gmail.com")
                .build();

        // when - action or the behavior we are going to test
        studentRepository.save(student);
        Optional<Student> savedStudent = studentRepository.findById(student.getId());

        // then - verify the output
        assertThat(savedStudent).isPresent()
                .hasValueSatisfying(studentDb -> {
                    assertThat(studentDb.getId()).isEqualTo(1L);
                    assertThat(studentDb.getFirstName()).isEqualTo("Fernando");
                    assertThat(studentDb.getLastName()).isEqualTo("Salas");
                    assertThat(studentDb.getEmail()).isEqualTo("fernando@gmail.com");
                });
    }

    //JUnit test to get student by email operation
    @DisplayName("JUnit test to get student by email operation")
    @Test
    public void givenStudentEmail_whenFindStudentByEmail_thenReturnStudentObject() {
        //given - precondition or setup
        Student student = Student.builder()
                .id(1L)
                .firstName("Fernando")
                .lastName("Salas")
                .email("fernando@gmail.com")
                .build();

        // when - action or the behavior we are going to test
        studentRepository.save(student);
        Student savedStudent = studentRepository.findByEmail(student.getEmail()).get();

        // then - verify the output
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getEmail()).isEqualTo(student.getEmail());
    }

    //JUnit test for updating student operation
    @DisplayName("JUnit test for updating student operation")
    @Test
    public void givenStudentObject_whenUpdatingStudent_thenReturnStudentObject() {
        //given - precondition or setup
        Student student = Student.builder()
                .id(1L)
                .firstName("Fernando")
                .lastName("Salas")
                .email("fernando@gmail.com")
                .build();
        studentRepository.save(student);
        // when - action or the behavior we are going to test
        Student savedStudent = studentRepository.findById(student.getId()).get();

        savedStudent.setFirstName("Luis");
        savedStudent.setLastName("Gave");
        savedStudent.setEmail("luis@gmail.com");

        Student updatedStudent = studentRepository.save(savedStudent);

        // then - verify the output
        assertThat(updatedStudent).isNotNull();
        assertThat(updatedStudent.getFirstName())
                .as("First name should be updated to 'Luis'").isEqualTo("Luis");
        assertThat(updatedStudent.getLastName())
                .as("Last name should be updated to 'Gave'")
                .isEqualTo("Gave");
        assertThat(updatedStudent.getEmail())
                .as("Email should be updated to 'luis@gmail.com'")
                .isEqualTo("luis@gmail.com");
    }

}

package net.fernandosalas.springboot.repository;

import net.fernandosalas.springboot.entity.Student;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}

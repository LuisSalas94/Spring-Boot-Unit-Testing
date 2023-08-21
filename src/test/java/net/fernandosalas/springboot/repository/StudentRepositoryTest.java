package net.fernandosalas.springboot.repository;

import net.fernandosalas.springboot.entity.Student;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    // JUnit test for save employee operation | given-when-then
    @DisplayName("JUnit test for save employee operation")
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
}

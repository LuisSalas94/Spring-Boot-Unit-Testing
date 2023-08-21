package net.fernandosalas.springboot.service;

import net.fernandosalas.springboot.entity.Student;
import net.fernandosalas.springboot.repository.StudentRepository;
import net.fernandosalas.springboot.service.implementation.StudentServiceImplementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImplementation studentServiceImplementation;
    private Student student;

    @BeforeEach
    public void setup() {
        student = Student.builder()
                .id(1L)
                .firstName("Fernando")
                .lastName("Salas")
                .email("fernando@gmail.com")
                .build();
    }

    //JUnit test for saveEmployee Method
    @DisplayName("JUnit test for saveEmployee Method")
    @Test
    public void givenStudentObject_whenSaveStudent_thenReturnStudentObject() {
        //given - precondition or setup
        given(studentRepository.save(student))
                .willReturn(student);

        // when - action or the behavior we are going to test
        Student savedStudent = studentServiceImplementation.saveStudent(student);

        // then - verify the output
        assertThat(savedStudent).isNotNull();
    }

}

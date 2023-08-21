package net.fernandosalas.springboot.service;

import net.fernandosalas.springboot.entity.Student;
import net.fernandosalas.springboot.repository.StudentRepository;
import net.fernandosalas.springboot.service.implementation.StudentServiceImplementation;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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

    //JUnit test for getAllStudents Method
    @DisplayName("JUnit test for getAllStudents Method")
    @Test
    public void givenStudentList_whenGetAllStudents_thenReturnStudentList() {
        //given - precondition or setup
        Student student1 = Student.builder()
                .id(2L)
                .firstName("Claudia")
                .lastName("Ramos")
                .email("claudia@gmail.com")
                .build();

        List<Student> mockStudentList = List.of(student, student1);
        given(studentRepository.findAll()).willReturn(mockStudentList);

        // when - action or the behavior we are going to test
        List<Student> studentList = studentServiceImplementation.getAllStudents();

        // then - verify the output
        assertThat(studentList).isNotNull();
        assertThat(studentList).hasSize(mockStudentList.size());
        assertThat(studentList).containsExactlyElementsOf(mockStudentList);
    }

}

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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @DisplayName("JUnit test for getAllStudents Method(negative scenario)")
    @Test
    public void givenEmptyStudentList_whenGetAllStudents_thenReturnEmptyStudentList() {
        //given - precondition or setup
        given(studentRepository.findAll()).willReturn(Collections.emptyList());

        // when - action or the behavior we are going to test
        List<Student> studentList = studentServiceImplementation.getAllStudents();

        // then - verify the output
        assertThat(studentList).isEmpty();
        assertThat(studentList).hasSize(0);
    }

    //JUnit test for getStudentById Method
    @DisplayName("JUnit test for getStudentById Method")
    @Test
    public void givenStudentId_whenGetStudentById_thenReturnStudentObject() {
        //given - precondition or setup
        long studentId = 1L;
        given(studentRepository.findById(studentId)).willReturn(Optional.of(student));

        // when - action or the behavior we are going to test
        Student savedStudent = studentServiceImplementation.getStudentById(studentId).get();

        // then - verify the output
        assertThat(savedStudent).isNotNull();
    }

    //JUnit test for updateStudent Method
    @DisplayName("JUnit test for updateStudent Method")
    @Test
    public void givenStudentObject_whenUpdateStudent_thenReturnUpdatedStudent() {
        //given - precondition or setup
        given(studentRepository.save(student)).willReturn(student);

        // when - action or the behavior we are going to test
        student.setFirstName("Claudia");
        student.setLastName("Ramos");
        student.setEmail("claudia@gmail.com");
        Student updatedStudent = studentServiceImplementation.updateStudent(student);

        // then - verify the output
        assertThat(updatedStudent.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(updatedStudent.getLastName()).isEqualTo(student.getLastName());
        assertThat(updatedStudent.getEmail()).isEqualTo(student.getEmail());
    }

    //JUnit test to delete Employee Method
    @DisplayName("JUnit test to delete Employee Method")
    @Test
    public void givenStudentId_whenDeleteStudent_thenNothing() {
        //given - precondition or setup
        long studentId = 1L;
        willDoNothing().given(studentRepository).deleteById(1L);

        // when - action or the behavior we are going to test
        studentServiceImplementation.deleteStudent(studentId);

        // then - verify the output
        verify(studentRepository, times(1)).deleteById(studentId);
    }

}

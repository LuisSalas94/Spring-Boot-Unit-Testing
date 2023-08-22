package net.fernandosalas.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.fernandosalas.springboot.entity.Student;
import net.fernandosalas.springboot.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;

@WebMvcTest
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenStudentObject_whenCreateStudent_thenReturnSavedStudent() throws Exception {
        //given - precondition or setup
        Student student = Student.builder()
                .firstName("Fernando")
                .lastName("Salas")
                .email("fernando@gmail.com")
                .build();

        // Mocking the behavior of studentService.saveStudent() method
        given(studentService.saveStudent(ArgumentMatchers.any(Student.class)))
                .willAnswer((invocationOnMock) -> invocationOnMock.getArgument(0));

        // when - action or the behavior we are going to test
        // Simulating an HTTP POST request to "/api/students"
        ResultActions response = mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        // then - verify the output
        // Asserting the response status code is 201 Created
        // Verifying response content using JSONPath
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(student.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(student.getLastName())))
                .andExpect(jsonPath("$.email", is(student.getEmail())));
    }

}

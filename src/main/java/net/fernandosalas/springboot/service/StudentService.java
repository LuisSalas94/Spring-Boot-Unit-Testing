package net.fernandosalas.springboot.service;

import net.fernandosalas.springboot.entity.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
}

package net.fernandosalas.springboot.service.implementation;

import lombok.AllArgsConstructor;
import net.fernandosalas.springboot.entity.Student;
import net.fernandosalas.springboot.repository.StudentRepository;
import net.fernandosalas.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImplementation implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}

package net.fernandosalas.springboot.contoller;

import lombok.AllArgsConstructor;
import net.fernandosalas.springboot.entity.Student;
import net.fernandosalas.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId) {
        return studentService.getStudentById(studentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long studentId,
                                                @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = studentService.getStudentById(studentId);
        if (optionalStudent.isPresent()) {
            Student savedStudent = optionalStudent.get();
            savedStudent.setFirstName(updatedStudent.getFirstName());
            savedStudent.setLastName(updatedStudent.getLastName());
            savedStudent.setEmail(updatedStudent.getEmail());
            Student updated = studentService.updateStudent(savedStudent);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

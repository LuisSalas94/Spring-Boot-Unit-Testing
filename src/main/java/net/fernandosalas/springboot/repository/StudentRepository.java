package net.fernandosalas.springboot.repository;

import net.fernandosalas.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

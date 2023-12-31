package net.fernandosalas.springboot.repository;

import net.fernandosalas.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    // define custom query using JPQL with index params
    @Query("select s from Student s where s.firstName =?1 and s.lastName = ?2")
    Student findByJPQLIndexParams(String firstName, String lastName);

    @Query("select s from Student s where s.firstName =:firstName and s.lastName =:lastName")
    Student findByJPQLNamedParams(String firstName, String lastName);

    @Query(value = "select * from students s where s.first_name = ?1 and s.last_name = ?2"
    ,nativeQuery = true)
    Student findByNativeIndexParams(String firstName, String lastName);

    @Query(value = "select * from students s where s.first_name =:firstName and s.last_name =:lastName"
            ,nativeQuery = true)
    Student findByNativeNamedParams(String firstName, String lastName);
}

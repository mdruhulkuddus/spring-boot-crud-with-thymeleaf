package com.example.crudthymeleaf.repositories;

import com.example.crudthymeleaf.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Student findByEmail(String email);
}

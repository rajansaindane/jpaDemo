package com.rajan.spring.jpaDemo.repository;

import com.rajan.spring.jpaDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rajan on 5/7/17.
 */
public interface StudentRepo extends JpaRepository<Student,Integer> {
}

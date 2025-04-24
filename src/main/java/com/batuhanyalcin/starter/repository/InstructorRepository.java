package com.batuhanyalcin.starter.repository;

import com.batuhanyalcin.starter.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository  extends JpaRepository<Instructor,Long> {
}

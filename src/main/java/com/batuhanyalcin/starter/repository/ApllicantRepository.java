package com.batuhanyalcin.starter.repository;

import com.batuhanyalcin.starter.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApllicantRepository extends JpaRepository<Applicant,Long> {
}

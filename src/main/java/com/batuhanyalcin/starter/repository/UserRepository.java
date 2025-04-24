package com.batuhanyalcin.starter.repository;

import com.batuhanyalcin.starter.core.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}

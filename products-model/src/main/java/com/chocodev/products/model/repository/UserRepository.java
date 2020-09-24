package com.chocodev.products.model.repository;

import com.chocodev.products.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

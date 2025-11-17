package com.main.repository;

import java.util.Optional;

import com.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByPhone(String phone);

}

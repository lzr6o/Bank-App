package com.learning.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learning.Controller.User;

public interface UserRepository extends JpaRepository<User, Integer>{
}

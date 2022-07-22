package com.learning.Bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.Bank.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}

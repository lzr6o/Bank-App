package com.learning.Bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.Bank.entity.Payload;

public interface PayloadRepository extends JpaRepository<Payload, Integer>{

}

package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Custromer;
@Repository
public interface ICustomerRepository extends JpaRepository<Custromer, Integer>{

}

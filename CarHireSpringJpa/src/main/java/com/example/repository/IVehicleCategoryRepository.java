package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.VehicleCategory;
@Repository
public interface IVehicleCategoryRepository extends JpaRepository<VehicleCategory, Integer>{

}

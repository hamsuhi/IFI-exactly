package com.example.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Manufacturer;
@Repository
public interface IManufacturerRepository extends JpaRepository<Manufacturer, Integer>{
//	 @Modifying(clearAutomatically = true)
//	    @Query("UPDATE Manufacturer c SET c.address = :address WHERE c.id = :companyId")
//	    int updateName(@Param("manufacturer_name") String manufacturerName, @Param("manufacurer_details") String manufacurerDetails);
	 //public List<Manufacturer> findByName(@Param("manufacturer_name") String manufacturerName);
}

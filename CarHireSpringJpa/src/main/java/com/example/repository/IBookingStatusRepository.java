package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.BookingStatus;
@Repository
public interface IBookingStatusRepository extends JpaRepository<BookingStatus, Integer>{

}

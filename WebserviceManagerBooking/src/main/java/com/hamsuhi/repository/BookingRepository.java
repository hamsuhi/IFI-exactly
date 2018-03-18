package com.hamsuhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hamsuhi.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	Booking findByBookingId(int id);
	//Booking findByBookName(String bookName);
}

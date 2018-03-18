package com.hamsuhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hamsuhi.entity.Booking;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Integer>{
	Booking findByBookingId(int id);
	Booking findByBookName(String bookName);
}

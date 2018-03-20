package com.hamsuhi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hamsuhi.entity.Booking;
import com.hamsuhi.repository.IBookingRepository;

@Service
//@Transactional
public class BookingService {

	@Autowired
	private IBookingRepository bookingRepository;

	public List<Booking> getAllBooking() {
		List<Booking> lst = bookingRepository.findAll();
		return lst;
	}

	public Booking getByIdBooking(int id) {
		Booking bookNew = bookingRepository.findByBookingId(id);
		if(bookNew!= null) {
			return bookNew;
		}
		return null;
	}
	

	public Booking getByName(String name) {
		return bookingRepository.findByBookName(name);
	}

	public boolean addBooking(Booking booking) {
		
		if (bookingRepository.save(booking) != null) {
			return true;
		}
		return false;
	}

	//ko co id, tu dong them 1 booking. Co booking thi update
	public boolean updateBooking(Booking booking) {
		if(bookingRepository.saveAndFlush(booking) != null) {
				return true;
		}
		return false;	
	}

	public boolean deleteBooking(int id) {
		if (this.getByIdBooking(id) != null) {
			bookingRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

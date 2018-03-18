package com.hamsuhi.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamsuhi.entity.Booking;
import com.hamsuhi.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	public List<Booking> getAllBooking() {
		List<Booking> list = bookingRepository.findAll();
			return list;
	}

	public Booking getByIdBooking(int id) {
		Booking bookNew = bookingRepository.findOne(id);
			return bookingRepository.findByBookingId(id);
	}

	public boolean addBooking(Booking booking) {
		if (bookingRepository.save(booking) == null) {
//			bookingRepository.save(booking);
			return false;
		}else {
			return true;
		}
	}

	public boolean updateBooking(Booking booking) {
		Booking bookNew = bookingRepository.findOne(booking.getBookingId());
		if (bookNew != null) {
			bookingRepository.saveAndFlush(booking);
			System.out.println("update ok");
			return true;
		}
		System.out.println("update not nnot ok");
		return false;
	}

	public boolean deleteByIdBooking(int id) {
		if (this.getByIdBooking(id) == null) {
			return false;
		} else {
			bookingRepository.delete(id);
			return true;
		}
	}

}

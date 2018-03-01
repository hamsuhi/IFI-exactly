/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 8, 2018, Nguyen Thanh Huong 
 */
package com.example.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.iservice.IBookingStatusService;
import com.example.model.BookingStatus;
import com.example.repository.IBookingStatusRepository;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
@Service
public class BookingStatusService implements IBookingStatusService {
	private static final Logger log = LogManager.getLogger(BookingStatusService.class);

	@Autowired
	private IBookingStatusRepository bookingStatusRepository;

	@Override
	@SuppressWarnings("unchecked")
	public List<BookingStatus> findAllBookingStatus() {
		List<BookingStatus> lst = bookingStatusRepository.findAll();
		for (BookingStatus b : lst) {
			b.toString();
		}
		return lst;
	}

	@Override
	public BookingStatus findBookingStatusById(int id) {
		BookingStatus book = bookingStatusRepository.findOne(id);
		if (book != null) {
			log.info("Da tim thay Booking Status By Id" + book.toString());
		} else {
			log.info("Khong tim thay Booking Status");
		}
		return book;
	}

	@Override
	public boolean addBookingStatus(BookingStatus book) {
		BookingStatus b = bookingStatusRepository.save(book);
		if (bookingStatusRepository.exists(b.getBookingStatusCode())) {
			log.info("Don't add because BookingStatus is existed: " + b.getBookingStatusDescription());
			return false;
		}
		log.info("*** Add Booking Status succedd!!" + b.toString());
		return true;
	}

	@Override
	public boolean updateBookingStatus(int id, String bookingStatusDescription) {
		BookingStatus b = bookingStatusRepository.findOne(id);
		if (b != null) {
			b.setBookingStatusDescription(bookingStatusDescription);
			bookingStatusRepository.saveAndFlush(b);
			log.info("****UPDATE Booking Status succed!" + b.toString());
			return true;
		} else {
			log.info("@.@__________Don't Update Booking Status!____@.@");
			return false;
		}

	}

	@Override
	public boolean deleteBookingStatus(int id) {
		BookingStatus b = bookingStatusRepository.findOne(id);
		if (b != null) {
			bookingStatusRepository.delete(id);
			log.info("******Delete Booking Status succedđ!!!*****");
			return true;
		} else {
			log.error("@.@____________Don't delete Booking Status!______@.@");
			return false;
		}

	}

}

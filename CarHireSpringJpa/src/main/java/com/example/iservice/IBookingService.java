/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.iservice;

import java.util.List;

import com.example.model.Booking;
import com.example.model.BookingStatus;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
public interface IBookingService {

	/**
	 * @return
	 */
	List<Booking> findAllBooking();

	/**
	 * @param id
	 * @return
	 */
	Booking findBookingById(int id);

	/**
	 * @param book
	 * @return
	 */
	Booking addBooking(Booking book);

	/**
	 * @param id
	 * @param bookingStatus
	 * @return
	 */
	boolean updateBooking(int id, BookingStatus bookingStatus);

	/**
	 * @param id
	 * @return
	 */
	boolean delete(int id);

}

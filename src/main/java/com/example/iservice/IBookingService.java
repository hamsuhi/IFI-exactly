/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.iservice;

import java.util.Date;
import java.util.List;

import com.example.model.Booking;
import com.example.model.BookingStatus;
import com.example.model.Custromer;
import com.example.model.Vehicle;

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
	Booking addBooking(Booking booking, BookingStatus bs, Custromer c, Vehicle v);

	/**
	 * @param id
	 * @param bookingStatus
	 * @return
	 */
	boolean updateBooking(int id, Booking booking, BookingStatus bookingStatus, Custromer custromer, Vehicle vehicle);

	/**
	 * @param id
	 * @return
	 */
	boolean delete(int id);

}

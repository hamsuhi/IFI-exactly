/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.iservice;

import java.util.List;

import com.example.model.BookingStatus;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
public interface IBookingStatusService {

	/**
	 * @return
	 */
	List<BookingStatus> findAllBookingStatus();

	/**
	 * @param id
	 * @return
	 */
	BookingStatus findBookingStatusById(int id);

	/**
	 * @param book
	 * @return
	 */
	boolean addBookingStatus(BookingStatus book);

	/**
	 * @param id
	 * @param bookingStatusDescription
	 * @return
	 */
	boolean updateBookingStatus(int id, String bookingStatusDescription);

	/**
	 * @param id
	 * @return
	 */
	boolean deleteBookingStatus(int id);

}

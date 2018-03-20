/*
 * Handling the Rest API call, coming from our angularjs based Front-end
 */
package com.hamsuhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hamsuhi.entity.Booking;
import com.hamsuhi.service.BookingService;

@RestController
@RequestMapping(value = "/api")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@GetMapping(value = "/booking/")
	public ResponseEntity<List<Booking>> getAllBooking() {
		List<Booking> lst = bookingService.getAllBooking();
		if (lst.isEmpty()) {
			return new ResponseEntity<List<Booking>>(lst, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Booking>>(lst, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
	public ResponseEntity<Booking> getById(@PathVariable("id") int id) {
		Booking book = bookingService.getByIdBooking(id);
		if (book != null) {
			return new ResponseEntity<Booking>(book, HttpStatus.OK);
		}
		return new ResponseEntity(new CustomErrorType("Booking with id " + id 
                + " not found"), HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/booking/getBook/")
	public ResponseEntity<Booking> getByName(@RequestParam("bookName") String bookName) {
		Booking book = bookingService.getByName(bookName);
		return new ResponseEntity<Booking>(book, HttpStatus.OK);
	}

	@PostMapping(value = "/booking/")
	public ResponseEntity<Void> addBooking(@RequestBody Booking booking, UriComponentsBuilder ucBuilder) {
		boolean book = bookingService.addBooking(booking);
		if (!book) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/booking/{id}").buildAndExpand(booking.getBookingId()).toUri());
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@PutMapping(value = "/booking/{id}")
	public ResponseEntity<?> updateBooking(@PathVariable("id") int id,@RequestBody Booking booking) {
		Booking bookNew = bookingService.getByIdBooking(id);
		bookNew.setBookingCol(booking.getBookingCol());
		bookNew.setBookName(booking.getBookName());
		bookNew.setDateImport(booking.getDateImport());
		bookNew.setPublisher(booking.getPublisher());
		
		boolean book = bookingService.updateBooking(bookNew);
		if (!book) {
			return new ResponseEntity<Booking>(bookNew, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Booking>(bookNew, HttpStatus.OK);
	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBooking(@PathVariable("id") int id) {
		boolean book = bookingService.deleteBooking(id);
		if (!book) {
			return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Booking>(HttpStatus.NO_CONTENT);
	}
}

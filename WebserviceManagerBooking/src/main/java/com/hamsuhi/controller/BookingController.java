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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hamsuhi.entity.Booking;
import com.hamsuhi.service.BookingService;

@RestController
@RequestMapping(value = "/api")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@GetMapping(value = "/booking")
	public ResponseEntity<List<Booking>> getAll() {
		List<Booking> list = bookingService.getAllBooking();
		if (list != null) {
			System.out.println("abc");
			return new ResponseEntity<List<Booking>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		Booking booking = bookingService.getByIdBooking(id);
		if (booking != null) {
			return new ResponseEntity<Booking>(booking, HttpStatus.OK);
		}
		return new ResponseEntity<Booking>(booking, HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/booking")
	public ResponseEntity<?> addUseRaw(Booking booking, UriComponentsBuilder ucBuilder) {
		boolean test = bookingService.addBooking(booking);
		if (test == false) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/booking/{id}").buildAndExpand(booking.getBookingId()).toUri());
		System.out.println("abc "+booking.getBookingId());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// all null is dead update, must be id
	@PutMapping(value = "/booking/{id}")
	public ResponseEntity<Booking> updateUseRaw(@PathVariable int id, Booking booking) {
		bookingService.updateBooking(booking);
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		if (bookingService.deleteByIdBooking(id) == false) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}

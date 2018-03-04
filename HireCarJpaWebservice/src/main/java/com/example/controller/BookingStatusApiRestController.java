package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.BookingStatus;
import com.example.service.BookingStatusService;

@RestController
@RequestMapping(value = "/api")
public class BookingStatusApiRestController {
	@Autowired
	private BookingStatusService bookingStatusService;

	@GetMapping(value = "/bookingstatus")
	public ResponseEntity<List<BookingStatus>> findAllBookingStatus() {
		List<BookingStatus> lst = bookingStatusService.findAllBookingStatus();
		if (lst.isEmpty()) {
			return new ResponseEntity<List<BookingStatus>>(lst, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BookingStatus>>(lst, HttpStatus.OK);
	}

	@GetMapping(value = "/bookingstatus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingStatus> findBookingStatusById(@PathVariable("id") int id) {
		BookingStatus book = bookingStatusService.findBookingStatusById(id);
		if (book == null) {
			return new ResponseEntity<BookingStatus>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookingStatus>(book, HttpStatus.OK);
	}

	@PostMapping(value = "/bookingstatus")
	public ResponseEntity<?> addBookingStatus(String bookingStatusDescription, UriComponentsBuilder ucBulder) {
		BookingStatus book = new BookingStatus(bookingStatusDescription);
		if (bookingStatusService.addBookingStatus(book)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBulder.path("/api/bookingstatus/{id}").buildAndExpand(book.getBookingStatusCode()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);

	}

	@PostMapping(value = "/bookingstatus/{id}")
	public ResponseEntity<?> updateBookingStatus(@PathVariable("id") String id, String bookingStatusDescription) {
		BookingStatus book = new BookingStatus(bookingStatusDescription);
		bookingStatusService.updateBookingStatus(Integer.parseInt(id), bookingStatusDescription);
		return new ResponseEntity<BookingStatus>(book, HttpStatus.OK);
	}

	@DeleteMapping(value = "/bookingstatus/{id}")
	public ResponseEntity<?> deleteBookingStatus(@PathVariable("id") int id) {
		if (bookingStatusService.deleteBookingStatus(id)) {
			return new ResponseEntity<BookingStatus>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<BookingStatus>(HttpStatus.NOT_FOUND);
	}
}

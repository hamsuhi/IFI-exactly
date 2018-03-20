/*
 * Handling the Rest API call, coming from our angularjs based Front-end
 */
package com.hamsuhi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hamsuhi.entity.Bookborrowing;
import com.hamsuhi.service.BookBorrowingService;
import com.hamsuhi.service.BookingService;
import com.hamsuhi.service.ReaderService;

@RestController
@RequestMapping(value = "/api")
public class BookBorrowingController {
	@Autowired
	private BookBorrowingService bookBorrowingService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ReaderService readerService;

	@GetMapping(value = "/bookborrowing/")
	public ResponseEntity<List<Bookborrowing>> getAllBookBorrowing() {
		List<Bookborrowing> lst = bookBorrowingService.getAllBookBorrowing();
		if (lst.isEmpty()) {
			return new ResponseEntity<List<Bookborrowing>>(lst, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Bookborrowing>>(lst, HttpStatus.OK);
	}

	@GetMapping(value = "/bookborrowing/{id}")
	public ResponseEntity<?> getByIdBookBorrowing(@PathVariable("id") int id) {
		Bookborrowing book = bookBorrowingService.getById(id);
		if (book != null) {
			return new ResponseEntity<Bookborrowing>(book, HttpStatus.OK);
		}
		return new ResponseEntity<Bookborrowing>(book, HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/bookborrowing/")
	public ResponseEntity<Void> addBookBorrowing(@RequestBody Bookborrowing bookBorrowing,
			UriComponentsBuilder ucBuilder) {
		int bookingId = bookBorrowing.getBooking().getBookingId();
		int readerId = bookBorrowing.getReader().getNumberCard();
		Bookborrowing bookReaderNew = new Bookborrowing(bookBorrowing.getDateBorrowing(), bookBorrowing.getDatePay(),
				bookingService.getByIdBooking(bookingId), readerService.getById(readerId));
		boolean bookReader = bookBorrowingService.addBookBorrowing(bookReaderNew);
		if (!bookReader) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders header = new HttpHeaders();
		header.setLocation(
				ucBuilder.path("/api/bookBorrowing/{id}").buildAndExpand(bookBorrowing.getBookBorrowingId()).toUri());
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@PutMapping(value = "/bookborrowing/{id}")
	public ResponseEntity<?> updateBookBorrowing(@PathVariable("id") int id, @RequestBody Bookborrowing bookBorrowing) {
		int bookingId = bookBorrowing.getBooking().getBookingId();
		int readerId = bookBorrowing.getReader().getNumberCard();
		Bookborrowing bookReaderNew = new Bookborrowing(bookBorrowing.getDateBorrowing(), bookBorrowing.getDatePay(),
				bookingService.getByIdBooking(bookingId), readerService.getById(readerId));
		boolean book = bookBorrowingService.updateBookBorrowing(bookReaderNew);
		if (!book) {
			return new ResponseEntity<Bookborrowing>(bookReaderNew, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bookborrowing>(bookReaderNew, HttpStatus.OK);
	}

	@DeleteMapping(value = "/bookborrowing/{id}")
	public ResponseEntity<?> deleteBookBorrowing(@PathVariable("id") int id) {
		boolean book = bookBorrowingService.deleteBookBorrowing(id);
		if (!book) {
			return new ResponseEntity<Bookborrowing>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bookborrowing>(HttpStatus.NO_CONTENT);
	}
}

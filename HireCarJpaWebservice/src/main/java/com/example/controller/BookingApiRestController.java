package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Booking;
import com.example.model.BookingStatus;
import com.example.model.Custromer;
import com.example.model.Vehicle;
import com.example.service.BookingService;
import com.example.service.BookingStatusService;
import com.example.service.CustomerService;
import com.example.service.VehicleService;

@RestController
@RequestMapping(value = "/api")
public class BookingApiRestController {
	private static final SimpleDateFormat formatDate = new SimpleDateFormat("dd/mm/yyyy");
	@Autowired
	private BookingService bookingService;

	@Autowired
	private BookingStatusService bookingStatusService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<Booking>> getAllBooking() {
		List<Booking> book = bookingService.findAllBooking();
		if (book.isEmpty()) {
			return new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Booking>>(book, HttpStatus.OK);
	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBookingById(@PathVariable int id) {
		Booking book = bookingService.findBookingById(id);
		if (book != null) {
			return new ResponseEntity<Booking>(book, HttpStatus.OK);
		}
		return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	private ResponseEntity<?> addBooking(String confirmationLetterSentYn, String dateFrom, String dateTo,
			String paymentRecievedYn, String bookingStatusCode, String customerId, String regNumber,
			UriComponentsBuilder ucBuilder) throws ParseException {
		BookingStatus bookStatus = bookingStatusService.findBookingStatusById(Integer.parseInt(bookingStatusCode));
		Custromer customer = customerService.findCustomerById(Integer.parseInt(customerId));
		Vehicle vehicle = vehicleService.findVehicleById(Integer.parseInt(regNumber));
		Booking book = bookingService.addBooking(confirmationLetterSentYn, formatDate.parse(dateFrom),
				formatDate.parse(dateTo), paymentRecievedYn, bookStatus, customer, vehicle);
		if (book == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/booking/{id}").buildAndExpand(book.getBookingId()).toUri());
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.POST)
	private ResponseEntity<?> updateBooking(@PathVariable("id") int id, String confirmationLetterSentYn,
			String dateFrom, String dateTo, String paymentRecievedYn, String bookingStatusCode, String customerId,
			String regNumber) throws ParseException {

		BookingStatus bookStatus = bookingStatusService.findBookingStatusById(Integer.parseInt(bookingStatusCode));
		Custromer customer = customerService.findCustomerById(Integer.parseInt(customerId));
		Vehicle vehicle = vehicleService.findVehicleById(Integer.parseInt(regNumber));
	
		Booking booking = new Booking(confirmationLetterSentYn, formatDate.parse(dateFrom), formatDate.parse(dateTo),
				paymentRecievedYn, bookStatus, customer, vehicle);
		boolean book = bookingService.updateBooking(id, booking, Integer.parseInt(bookingStatusCode),
				Integer.parseInt(customerId), Integer.parseInt(regNumber));
		if (book == false) {
			return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBooking(@PathVariable("id") int id) {
		if (bookingService.delete(id) == false) {
			return new ResponseEntity<BookingService>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookingService>(HttpStatus.NO_CONTENT);
	}

}
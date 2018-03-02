package com.example.controller;

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
@RequestMapping(value = "api")
public class BookingApiRestController {
	@Autowired
	private BookingService bookingService;

	@Autowired
	private BookingStatusService bookingStatusService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private ResponseEntity<List<Booking>> getAllBooking() {
		List<Booking> book = bookingService.findAllBooking();
		if (book.isEmpty()) {
			return new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Booking>>(book, HttpStatus.OK);
	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
	private ResponseEntity<?> getBookingById(@PathVariable int id) {
		Booking book = bookingService.findBookingById(id);
		if (book != null) {
			return new ResponseEntity<Booking>(book, HttpStatus.OK);
		}
		return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	private ResponseEntity<?> addBooking(@RequestParam(value = "booking_status_code") int idBs,
			@RequestParam(value = "reg_number") int idV, @RequestParam(value = "customer_id") int idC, Booking booking,
			UriComponentsBuilder ucBuilder) {
		BookingStatus bookStatus = bookingStatusService.findBookingStatusById(idBs);
		Vehicle vehicle = vehicleService.findVehicleById(idV);
		Custromer customer = customerService.findCustomerById(idC);
		Booking book = bookingService.addBooking(booking, bookStatus, customer, vehicle);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/booking/{id}").buildAndExpand(booking.getBookingId()).toUri());
		return new ResponseEntity<Booking>(header, HttpStatus.OK);
	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.PUT)
	private ResponseEntity<?> updateBooking(@PathVariable("id") int id,
			@RequestParam(value = "booking_status_code") int idBs, @RequestParam(value = ("reg_number")) int idV,
			@RequestParam(value = "customer_id") int idC, @ModelAttribute("booking") Booking booking) {
		BookingStatus bookStatus = bookingStatusService.findBookingStatusById(idBs);
		Vehicle vehicle = vehicleService.findVehicleById(idV);
		Custromer customer = customerService.findCustomerById(idC);
		boolean book = bookingService.updateBooking(id, booking, bookStatus, customer, vehicle);
		if (book == false) {
			return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}

	@RequestMapping(value = " /booking/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<?> deleteBooking(@PathVariable int id) {
		if (bookingService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}

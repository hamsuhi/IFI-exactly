package com.example.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.iservice.IBookingService;
import com.example.model.Booking;
import com.example.model.BookingStatus;
import com.example.model.Custromer;
import com.example.model.Vehicle;
import com.example.repository.IBookingRepository;
import com.example.repository.IBookingStatusRepository;
import com.example.repository.ICustomerRepository;
import com.example.repository.IVehicleRepository;

@Service
public class BookingService implements IBookingService {
	private static final Logger log = LogManager.getLogger(BookingService.class);
	@Autowired
	private IBookingRepository bookingRepository;

	@Autowired
	private IBookingStatusRepository bookingStatusRepository;

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IVehicleRepository vehicleRepository;

	@Override
	@SuppressWarnings("unchecked")
	public List<Booking> findAllBooking() {
		List<Booking> lst = bookingRepository.findAll();
		for (Booking b : lst) {
			b.toString();
		}
		return lst;
	}

	@Override
	public Booking findBookingById(int id) {
		Booking book = bookingRepository.findOne(id);
		if (book != null) {
			log.info("Da tim thay Booking By Id" + book.toString());
		} else {
			log.info("Khong tim thay booking by id");
		}
		return book;
	}

	@Override
	public Booking addBooking(String confirmationLetterSentYn, Date dateFrom, Date dateTo, String paymentRecievedYn,
			BookingStatus bs, Custromer c, Vehicle v) {
		Booking booking = new Booking(confirmationLetterSentYn, dateFrom, dateTo, paymentRecievedYn, bs, c, v);
		boolean existBookingStatus = bookingStatusRepository.exists(bs.getBookingStatusCode());
		boolean existCustomer = customerRepository.exists(c.getCustomerId());
		boolean existVehicle = vehicleRepository.exists(v.getRegNumber());

		// b.setConfirmationLetterSentYn(confirmationLetterSentYn);
		// b.setDateFrom(dateFrom);
		// b.setDateTo(dateTo);
		// b.setPaymentRecievedYn(paymentRecievedYn);
		// b.setBookingStatus(bookingStatus);
		// b.setCustromer(custromer);
		// b.setVehicle(vehicle);
		if (bookingRepository.exists(booking.getBookingId()) || !existBookingStatus || !existCustomer || !existVehicle) {
			log.info("Ko them book: " + booking.getConfirmationLetterSentYn());

		}
		bookingRepository.save(booking);
		log.info("Thêm book thành công: " + booking.toString());
		return booking;
	}

	@Override
	public boolean updateBooking(int id, Booking booking, int bookingStatusId, int custromerId, int vehicleId) {
		Booking b = bookingRepository.findOne(id);
		BookingStatus bs = bookingStatusRepository.getOne(bookingStatusId);
		Custromer c = customerRepository.getOne(custromerId);
		Vehicle v = vehicleRepository.getOne(vehicleId);
		boolean existBookingStatus = bookingStatusRepository.exists(bookingStatusId);
		boolean existCustomer = customerRepository.exists(custromerId);
		boolean existVehicle = vehicleRepository.exists(vehicleId);
		if (b != null) {
			b.setConfirmationLetterSentYn(booking.getConfirmationLetterSentYn());
			b.setDateFrom(booking.getDateFrom());
			b.setDateTo(booking.getDateTo());
			b.setPaymentRecievedYn(booking.getPaymentRecievedYn());
			b.setBookingStatus(bs);
			b.setCustromer(c);
			b.setVehicle(v);
			if (existBookingStatus || existCustomer || existVehicle) {
				bookingRepository.saveAndFlush(b);
				log.info("*****Update book succedd!!! " + b.toString());
				return true;
			} else {
				log.info("@.@______________Update BOOKING failed!________@.@");
				return false;
			}
		}
		log.info("@.@______________Update BOOKING failed!!!!!________@.@");
		return false;
	}

	@Override
	public boolean delete(int id) {
		Booking b = bookingRepository.findOne(id);
		if (b != null) {
			bookingRepository.delete(b);
			return true;
		}
		return false;
	}
}

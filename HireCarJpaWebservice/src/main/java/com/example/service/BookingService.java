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
	public Booking addBooking(Booking booking, BookingStatus bs, Custromer c, Vehicle v) {
		Booking b = bookingRepository.save(booking);
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
		if (bookingRepository.exists(b.getBookingId()) || !existBookingStatus || !existCustomer || !existVehicle) {
			log.info("Ko them book: " + b.getConfirmationLetterSentYn());
		}
		bookingRepository.save(b);
		log.info("Thêm book thành công: " + b.toString());
		return b;
	}

	@Override
	public boolean updateBooking(int id, Booking booking, BookingStatus bookingStatus, Custromer custromer, Vehicle vehicle) {
		Booking b = bookingRepository.findOne(id);
		boolean existBookingStatus = bookingStatusRepository.exists(bookingStatus.getBookingStatusCode());
		boolean existCustomer = customerRepository.exists(custromer.getCustomerId());
		boolean existVehicle = vehicleRepository.exists(vehicle.getRegNumber());
		if (b != null) {
			b.setConfirmationLetterSentYn(booking.getConfirmationLetterSentYn());
			b.setDateFrom(booking.getDateFrom());
			b.setDateTo(booking.getDateTo());
			b.setPaymentRecievedYn(booking.getPaymentRecievedYn());
			b.setBookingStatus(bookingStatus);
			b.setCustromer(custromer);
			b.setVehicle(vehicle);
			if(existBookingStatus || existCustomer || existVehicle) {
				bookingRepository.saveAndFlush(b);
				log.info("*****Update book succedd!!! " + b.toString());
				return true;
			}else {
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
			log.info("Xóa thành công! ");
			return true;
		} else {
			log.error("Don't delete Booking!");
			return false;
		}
	}
}

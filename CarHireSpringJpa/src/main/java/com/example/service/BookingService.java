package com.example.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.iservice.IBookingService;
import com.example.model.Booking;
import com.example.model.BookingStatus;
import com.example.repository.IBookingRepository;

@Service
public class BookingService implements IBookingService{
	private static final Logger log = LogManager.getLogger(BookingService.class);
	@Autowired
	private IBookingRepository bookingRepository;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Booking> findAllBooking(){
		List<Booking> lst = bookingRepository.findAll();
		for(Booking b: lst) {
			b.toString();
		}
		return lst;
	}
	
	@Override
	public Booking findBookingById(int id) {
		Booking book = bookingRepository.findOne(id);
		if(book!=null) {
			log.info("Da tim thay Booking By Id"+ book.toString());
		}else {
			log.info("Khong tim thay booking by id");
		}
		return book;
	}
	
	@Override
	public Booking addBooking(Booking book) {
		Booking b = bookingRepository.save(book);
		log.info("Thêm book thành công: "+ b.toString());
		return b;
	}
	
	@Override
	public boolean updateBooking(int id, BookingStatus bookingStatus) {
		Booking b = bookingRepository.findOne(id);
		if(b!=null) {
			b.setBookingStatus(bookingStatus);
//			b.setConfirmationLetterSentYn(book.getConfirmationLetterSentYn());
//			b.setCustromer(book.getCustromer());
//			b.setDateFrom(book.getDateFrom());
//			b.setDateTo(book.getDateTo());
//			b.setPaymentRecievedYn(book.getPaymentRecievedYn());
//			b.setVehicle(book.getVehicle());
			bookingRepository.saveAndFlush(b);
			log.info("*****Update book succedd!!! "+ b.toString());
			return true;
		}else {
			log.info("@.@______________Update BOOKING failed!________@.@");
			return false;
		}
	}
	
	@Override
	public boolean delete(int id) {
		Booking b = bookingRepository.findOne(id);
		if(b!=null) {
			bookingRepository.delete(b);
			log.info("Xóa thành công! ");
			return true;
		}else {
			log.error("Don't delete Booking!");
			return false;
		}
	}
}

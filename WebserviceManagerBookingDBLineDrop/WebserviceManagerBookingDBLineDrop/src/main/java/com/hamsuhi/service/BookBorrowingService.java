package com.hamsuhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hamsuhi.entity.Bookborrowing;
import com.hamsuhi.repository.IBookBorrowingRepository;

@Service
@Transactional
public class BookBorrowingService {
	@Autowired
	private IBookBorrowingRepository bookBorrowingRepository;

	public List<Bookborrowing> getAllBookBorrowing() {
		return bookBorrowingRepository.findAll();
	}

	public Bookborrowing getById(int id) {
		return bookBorrowingRepository.findByBookBorrowingId(id);
	}

	public boolean addBookBorrowing(Bookborrowing bookBorrowing) {
		if (bookBorrowingRepository.save(bookBorrowing) != null) {
			return true;
		}
		return false;
	}

	public boolean updateBookBorrowing(Bookborrowing bookBorrowing) {
		if (bookBorrowingRepository.saveAndFlush(bookBorrowing) != null) {
			return true;
		}
		return false;
	}

	public boolean deleteBookBorrowing(int id) {
		if (this.getById(id) != null) {
			bookBorrowingRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

package com.hamsuhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hamsuhi.entity.Booking;
import com.hamsuhi.entity.Reader;
import com.hamsuhi.repository.IReaderRepository;

@Service
@Transactional
public class ReaderService {

	@Autowired
	private IReaderRepository readerRepository;

	public List<Reader> getAllReader() {
		return readerRepository.findAll();
	}

	public Reader getById(int id) {
		return readerRepository.findByNumberCard(id);
	}

	public Reader getByName(String name) {
		return readerRepository.findByFullName(name);
	}

	public boolean addReader(Reader reader) {
		if (readerRepository.save(reader) != null) {
			return true;
		}
		return false;
	}

	public boolean updateReader(Reader reader) {
		if (readerRepository.saveAndFlush(reader) != null) {
			return true;
		}
		return false;
	}

	public boolean deleteReader(int id) {
		if (this.getById(id) != null) {
			readerRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

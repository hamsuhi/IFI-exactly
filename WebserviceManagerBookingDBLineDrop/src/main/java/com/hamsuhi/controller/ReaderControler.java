/*
 * Handling the Rest API call, coming from our angularjs based Front-end
 */
package com.hamsuhi.controller;

import java.util.List;

import javax.annotation.security.PermitAll;

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

import com.hamsuhi.entity.Reader;
import com.hamsuhi.service.ReaderService;

@RestController
@RequestMapping(value = "/api")
public class ReaderControler {
	@Autowired
	private ReaderService readerService;

	@GetMapping(value = "/reader/")
	public ResponseEntity<List<Reader>> getAll() {
		List<Reader> lst = readerService.getAllReader();
		return new ResponseEntity<List<Reader>>(lst, HttpStatus.OK);
	}

	@GetMapping(value = "/reader/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) {
		Reader reader = readerService.getById(id);
		if (reader != null) {
			return new ResponseEntity<Reader>(reader, HttpStatus.OK);
		}
		return new ResponseEntity<Reader>(reader, HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/reader/getName/")
	public ResponseEntity<?> getByName(@PathVariable String name) {
		Reader reader = readerService.getByName(name);
		return new ResponseEntity<Reader>(reader, HttpStatus.OK);
	}

	@PostMapping(value = "/reader/")
	public ResponseEntity<?> addReader(@RequestBody Reader reader, UriComponentsBuilder ucBuilder) {
		boolean testRead = readerService.addReader(reader);
		if (!testRead) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("/api/reader/{id}").buildAndExpand(reader.getNumberCard()).toUri());
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@PutMapping(value = "/reader/{id}")
	public ResponseEntity<?> updateReader(@PathVariable int id,@RequestBody Reader reader) {
		Reader readerNew = readerService.getById(id);
		readerNew.setAddress(reader.getAddress());
		readerNew.setDateOfBirth(reader.getDateOfBirth());
		readerNew.setFullName(reader.getFullName());
		readerNew.setGender(reader.getGender());
		readerNew.setTel(reader.getTel());
		readerService.updateReader(readerNew);
		boolean testReader = readerService.updateReader(readerNew);
		if (!testReader) {
			return new ResponseEntity<Reader>(readerNew, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reader>(readerNew, HttpStatus.OK);
	}
	
	

	@DeleteMapping(value = "/reader/{id}")
	public ResponseEntity<?> deleteReader(@PathVariable int id) {
		boolean testReader = readerService.deleteReader(id);
		if (!testReader) {
			return new ResponseEntity<Reader>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reader>(HttpStatus.NO_CONTENT);
	}
}

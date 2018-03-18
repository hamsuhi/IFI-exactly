/*
 * Handling the Rest API call, coming from our angularjs based Front-end
 */
package com.hamsuhi.controller;

import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hamsuhi.entity.Reader;
import com.hamsuhi.service.ReaderService;

@RestController
@RequestMapping(value = "/api")
public class ReaderController {

	@Autowired
	private ReaderService readerService;

	@GetMapping(value = "/reader")
	public ResponseEntity<List<Reader>> getAll() {
		List<Reader> list = readerService.getAllReading();
		if (list != null) {
			return new ResponseEntity<List<Reader>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<Reader>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/reader/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") int id) {
		Reader reader = readerService.getByIdReader(id);
		if (reader != null) {
			return new ResponseEntity<Reader>(reader, HttpStatus.OK);
		}
		return new ResponseEntity<Reader>(reader, HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/reader")
	public ResponseEntity<?> addUseRaw(Reader reader, UriComponentsBuilder ucBuilder) {
		boolean test = readerService.addReader(reader);
		if (test == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/reader/{id}").buildAndExpand(reader.getNumberCard()).toUri());
		System.out.println("reader add:" + reader.getNumberCard());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// all null is dead update, must be id
	@PutMapping(value = "/reader/{id}")
	public ResponseEntity<?> updateUseRaw(@PathVariable(value = "id") Integer id, Reader reader) {
		if (readerService.updateReader(reader) == true) {
			return new ResponseEntity<Reader>(reader, HttpStatus.OK);
		}
		return new ResponseEntity<Reader>(reader, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/reader/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		if (readerService.deleteByIdReader(id) == false) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}
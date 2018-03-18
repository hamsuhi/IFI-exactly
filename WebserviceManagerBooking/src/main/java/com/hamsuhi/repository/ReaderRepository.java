package com.hamsuhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hamsuhi.entity.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {
	Reader findBynumberCard(Integer id);
	
	Reader findByReaderName (String proName);
//	List<Reader> findByReaderNameContaining (String proName);

}

package com.hamsuhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hamsuhi.entity.Reader;

@Repository
public interface IReaderRepository extends JpaRepository<Reader, Integer>{
	Reader findByNumberCard(int numberCard);
	Reader findByFullName(String fullName);
}

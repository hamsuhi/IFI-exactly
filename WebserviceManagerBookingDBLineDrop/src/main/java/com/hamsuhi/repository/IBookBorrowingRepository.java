package com.hamsuhi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hamsuhi.entity.Bookborrowing;

@Repository
public interface IBookBorrowingRepository extends JpaRepository<Bookborrowing, Integer> {
	Bookborrowing findByBookBorrowingId(int bookBorrowingId);
}

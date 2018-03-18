package com.hamsuhi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hamsuhi.entity.BookBorrowing;
import com.hamsuhi.entity.BookBorrowingId;

@Repository
public interface BookBorrowingRepository extends JpaRepository<BookBorrowing,BookBorrowingId> {
	//tim theo so the by id
	List<BookBorrowing> findByIdNumberCard(Integer id);
	
//	//tim kiem ten nguoi doc trong bang Reader
//	List<BookBorrowing> findByReaderReaderNameContaining(String readerName);
//	
//	//tim kiem ten sach tron bang sach
//	List<BookBorrowing> findByBookingBookNameContaining(String bookName);
}

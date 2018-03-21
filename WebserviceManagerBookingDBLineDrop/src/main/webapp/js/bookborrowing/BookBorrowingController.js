/**
 * 
 */
'use strict';
//'ReaderService','BookingService',
angular.module('myApp').controller('BookBorrowingController', [ 'BookBorrowingService', '$scope', function(BookBorrowingService,$window, $scope) {
	var self = this;
	self.bookborrowing = {}; // khai bao 1 OBJ
	self.bookborrowings = [];

	self.submit = submit;
	self.getAllBookBorrowings = getAllBookBorrowings;
	self.getAllReaders = getAllReaders;
	self.getAllBookings = getAllBookings;
	self.createBookBorrowing = createBookBorrowing;
	self.updateBookBorrowing = updateBookBorrowing;
	self.removeBookBorrowing = removeBookBorrowing;
	self.bookborrowingToSubmit = bookborrowingToSubmit;
	self.reset = reset;

	self.successMessage = '';
	self.errorMessage = '';
	self.done = false;

	function submit() {
		if (self.bookborrowing.bookBorrowingId === undefined || self.bookborrowing.bookBorrowingId === null) {
			createBookBorrowing(self.bookborrowing);
		} else {
			updateBookBorrowing(self.bookborrowing.bookBorrowingId, self.bookborrowing);
		}
	}

	function getAllBookBorrowings() {
		return BookBorrowingService.getAllBookBorrowings();
	}

	function getAllReaders() {
		return BookBorrowingService.getAllReaders();
	}

	function getAllBookings() {
		return BookBorrowingService.getAllBookings();
	}
	
	function createBookBorrowing(bookborrowing) {
		BookBorrowingService.createBookBorrowing(bookborrowing).then(
			function(response) {
				self.successMessage = 'BookBorrowing created successfully';
				self.errorMessage = '';
				self.done = true; //self.done =!self.done: se duoc goi lai
				self.bookborrowing = {};
				$scope.bookborrowingForm.$setPristine(); //set rong
			}, function(errResponse) {
				self.errorMessage = 'Error while createing BookBorrowing: ' + errResponse;
				self.successMessage = '';
			}
		);
	}

	function updateBookBorrowing(id, bookborrowing) {
		BookBorrowingService.updateBookBorrowing(id, bookborrowing).then(
			function(response) {
				self.successMessage = 'Bookborrowing updated successfully';
				self.errorMessage = '';
				self.done = true;
				//self.bookborrowing ={};
				$scope.bookborrowingForm.$setPristine();
			}, function(errResponse) {
				self.errorMessage = 'Error while updateing BookBorrowing: ' + errResponse;
				self.successMessage = '';
			}
		);
	}
	
	function removeBookBorrowing(id) {
		BookBorrowingService.removeBookBorrowing(id).then(
			function(response) {
				self.successMessage = 'BookBorrowing deleted successfully';
				self.errorMessage = '';
				self.done = true;
			}
		);

	}
	
/*	function confirm(id){
		if($window.confirm("Are you want to delete Book Borrowing?")){
			removeBookBorrowing(id);
		}
	}*/


	function bookborrowingToSubmit(id) {
		self.successMessage = '';
		self.errorMessage = '';
		BookBorrowingService.getBookBorrowing(id).then(
			function(bookborrowing) {
				self.bookborrowing = bookborrowing;
				self.bookborrowing.dateBorrowing = new Date(self.bookborrowing.dateBorrowing);
				self.bookborrowing.datePay = new Date(self.bookborrowing.datePay);
			}
		);
	}
	
	function reset() {
		self.successMessage = '';
		self.errorMessage = '';
		self.bookborrowing = {};
		$scope.bookborrowingForm.$setPristine();
	}
} ])

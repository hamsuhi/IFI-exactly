/**
 * 
 */
'use strict';
angular.module('myApp').controller('BookBorrowingController', [ 'BookBorrowingService', '$scope', function(BookBorrowingService, $scope) {
	var self = this;
	self.bookborrowing = {}; //1 OBJ
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
		if (self.bookborrowing.id === undefined || self.bookborrowing.id === null) {
			createBookBorrowing(self.bookborrowing);
		} else {
			updateBookBorrowing(self.bookborrowing.id, self.bookborrowing);
		}
	}

	function getAllBookBorrowings() {
		return BookBorrowingService.getAllBookBorrowings();
	}

	function getAllReaders() {
		return ReaderService.getAllReaders();
	}

	function getAllBookings() {
		return BookingService.getAllBookings();
	}

	function createBookBorrowing(bookborrowing) {
		BookBorrowingService.createBookBorrowing(bookborrowing).then(
			function(response) {
				self.successMessage = 'BookBorrowing created successfully';
				self.errorMessage = '';
				self.done = true;
				self.bookborrowing = {};
				$scope.bookborrowingForm.$setPristine();
			}, function(errResponse) {
				self.errorMessage = 'Error while createing BookBorrowing: ' + errResponse;
				self.successMessage = '';
			}
		);
	}

	function updateBookBorrowing(id, bookborrowing) {
		BookBorrowingService.updateBookBorrowing(id, bookborrowing).then(
			function(response) {
				self.successMessage = 'bookborrowing updated successfully';
				self.errorMessage = '';
				self.done = true;
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


	function bookborrowingToSubmit(id) {
		self.successMessage = '';
		self.errorMessage = '';
		BookBorrowingService.getBookBorrowing(id).then(
			function(bookborrowing) {
				self.bookborrowing = bookborrowing;
				self.bookborrowing.dob = new Date(self.bookborrowing.dob);
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
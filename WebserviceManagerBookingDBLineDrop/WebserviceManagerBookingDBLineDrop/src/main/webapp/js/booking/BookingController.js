/**
 * Goi den service
 */
'use strict';
angular.module('myApp').controller('BookingController', [ 'BookingService', '$scope', function(BookingService, $scope) {

	var self = this;
	self.booking = {};//1 OBJ
	self.bookings = [];

	self.submit = submit; 	//Dang ki thu vien can dung
	self.getAllBookings = getAllBookings;
	self.createBooking = createBooking;
	self.updateBooking = updateBooking;
	self.removeBooking = removeBooking;
	self.bookingToSubmit = bookingToSubmit;
	self.reset = reset;

	self.successMessage = '';
	self.errorMessage = '';
	self.done = false;


	function submit() {
		if (self.booking.bookingId === undefined || self.booking.bookingId === null) {
			createBooking(self.booking);
		} else {
			updateBooking(self.booking.bookingId, self.booking);
		}
	}

	function getAllBookings() {
		return BookingService.getAllBookings();
	}

	function createBooking(booking) {
		BookingService.createBooking(booking).then(
			function(response) {
				self.successMessage = 'Booking created successfully';
				self.errorMessage = '';
				self.done = true;
				self.booking = {};
				$scope.bookingForm.$setPristine();// set form back rong khi muon lay lai 1 form ban dau sau khi save va update.
			}, function(errResponse) {
				self.errorMessage = 'Error while createing Booking: ' + errResponse;
				self.successMessage = '';
			}
		);
	}

	function updateBooking(id, booking) {
		BookingService.updateBooking(id, booking).then(
			function(response) {
				self.successMessage = 'Booking updated successfully';
				self.errorMessage = '';
				self.done = true;
				$scope.bookingForm.$setPristine();
			}, function(errResponse) {
				self.errorMessage = 'Error while updateing Booking: ' + errResponse;
				self.successMessage = '';
			}
		);
	}
	
	function removeBooking(id) {
		BookingService.removeBooking(id).then(
			function(response) {
				self.successMessage = 'Booking deleted successfully';
				self.errorMessage = '';
				self.done = true;
			}
		);

	}
	
	//
	function bookingToSubmit(id) {
		self.successMessage = '';
		self.errorMessage = '';
		BookingService.getBooking(id).then(
			function(booking) {
				self.booking = booking;
				self.booking.dateImport = new Date(self.booking.dateImport); // thiet dat ngay
			}
		);
	}
	
	//reset
	function reset() {
		self.successMessage = '';
		self.errorMessage = '';
		self.booking = {};
		$scope.bookingForm.$setPristine();
	}
} ])
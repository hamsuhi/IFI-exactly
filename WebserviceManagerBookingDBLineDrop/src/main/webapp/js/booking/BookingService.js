/**
 * Lay du lieu tu server
 */
'use strict';

angular.module('myApp').factory('BookingService',['$localStorage','$http','$q','urls', //Đặt tên cho service (BookingService) và định nghĩa các thư viện cần dùng
	function($localStorage,$http,$q,urls){
		var factory = {
				loadAllBookings: loadAllBookings,
				getAllBookings: getAllBookings,
				getBooking: getBooking,
				createBooking:createBooking,
				updateBooking:updateBooking,
				removeBooking:removeBooking
		};
		
		return factory;
		
		function loadAllBookings(){
				var deferred = $q.defer();// 1 c/v chua duoc thuc hien
				$http.get(urls.BOOKING_SERVICE_API)
				.then(function(response) {
					$localStorage.bookings = response.data;
					deferred.resolve(response);
				},function(errResponse){
					deferred.reject(errResponse);
				}
			);
			return deferred.promise;
		}
		
		function getAllBookings() {
			return $localStorage.bookings;
		}
		
		function getBooking(id) {
			var deferred = $q.defer();
			$http.get(urls.BOOKING_SERVICE_API + id) //http://localhost:8181/api/booking/{id}
			.then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}
		
		function createBooking(booking) {
			var defferred = $q.defer();
			$http.post(urls.BOOKING_SERVICE_API, booking).then(
					function(response) {
						loadAllBookings();
						defferred.resolve(response.data);
					},function(errResponse){
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function updateBooking(id,booking) {
			var defferred = $q.defer();
			$http.put(urls.BOOKING_SERVICE_API + id , booking).then(
					function(response) {
						loadAllBookings();
						defferred.resolve(response.data);
					},
					function(errResponse) {
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function removeBooking(id) {
			var defferred = $q.defer();
			$http.delete(urls.BOOKING_SERVICE_API+id).then(
					function(response) {
						loadAllBookings();
						defferred.resolve(response.data);
					},
					function(errResponse){
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;// bat dong bo
		}
		
		
	}
]);
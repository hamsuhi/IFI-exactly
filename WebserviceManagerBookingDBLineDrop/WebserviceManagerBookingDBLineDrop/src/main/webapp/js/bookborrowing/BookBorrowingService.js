/**
 * 
 */
'use strict';

angular.module('myApp').factory('BookBorrowingService',['$localStorage','$http','$q','urls', //Đặt tên cho service (BookBorrowingService) và định nghĩa các thư viện cần dùng
	function($localStorage,$http,$q,urls){
		var factory = {
				loadAllBookBorrowings: loadAllBookBorrowings,
				getAllBookBorrowings: getAllBookBorrowings,
				loadAllBookings : loadAllBookings,
				getAllBookings : getAllBookings,
				loadAllReaders : loadAllReaders,
				getAllReaders : getAllReaders,
				getBookBorrowing: getBookBorrowing,
				createBookBorrowing:createBookBorrowing,
				updateBookBorrowing:updateBookBorrowing,
				removeBookBorrowing:removeBookBorrowing
		};
		
		return factory;
		
		function loadAllBookBorrowings(){
				var deferred = $q.defer();
				$http.get(urls.BOOKBORROWING_SERVICE_API)
				.then(function(response) {
					$localStorage.bookborrowings = response.data;
					deferred.resolve(response);
				},function(errResponse){
					deferred.reject(errResponse);
				}
			);
			return deferred.promise;
		}
		
		function loadAllBookings(){
			var deferred =$q.defer();// Ham khoi tao 
			$http.get(urls.BOOKING_SERVICE_API) 
			.then(function(response){
				$localStorage.bookings =response.data;//dua vao 1 phien luu tru de khi dong trinh duyet hoac da add vao Db van luu data.
				deferred.resolve(response);
			},function(errResponse){
				deferred.reject(errResponse);
			});
				return deferred.promise;// ket qua tra ve loi
		}
		
		function loadAllReaders(){
			var deferred = $q.defer();
			$http.get(urls.READER_SERVICE_API)
			.then(function(response){
				$localStorage.readers = response.data;
				deferred.resolve(response);
			},function(errResponse){
				deferred.reject(errResponse);
			});
			return 	deferred.promise;	
		}
		
		function getAllBookBorrowings() {
			return $localStorage.bookborrowings;
		}
		
		function getAllBookings(){
			return $localStorage.bookings;
		}
		
		function getAllReaders(){
			return $localStorage.readers;
		}
		
		function getBookBorrowing(id) {
			var deferred = $q.defer();
			$http.get(urls.BOOKBORROWING_SERVICE_API + id) //http://localhost:8181/api/bookborrowing/{id}
			.then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}
		
		function createBookBorrowing(bookborrowing) {
			var defferred = $q.defer();
			$http.post(urls.BOOKBORROWING_SERVICE_API, bookborrowing).then(
					function(response) {
						loadAllBookBorrowings();
						defferred.resolve(response.data);
					},function(errResponse){
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function updateBookBorrowing(id,bookborrowing) {
			var defferred = $q.defer();
			$http.put(urls.BOOKBORROWING_SERVICE_API + id, bookborrowing).then(
					function(response) {
						loadAllBookBorrowings();
						defferred.resolve(response.data);
					},
					function(errResponse) {
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function removeBookBorrowing(id) {
			var defferred = $q.defer();
			$http.delete(urls.BOOKBORROWING_SERVICE_API+id).then(
					function(response) {
						loadAllBookBorrowings();
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
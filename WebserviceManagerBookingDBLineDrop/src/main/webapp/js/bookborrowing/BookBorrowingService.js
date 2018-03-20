/**
 * 
 */
'use strict';
//register the interceptor(chan) as a service
angular.module('myApp').factory('BookBorrowingService', [ '$localStorage', '$http', '$q', 'urls', //Đặt tên cho service (BookBorrowingService) và định nghĩa các thư viện cần dùng
	function($localStorage, $http, $q, urls) { 
		var factory = {
			loadAllBookBorrowings : loadAllBookBorrowings,
			loadAllBookings : loadAllBookings,
			loadAllReaders : loadAllReaders,
			getAllBookBorrowings : getAllBookBorrowings,
			getAllBookings : getAllBookings,
			getAllReaders : getAllReaders,
			getBookBorrowing : getBookBorrowing,
			createBookBorrowing : createBookBorrowing,
			updateBookBorrowing : updateBookBorrowing,
			removeBookBorrowing : removeBookBorrowing
		};

		return factory;

		// Tim du lieu tren server de load len form
		function loadAllBookBorrowings() {
			var deferred = $q.defer(); // COnstructer bat dong bo
			// GET request tu URL duoc goi den trong main.js
			$http.get(urls.BOOKBORROWING_SERVICE_API)
				.then(function(response) { // giong try..catch
					// this callback will be called asynchronously
					// when the response is available
					$localStorage.bookborrowings = response.data; //Tra ve data duoc luu trong server. (Luu qua $localStorage vi khi trinh duyet dong hoac trang duoc lam moi thi thi giua cac phien trinh duyet luu nhu vay se khong bi xoa
					deferred.resolve(response);
				}, function(errResponse) {
					// called asynchronously if an error occurs
					// or server returns response with an error status.
					deferred.reject(errResponse);
				}
			);
			return deferred.promise; // tra ve ket qua cua c/v bi loi.
		}

		function loadAllReaders() {
			var deffered = $q.defer(); // ham khoi tao thuc hien bat dong bo(tuc la 1 c/v chua duoc t/h); đại diện cho một nhiệm vụ sẽ kết thúc trong tương lai.
			$http.get(urls.READER_SERVICE_API)
				.then(function(response) {
					$localStorage.readers = response.data;
					deffered.resolve(response);
				}, function(errResponse) {
					deffered.reject(errResponse);
				}
			);
			return deffered.promise();
		}
		
		function loadAllBookings(){
			var deffered =$q.defer();
			$http.get(urls.BOOKING_SERVICE_API)
			.then(function(response){
				$localStorage.bookings = response.data;
				deffered.resolve(response);
			},function(errResponse){
				deffered.reject(errResponse);
			});
			return deffered.promise;
		}

		//Lay du lieu tu server
		function getAllBookBorrowings() {
			return $localStorage.bookborrowings;
		}
		
		function getAllReaders(){
			return $localStorage.readers;
		}
		
		function getAllBookings(){
			return $localStorage.bookings;
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
				}, function(errResponse) {
					defferred.reject(errResponse);
				}
			);
			return defferred.promise;
		}

		function updateBookBorrowing(id, bookborrowing) {
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
			$http.delete(urls.BOOKBORROWING_SERVICE_API + id).then(
				function(response) {
					loadAllBookBorrowings();
					defferred.resolve(response.data);
				},
				function(errResponse) {
					defferred.reject(errResponse);
				}
			);
			return defferred.promise; // bat dong bo
		}
	}
]);
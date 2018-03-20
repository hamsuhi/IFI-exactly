/**
 * 
 */
'use strict';

angular.module('myApp').factory('ReaderService',['$localStorage','$http','$q','urls', //Đặt tên cho service (ReaderService) và định nghĩa các thư viện cần dùng
	function($localStorage,$http,$q,urls){//
		var factory = {
				loadAllReaders: loadAllReaders,
				getAllReaders: getAllReaders,
				getReader: getReader,
				createReader:createReader,
				updateReader:updateReader,
				removeReader:removeReader
		};
		
		return factory;
		
		function loadAllReaders(){
				var deferred = $q.defer();
				$http.get(urls.READER_SERVICE_API)
				.then(function(response) {
					$localStorage.readers = response.data;
					deferred.resolve(response);
				},function(errResponse){
					deferred.reject(errResponse);
				}
			);
			return deferred.promise;
		}
		
		function getAllReaders() {
			return $localStorage.readers;
		}
		
		function getReader(id) {
			var deferred = $q.defer();
			$http.get(urls.READER_SERVICE_API + id) //http://localhost:8181/api/reader/{id}
			.then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}
		
		function createReader(reader) {
			var defferred = $q.defer();
			$http.post(urls.READER_SERVICE_API, reader).then(
					function(response) {
						loadAllReaders();
						defferred.resolve(response.data);
					},function(errResponse){
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function updateReader(id,reader) {
			var defferred = $q.defer();
			$http.put(urls.READER_SERVICE_API + id, reader).then(
					function(response) {
						loadAllReaders();
						defferred.resolve(response.data);
					},
					function(errResponse) {
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function removeReader(id) {
			var defferred = $q.defer();
			$http.delete(urls.READER_SERVICE_API+id).then(
					function(response) {
						loadAllReaders();
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
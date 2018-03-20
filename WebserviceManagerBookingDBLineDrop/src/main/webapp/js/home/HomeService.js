/**
 * 
 */
'use strict';

angular.module('myApp').factory('PersonalService',['$localStorage','$http','$q','urls', //Đặt tên cho service (PersonalService) và định nghĩa các thư viện cần dùng
	function($localStorage,$http,$q,urls){//
		var factory = {
				loadAllPersonals: loadAllPersonals,
				getAllPersonals: getAllPersonals,
				getPersonal: getPersonal,
				createPersonal:createPersonal,
				updatePersonal:updatePersonal,
				removePersonal:removePersonal
		};
		
		return factory;
		
		function loadAllPersonals(){
				var deferred = $q.defer();
				$http.get(urls.PERSONAL_SERVICE_API)
				.then(function(response) {
					$localStorage.personals = response.data;
					deferred.resolve(response);
				},function(errResponse){
					deferred.reject(errResponse);
				}
			);
			return deferred.promise;
		}
		
		function getAllPersonals() {
			return $localStorage.personals;
		}
		
		function getPersonal(id) {
			var deferred = $q.defer();
			$http.get(urls.PERSONAL_SERVICE_API + id) //http://localhost:8181/api/personal/{id}
			.then(function(response) {
				deferred.resolve(response.data);
			}, function(errResponse) {
				deferred.reject(errResponse);
			});
			return deferred.promise;
		}
		
		function createPersonal(personal) {
			var defferred = $q.defer();
			$http.post(urls.PERSONAL_SERVICE_API, personal).then(
					function(response) {
						loadAllPersonals();
						defferred.resolve(response.data);
					},function(errResponse){
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function updatePersonal(id,personal) {
			var defferred = $q.defer();
			$http.put(urls.PERSONAL_SERVICE_API + id, personal).then(
					function(response) {
						loadAllPersonals();
						defferred.resolve(response.data);
					},
					function(errResponse) {
						defferred.reject(errResponse);
					}
			);
			return defferred.promise;
		}
		
		function removePersonal(id) {
			var defferred = $q.defer();
			$http.delete(urls.PERSONAL_SERVICE_API+id).then(
					function(response) {
						loadAllPersonals();
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
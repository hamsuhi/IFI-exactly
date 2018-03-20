/**
 * 
 */
'use strict';
angular.module('myApp').controller('PersonalController', [ 'PersonalService', '$scope', function(PersonalService, $scope) {
	var self = this;
	self.personal = {};//1 OBJ
	self.personals = [];

	self.submit = submit;
	self.getAllPersonals = getAllPersonals;
	self.createPersonal = createPersonal;
	self.updatePersonal = updatePersonal;
	self.removePersonal = removePersonal;
	self.personalToSubmit = personalToSubmit;
	self.reset = reset;


	self.successMessage = '';
	self.errorMessage = '';
	self.done = false;



	function submit() {
		if (self.personal.id === undefined || self.personal.id === null) {
			createPersonal(self.personal);
		} else {
			updatePersonal(self.personal.id, self.personal);
		}
	}

	function getAllPersonals() {
		return PersonalService.getAllPersonals();
	}

	function createPersonal(personal) {
		PersonalService.createPersonal(personal).then(
			function(response) {
				self.successMessage = 'Personal created successfully';
				self.errorMessage = '';
				self.done = true;
				self.personal = {};
				$scope.personalForm.$setPristine();
			}, function(errResponse) {
				self.errorMessage = 'Error while createing Personal: ' + errResponse;
				self.successMessage = '';
			}
		);

	}

	function updatePersonal(id, personal) {
		PersonalService.updatePersonal(id, personal).then(
			function(response) {
				self.successMessage = 'Personal updated successfully';
				self.errorMessage = '';
				self.done = true;
				$scope.personalForm.$setPristine();
			}, function(errResponse) {
				self.errorMessage = 'Error while updateing Personal: ' + errResponse;
				self.successMessage = '';
			}
		);
	}
	function removePersonal(id) {
		PersonalService.removePersonal(id).then(
			function(response) {
				self.successMessage = 'Personal deleted successfully';
				self.errorMessage = '';
				self.done = true;
			}
		);

	}


	function personalToSubmit(id) {
		self.successMessage = '';
		self.errorMessage = '';
		PersonalService.getPersonal(id).then(
			function(personal) {
				self.personal = personal;
				self.personal.dob = new Date(self.personal.dob);
			}
		);
	}
	function reset() {
		self.successMessage = '';
		self.errorMessage = '';
		self.personal = {};
		$scope.personalForm.$setPristine();
	}
} ])
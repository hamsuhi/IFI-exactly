/**
 * 
 */
'use strict';
angular.module('myApp').controller('ReaderController', [ 'ReaderService', '$scope', function(ReaderService, $scope) {
	var self = this;
	self.reader = {};//1 OBJ
	self.readers = [];

	self.submit = submit;
	self.getAllReaders = getAllReaders;
	self.createReader = createReader;
	self.updateReader = updateReader;
	self.removeReader = removeReader;
	self.readerToSubmit = readerToSubmit;
	self.reset = reset;


	self.successMessage = '';
	self.errorMessage = '';
	self.done = false;



	function submit() {
		if (self.reader.numberCard === undefined || self.reader.numberCard === null) {
			createReader(self.reader);
		} else {
			updateReader(self.reader.numberCard, self.reader);
		}
	}

	function getAllReaders() {
		return ReaderService.getAllReaders();
	}

	function createReader(reader) {
		ReaderService.createReader(reader).then(
			function(response) {
				self.successMessage = 'Reader created successfully';
				self.errorMessage = '';
				self.done = true;
				self.reader = {};
				$scope.readerForm.$setPristine();
			}, function(errResponse) {
				self.errorMessage = 'Error while createing Reader: ' + errResponse;
				self.successMessage = '';
			}
		);

	}

	function updateReader(id, reader) {
		ReaderService.updateReader(id, reader).then(
			function(response) {
				self.successMessage = 'Reader updated successfully';
				self.errorMessage = '';
				self.done = true;
				$scope.readerForm.$setPristine();
			}, function(errResponse) {
				self.errorMessage = 'Error while updateing Reader: ' + errResponse;
				self.successMessage = '';
			}
		);
	}
	function removeReader(id) {
		ReaderService.removeReader(id).then(
			function(response) {
				self.successMessage = 'Reader deleted successfully';
				self.errorMessage = '';
				self.done = true;
			}
		);

	}

	function readerToSubmit(id) {
		self.successMessage = '';
		self.errorMessage = '';
		ReaderService.getReader(id).then(
			function(reader) {
				self.reader = reader;
				self.reader.dateOfBirth = new Date(self.reader.dateOfBirth);
			}
		);
	}
	function reset() {
		self.successMessage = '';
		self.errorMessage = '';
		self.reader = {};
		$scope.readerForm.$setPristine();
	}
} ])
var app = angular.module('myApp', [ 'ui.router', 'ngStorage' ]);

app.constant('urls', {
	BASE : 'http://localhost:8083',
	READER_SERVICE_API : 'http://localhost:8083/api/reader/',
	BOOKING_SERVICE_API : 'http://localhost:8083/api/booking/',
	BOOKBORROWING_SERVICE_API : 'http://localhost:8083/api/bookborrowing/'
});

app.config(['$stateProvider','$urlRouterProvider',
	function($stateProvider,  $urlRouterProvider) {

		//Khai bao OBJ
		var homeState = {
			name : 'home',
			url : '/',
			templateUrl : '/js/home/home.view.jsp'
		};

		var readerState = {
			name : 'reader',
			url : '/reader',
			templateUrl : '/js/reader/reader.view.jsp',
			controller : 'ReaderController',
			controllerAs : 'ctrl',
					resolve: {
						readers: function ($q, ReaderService) {
							console.log('Load all reader');
							var deferred = $q.defer();
							ReaderService.loadAllReaders().then(deferred.resolve, deferred.resolve);
							return deferred.promise;
						}
					}
		};

		var bookingState = {
			name : 'booking',
			url : '/booking',
			templateUrl : '/js/booking/booking.view.jsp',
			controller : 'BookingController',
			controllerAs : 'ctrl',
			resolve : {
				bookings : function($q, BookingService) {
					console.log('Load all booking');
					var deferred = $q.defer(); //reset lai. Tao moi 1 obj 
					BookingService.loadAllBookings().then(deferred.resolve, deferred.resolve);
					return deferred.promise;
				}
			}
		};

		var bookborrowingState = {
			name : 'bookborrowing',
			url : '/bookborrowing',
			templateUrl : '/js/bookborrowing/bookborrowing.view.jsp'
		};

		//dang ki OBJ
		$stateProvider.state(homeState);
		$stateProvider.state(readerState);
		$stateProvider.state(bookingState);
		$stateProvider.state(bookborrowingState);

		$urlRouterProvider.otherwise('/');

	}]);
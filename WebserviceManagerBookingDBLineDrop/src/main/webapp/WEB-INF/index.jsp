<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<!-- Goi toi trong main.js -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!--  -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="footer, address, phone, icons" />

<title>Manager Booking</title>

<link rel="stylesheet" href="/css/bootstrap.min.css" />

<!-- Library footer -->
<link href="/css/font.css" rel="stylesheet" type="text/css">
<link href="/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
<link href="/css/footer-distributed-with-address-and-phones.css"
	type="text/css" rel="stylesheet" />

<!-- End library footer -->
<link rel="stylesheet" href="/css/main.css" />

<script type="text/javascript" src="/js/lib/angular.min.js"></script>
<script type="text/javascript" src="/js/lib/angular-ui-router.js"></script>
<script type="text/javascript" src="/js/lib/localforage.min.js"></script>
<script type="text/javascript" src="/js/lib/ngStorage.min.js"></script>

<script type="text/javascript" src="/js/main.js"></script>

<script type="text/javascript" src="/js/reader/ReaderService.js"></script>
<script type="text/javascript" src="/js/reader/ReaderController.js"></script>

<script type="text/javascript" src="/js/booking/BookingService.js"></script>
<script type="text/javascript" src="/js/booking/BookingController.js"></script>

<script type="text/javascript" src="/js/bookborrowing/BookBorrowingService.js"></script>
<script type="text/javascript" src="/js/bookborrowing/BookBorrowingController.js"></script>

</head>

<body>
	<!-- Show menubar -->
	<div class="navbar">
		<a ui-sref="home" ui-sref-active="active">Home</a> <a ui-sref="reader"
			ui-sref-active="active">Reader</a> <a ui-sref="booking"
			ui-sref-active="active">Booking</a> <a ui-sref="bookborrowing"
			ui-sref-active="active">Book Borrowing</a>
	</div>



	<!-- Container all template for user view -->
	<div class="main">
		<ui-view></ui-view>
	</div>

	<%@include file="footer.jsp"%>

</body>
</html>
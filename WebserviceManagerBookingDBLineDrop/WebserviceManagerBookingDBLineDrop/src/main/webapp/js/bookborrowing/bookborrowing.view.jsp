<div class="container">

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">Infomation about BookBorrowing</span>
		</div>
		<div class="panel-body">
			<div class="formcontainer">
				<div class="alert alert-success" role="alert"
					ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
				<div class="alert alert-danger" role="alert"
					ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
				<form ng-submit="ctrl.submit()" name="bookborrowingForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.bookborrowing.bookBorrowingId" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="uname">Date
								Borrowing</label>
							<div class="col-md-7">
								<input type="date" ng-model="ctrl.bookborrowing.dateBorrowing"
									id="uname" class="username form-control input-sm"
									placeholder="Enter your dateBorrowing" required
									ng-minlength="3" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="age">Date Pay</label>
							<div class="col-md-7">
								<input type="date" ng-model="ctrl.bookborrowing.datePay"
									id="dob" class="form-control input-sm" required />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Select Booking</label>
							<div class="col-md-7">
								<select class="form-control"
									ng-model="ctrl.bookborrowing.booking.bookingId" required>
									<option value="">Choose booking ...</option>
									<option ng-repeat="p in ctrl.getAllBookings()"
										value="{{p.bookingId}}"
										ng-selected="ctrl.bookborrowing.booking.bookingId == p.bookingId">{{p.bookName}}</option>
									<!--Lay ra click id trung trong db -->
								</select>
							</div>
							<div class="input-group-append">
								<button type="button" class="btn btn-primary" disabled>Options</button>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable">Select Reader</label>
							<div class="col-md-7">
								<select class="form-control"
									ng-model="ctrl.bookborrowing.reader.numberCard"
									required="required">
									<option value="">Choose reader ...</option>
									<option ng-repeat="p in ctrl.getAllReaders()"
										value="{{p.numberCard}}"
										ng-selected="ctrl.bookborrowing.reader.numberCard == p.numberCard">{{p.fullName}}</option>
								</select>
							</div>
							<div class="input-group-append">
								<button type="button" class="btn btn-primary" disabled>Options</button>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.bookborrowing.bookBorrowingId ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm"
								ng-disabled="bookborrowingForm.$invalid || bookborrowingForm.$pristine">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm"
								ng-disabled="bookborrowingForm.$pristine">Reset Form</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<hr />
	{{ctrl.bookborrowing.datePay}}
	<h2>List of Person</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>Date Borrowing</th>
				<th>Date Pay</th>
				<th>Booking</th>
				<th>Reader</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="p in ctrl.getAllBookBorrowings()">
				<td>{{p.bookBorrowingId}}</td>
				<td>{{p.dateBorrowing | date: "dd/MM/yyyy"}}</td>
				<td>{{p.datePay | date: "dd/MM/yyyy"}}</td>
				<td>{{p.booking.bookingId}}</td>
				<td>{{p.reader.numberCard}}</td>

				<td><button type="button"
						ng-click="ctrl.bookborrowingToSubmit(p.bookBorrowingId)"
						class="btn btn-success custom-width">Edit</button></td>
				<td><button type="button"
						ng-click="ctrl.removeBookBorrowing(p.bookBorrowingId)"
						class="btn btn-danger custom-width">Remove</button></td>

			</tr>
		</tbody>
	</table>
</div>
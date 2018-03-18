<div class="container">

	<div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Specific Personal </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="bookingForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.booking.bookingId" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="uname">Number book</label>
                            <div class="col-md-7">
                   <!--  -->             <input type="text" ng-model="ctrl.booking.bookingCol" id="uname" class="username form-control input-sm" placeholder="Enter your number book" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="name">Book name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.booking.bookName" id="dob" class="form-control input-sm" placeholder="Enter your book name" required/>
                            </div>
                        </div>
                    </div>
     
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="date">Date Import</label>
                            <div class="col-md-7">
                                <input type="date" ng-model="ctrl.booking.dateImport" id="date" class="form-control input-sm"  required/>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="publish">Publisher</label>
                            <div class="col-md-7">
                                <input type="type" ng-model="ctrl.booking.publisher" id="publisher" class="form-control input-sm"  required/>
                            </div>
                        </div>
                    </div>
                    
 
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.booking.bookingId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="bookingForm.$invalid || bookingForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="bookingForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>    
    </div>
<hr/>
{{ctrl.booking.bookingId}}
	<h2>List of Booking</h2>
	
	
	<label class="col-md-2 control-lable" for="publish">Search </label>
	<div class="col-md-7">
		<input type="type" ng-model="findAll" id="publisher"
			class="form-control input-sm" required />
	</div>


	<table class="table table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>Number Book</th>
				<th>Name book</th>
				<th>Date Import</th>
				<th>Publisher</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="p in ctrl.getAllBookings()  | filter:findAll">
				<td>{{p.bookingId}}</td>
				<td>{{p.bookingCol}}</td>
				<td>{{p.bookName}}</td>
				<td>{{p.dateImport | date: "dd/MM/yyyy"}}</td>
				<td>{{p.publisher}}</td>
				
				<td><button type="button" ng-click="ctrl.bookingToSubmit(p.bookingId)"
						class="btn btn-success custom-width">Edit</button></td>
				<td><button type="button" ng-click="ctrl.removeBooking(p.bookingId)"
						class="btn btn-danger custom-width">Remove</button></td>

			</tr>
		</tbody>
		<!-- Begin vung footer -->
	
	<!-- End vung footer -->
	</table>
</div>	
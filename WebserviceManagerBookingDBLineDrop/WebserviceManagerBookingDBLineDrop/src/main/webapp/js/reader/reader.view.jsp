<div class="container">
	<div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Specific Reader </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="readerForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.reader.numberCard" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="uname">Address</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.reader.address" id="uname" class="username form-control input-sm" placeholder="Enter your address" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="age">Date of Birth</label>
                            <div class="col-md-7">
                                <input type="date" ng-model="ctrl.reader.dateOfBirth" id="dob" class="form-control input-sm"  required/>
                            </div>
                        </div>
                    </div>
     
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="name">Full Name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.reader.fullName" id="name" class="form-control input-sm" placeholder="Enter your Fullname" required/>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="gender">Gender</label>
                            <div class="col-md-7">
                                <input type="radio" ng-model="ctrl.reader.gender" id="gender"   value="Male"/>Male
                                <input type="radio" ng-model="ctrl.reader.gender" id="gender"   value="Female"/>Female
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="phone">Phone</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.reader.tel" id="phone" class="form-control input-sm" placeholder="Enter your phone number" required/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.reader.numberCard ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="readerForm.$invalid || readerForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="readerForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>    
    </div>
<hr/>
	<h2>List of Person</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>Address</th>
				<th>Date Of Birth</th>
				<th>Fullname</th>
				<th>Gender</th>
				<th>Phone</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="p in ctrl.getAllReaders()">
				<td>{{p.numberCard}}</td>
				<td>{{p.address}}</td>
				<td>{{p.dateOfBirth | date: "dd/MM/yyyy"}}</td>
				<td>{{p.fullName}}</td>
				<td>{{p.gender}}</td>
				<td>{{p.tel}}</td>
				<td><button type="button" ng-click="ctrl.readerToSubmit(p.numberCard)"
						class="btn btn-success custom-width">Edit</button></td>
				<td><button type="button" ng-click="ctrl.removeReader(p.numberCard)"
						class="btn btn-danger custom-width">Remove</button></td>

			</tr>
		</tbody>
	</table>
</div>
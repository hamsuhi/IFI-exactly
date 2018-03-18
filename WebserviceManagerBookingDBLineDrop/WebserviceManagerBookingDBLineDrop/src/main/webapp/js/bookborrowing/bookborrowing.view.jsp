<div class="container">

	<div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Specific Personal </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="personalForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.personal.id" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="uname">Address</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.personal.address" id="uname" class="username form-control input-sm" placeholder="Enter your address" required ng-minlength="3"/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="age">Date of Birth</label>
                            <div class="col-md-7">
                                <input type="date" ng-model="ctrl.personal.dob" id="dob" class="form-control input-sm"  required/>
                            </div>
                        </div>
                    </div>
     
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="name">Fullname</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.personal.fullname" id="name" class="form-control input-sm" placeholder="Enter your Fullname" required/>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="gender">Gender</label>
                            <div class="col-md-7">
                                <input type="radio" ng-model="ctrl.personal.gender" id="gender"   value="Male"/>Male
                                <input type="radio" ng-model="ctrl.personal.gender" id="gender"   value="Female"/>Female
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="phone">Phone</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.personal.phone" id="phone" class="form-control input-sm" placeholder="Enter your phone number" required/>
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.personal.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="personalForm.$invalid || personalForm.$pristine">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="personalForm.$pristine">Reset Form</button>
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
			<tr ng-repeat="p in ctrl.getAllPersonals()">
				<td>{{p.id}}</td>
				<td>{{p.address}}</td>
				<td>{{p.dob | date: "dd/MM/yyyy"}}</td>
				<td>{{p.fullname}}</td>
				<td>{{p.gender}}</td>
				<td>{{p.phone}}</td>
				<td><button type="button" ng-click="ctrl.personalToSubmit(p.id)"
						class="btn btn-success custom-width">Edit</button></td>
				<td><button type="button" ng-click="ctrl.removePersonal(p.id)"
						class="btn btn-danger custom-width">Remove</button></td>

			</tr>
		</tbody>
	</table>
</div>
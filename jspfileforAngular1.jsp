<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 

<!DOCTYPE html>
<html ng-app="myApp">
<head>
	
	<meta charset="UTF-8">
	<title>Angular Integration with servlet</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	
	<style type="text/css">
		input{margin-top:20px;margin-left: 10px;}
		#getDataBtn{margin-left: 210px;}
	</style>
	
	<script type="text/javascript">
		var app=angular.module('myApp', []);
		app.controller('myCtrl',['$scope','$window','$http',function($scope,$window,$http){  // $scope , $window , $http
			$scope.dataArrayList=function(){
				$http({
					url:"http://localhost:8080/AngularFirst/AngularJsServlet1",
					method:"GET",
					params:{
						"startRange":$scope.startRange,
						"endRange":$scope.endRange
					}
				}).then(function(response){
					$scope.JSONData=response.data.myArrayList;
					$scope.statusCode=response.status;
					$window.alert(JSONData);
				},function(response){
					$window.alert("Connection Failed");
				});
			}
		}]); 
	</script>

</head>
<body ng-controller="myCtrl">

		<input type="text" ng-model="startRange"/>
		<input type="text" ng-model="endRange"/><br><br>
		<button id="getDataBtn" ng-click="dataArrayList()">Get Data</button>

		<table border="2">
			<tr>
				<th>ReadDateAndTime</th>
				<th>RamUsed</th>
				<th>DiskUsed</th>
				<th>CpuUsed</th>
			</tr>
			<tr ng-repeat="objData in JSONData">
				<td>{{objData.map.readDateAndTime}}</td>
				<td>{{objData.map.ramUsed}}</td>
				<td>{{objData.map.diskUsed}}</td>
				<td>{{objData.map.cpuUsed}}</td>
			</tr>
		</table>

</body>
</html>


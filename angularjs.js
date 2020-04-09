
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
  $http({
    method: "GET",
    url: "http://localhost:8080/AngularFirst/AngularJsServlet"
    //http://localhost:8080/AngularFirst/AngularJsServlet
  }).then(function mySuccess(response) {
      // a string, or an object, carrying the response from the server.
      $scope.myRes = response.data.myArrayList;
      $scope.statuscode = response.status;
    }, function myError(response) {
      $scope.myRes = response.myArrayList;
  });
});

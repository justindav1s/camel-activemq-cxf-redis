'use strict';

/* Controllers */

var sellingApp = angular.module('sellingApp', ['sellingApp.services']);

sellingApp.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);

sellingApp.controller('PersonListCtrl', ['$scope', '$http', 'ListPeople', function ($scope, $http, ListPeople) {
    
    //$http.get('http://localhost:8080/selling/person/list').success(function(data) {
	//	console.log("Success", data);
	//	$scope.people = data;
    //})
    //.error(function() {
	//	console.log("error");
	//});
	
	ListPeople.query(function(response) {
      // Assign the response INSIDE the callback
      	console.log("Success", response);
		$scope.people = response;
    });

	$scope.orderProp = 'personID';
  }]);

sellingApp.controller('NewPersonFormCtrl', ['$scope', '$http', 'ListPeople', function ($scope, $http, ListPeople) {
	$scope.newperson = {};
	
	$scope.update = function(person) {
		console.log("update");
		console.log("person :" + angular.toJson(person, false));
		$scope.newperson = angular.copy(person);
	};

	$scope.reset = function() {
		console.log("reset");
		$scope.person = {};
	};

	$scope.reset();

}]);
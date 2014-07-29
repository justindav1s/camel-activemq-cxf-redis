'use strict';

/* Controllers */

var sellingApp = angular.module('sellingApp', ['sellingApp.services']);

sellingApp.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);

sellingApp.controller('PersonListCtrl', ['$scope', 'PersonResource', function ($scope, PersonResource) {
   
   	PersonResource.list( function(response) {
      // Assign the response INSIDE the callback
      	console.log("Success", response);
		$scope.people = response;
    });
     	
	PersonResource.read({pid:'3691'}, function(response) {
      // Assign the response INSIDE the callback
      	console.log("Success", response);
		$scope.sperson = response;
    });

	$scope.orderProp = 'personID';
  }]);

sellingApp.controller('NewPersonFormCtrl', ['$scope', 'PersonResource', function ($scope, PersonResource) {
	$scope.newperson = {};
	
	$scope.update = function(person) {
		console.log("update");
		console.log("person :" + angular.toJson(person, false));
		$scope.newperson = angular.copy(person);
			
		PersonResource.create($scope.newperson, function(response) {
      		console.log("Success", response);
			$scope.sperson = response;
			$scope.people.push(response);
    	});
	};

	$scope.reset = function() {
		console.log("reset");
		$scope.person = {};
	};

	$scope.reset();

}]);
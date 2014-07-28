'use strict';

/* Services */

var sellingAppServices = angular.module('sellingApp.services', ['ngResource'])
  .factory('ListPeople', function($resource){
    return $resource('http://localhost:8080/selling/s/person/list', {})
  })
  .value('version', '0.1');
  
sellingAppServices.config(['$resourceProvider', function ($resourceProvider) {

}]);
  


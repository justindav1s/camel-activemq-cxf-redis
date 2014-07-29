'use strict';

/* Services */

var sellingAppServices = angular.module('sellingApp.services', ['ngResource']);
          
//var sellingAppServices = angular.module('sellingApp.services', ['ngResource'])
//  .factory('ListPeople', function($resource){
//    return $resource('http://localhost:8080/selling/s/person/list', {})
//  })
//  .value('version', '0.1');

   sellingAppServices.factory('PersonResource', ['$resource', function($resource) {
   return $resource('http://localhost:8080/selling/s/person/:verb;pid=:pid', 
   null,
	{
		'create': {method:'POST', params:{verb:'create'}},
		'read': {method:'GET', params:{verb:'read', pid:'@id'}},
		'list': {method:'GET', params:{verb:'list'}, isArray:true}
       });
   }]);
  
//var Person = $resource('http://localhost:8080/selling/s/person/:verb');
  
  


define(['angular',"angularResource"],function(angular){
  'use strict';

	var mainSrv = angular.module('mainSrv',['ngResource']);

	 // mainSrv.factory('hdSrv',['$resource', function ($resource){
	 // 	return $resource('/stw/:entity/:method/:id/:param1/:param2',null,
	 // 			{
	 //         'put': { method:'PUT' ,params: {entity : '@entity', method:'@method'} },
  //          'sendFile': { method:'POST' ,params: {entity : '@entity', method:'@method'},
  //                       transformRequest: angular.identity,
  //                       headers: {'Content-Type':undefined} }
	 // 			});
	 // }]);

	 mainSrv.factory('hdSrv',['$resource', function ($resource){
	 	return $resource('http://192.168.1.36:8080/stw/:entity/:method/:id/:param1/:param2',null,
	 			{
	          'put': { method:'PUT' ,params: {entity : '@entity', method:'@method'} },
           'sendFile': { method:'POST' ,params: {entity : '@entity', method:'@method'},
                          transformRequest: angular.identity,
                          headers: {'Content-Type':undefined} }
	 			});
	 }]);

	return mainSrv;
});

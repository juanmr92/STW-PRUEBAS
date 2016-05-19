define(['services/mainSrv'], function(mainSrv){
  'use strict';

//	mainSrv.factory('loginSrv',['$resource', function ($resource){
//		return $resource('http://systemlab.com.pe/sca/login');
//	}]);

   // mainSrv.factory('loginSrv',['$resource', function ($resource){
	  //  return $resource('/stw/login');
   // }]);

	mainSrv.factory('loginSrv',['$resource', function ($resource){
		return $resource('http://localhost:8080/stw/login');
	}]);

//	 mainSrv.factory('loginSrv',['$resource', function ($resource){
//	 	return $resource('http://192.168.1.36:8080/help-desk/login');
//	 }]);

});


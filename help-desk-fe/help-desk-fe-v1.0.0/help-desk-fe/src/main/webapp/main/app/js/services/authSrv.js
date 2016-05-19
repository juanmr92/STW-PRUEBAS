define(['services/mainSrv'], function(mainSrv){
  'use strict';
	mainSrv.factory('authSrv',['$location',function ($location){
		return {
			auth : function (){
				if(!localStorage.getItem('userRoleId')){
					$location.path('/login');
				}
			}
		};
	}]);

});

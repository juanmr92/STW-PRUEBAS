define(['angular','services/mainSrv','directives/mainDtv','filters/mainFtr','bootstrap'],function(angular){
  'use strict';
	var mainCtr = angular.module('mainCtr',['mainSrv','mainDtv','mainFtr']);
	return mainCtr;
});

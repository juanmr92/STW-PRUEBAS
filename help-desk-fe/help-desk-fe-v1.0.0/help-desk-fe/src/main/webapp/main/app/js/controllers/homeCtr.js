define(['controllers/mainCtr'], function(mainCtr){
  'use strict';

  mainCtr.controller('homeCtr',['$scope','$location',function ($scope,$location){

    $scope.sca_container = {'background' : '#F8F9F4'};

    $scope.logout = function(){
      localStorage.clear();
      $location.path('/login');
    };

  }]);

});

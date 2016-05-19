define(['controllers/mainCtr','filters/hdFtr'], function(mainCtr){
  'use strict';

  mainCtr.controller('detailRequestCtr',['$scope','hdSrv',function ($scope,hdSrv){

    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.request = JSON.parse(sessionStorage.getItem('request'));

  }]);

});

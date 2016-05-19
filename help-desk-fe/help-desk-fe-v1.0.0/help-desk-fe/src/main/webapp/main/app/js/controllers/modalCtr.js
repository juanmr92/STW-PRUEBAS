define(['controllers/mainCtr'], function(mainCtr){
  'use strict';

  mainCtr.controller('modalCtr',['$scope','$modalInstance',function($scope,$modalInstance){

    $scope.closeModal = function () {
      $modalInstance.dismiss('cancel');
    };

    $scope.ok = function () {
      $modalInstance.close();
    };


  }]);

});

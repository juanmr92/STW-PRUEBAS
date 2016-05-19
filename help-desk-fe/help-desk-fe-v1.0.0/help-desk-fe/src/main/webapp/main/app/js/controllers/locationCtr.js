define(['controllers/mainCtr','controllers/modalCtr','bootstrap'], function(mainCtr){
  'use strict';

  mainCtr.controller('locationCtr',['$scope','$modal','hdSrv',function ($scope,$modal,hdSrv){

    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.loading = false;
    $scope.step = 1;
    $scope.locationList = [];
    $scope.option = '';
    var userRoleId = 1;

    $scope.getLocationList = function() {
      $scope.loading = true;
      var request = {};

      hdSrv.save({entity:'location',method:'getList'},request,function(data){
        switch (data.status) {
          case 200:
            $scope.locationList = data.dtoList;
            break;
          case 202:
            $scope.messageInfo = 'No existe pabellones registrados.';
            break;
          default:
            showErrorMsg();
            break;
        }
      $scope.loading = false;
      }, function(error){
        showErrorMsg(error);
      });
    };

    $scope.getLocationList();

    $scope.saveLocation = function(location) {
      cleanMsg();
      $scope.loading = true;
      location.userRoleId = userRoleId;

      if($scope.option === 'add') {
        hdSrv.save({entity:'location'},location,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Pabellón registrado satisfactoriamente.';
            $scope._location = {};
            $scope.getLocationList();
          }else{
            showErrorMsg(data.status);
          }
          $scope.getLocationList();
        }, function(error){
          showErrorMsg(error);
        });

      }else if($scope.option === 'edit') {
        hdSrv.put({entity:'location'},location,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Pabellón registrado satisfactoriamente.';
            $scope.getLocationList();
          }else{
            showErrorMsg(data.status);
          }
          $scope.getLocationList();
        }, function(error){
          showErrorMsg(error);
        });
      }
    };

    $scope.goAdd = function() {
      cleanMsg();
      $scope.option = 'add';
      $scope.step = 2;
    };

    $scope.goEdit = function(location) {
      cleanMsg();
      $scope.option = 'edit';
      $scope._location = location;
      $scope.step = 2;
    };

    $scope.goDelete = function(location) {
      cleanMsg();
      var modalInstance = $modal.open({
        template : '<div class="modal-header"><h3>Confirmar eliminación</h3></div>'+
              '<div class="modal-body"><p>¿Desea eliminar al usuario ' + location.name + '?</p></div>'+
              '<div class="modal-footer"><button class="btn btn-success" ng-click="ok()">Aceptar</button>' +
              '<button class="btn btn-danger" ng-click="closeModal()">Cancelar</button></div>',
        controller : 'modalCtr'
      });
      modalInstance.result.then(function () {
        $scope.loading = true;
        var request = { id:location.id, update_date:location.update_date, userRoleId: userRoleId};
        hdSrv.put({entity:'location',method:'desactivate'},request,function(data){
          if (data.status === 200) {
            $scope.messageSuccess = 'Pabellón eliminado satisfactoriamente';
            $scope.getLocationList();
          }else{
            showErrorMsg(data.status);
          }
        $scope.loading = false;
        }, function(error){
          showErrorMsg(error);
        });
      }, function () {});

    };

    $scope.goBack = function() {
      $scope.step = 1;
      $scope.user = {};
      cleanMsg();
    };

    function showErrorMsg(error){
      $scope.loading = false;
      $scope.messageError = 'Ocurrió un error, recargue la página.';
      console.log('error -> ',error);
    }

    function cleanMsg(){
      $scope.messageInfo = null;
      $scope.messageSuccess = null;
      $scope.messageError = null;
    }

  }]);

});

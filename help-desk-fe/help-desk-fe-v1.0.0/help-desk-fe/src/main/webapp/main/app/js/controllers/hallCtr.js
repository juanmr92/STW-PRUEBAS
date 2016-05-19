define(['controllers/mainCtr','controllers/modalCtr','filters/hdFtr','bootstrap'], function(mainCtr){
  'use strict';

  mainCtr.controller('hallCtr',['$scope','$modal','hdSrv',function ($scope,$modal,hdSrv){

    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.loading = false;
    $scope.step = 1;
    $scope.hallList = [];
    $scope.locationList = [];
    $scope.option = '';
    var userRoleId = 1;

    $scope.getHallList = function() {
      $scope.loading = true;
      var request = {
        "aliases":{
          "hall.location":"location"
        },
        "projections":{
         "id": "id",
         "active": "active",
         "register_date": "register_date",
         "update_date": "update_date",
         "userRoleId": "userRoleId",
         "name": "name",
         "provider": "provider",
         "location.id": "location.id",
         "location.name": "location.name"
        }
      };

      hdSrv.save({entity:'hall',method:'getList'},request,function(data){
        switch (data.status) {
          case 200:
            $scope.hallList = data.dtoList;
            break;
          case 202:
            $scope.messageInfo = 'No existe Ambientes registrados.';
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

    $scope.getHallList();

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

    $scope.saveHall = function(hall) {
      cleanMsg();
      $scope.loading = true;
      hall.userRoleId = userRoleId;

      if($scope.option === 'add') {
        hdSrv.save({entity:'hall'},hall,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Ambiente registrado satisfactoriamente';
            $scope.hall = {};
            $scope.getHallList();
          }else{
            showErrorMsg(data.status);
          }
          $scope.getHallList();
        }, function(error){
          showErrorMsg(error);
        });

      }else if($scope.option === 'edit'){
        hdSrv.put({entity:'hall'},hall,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Ambiente editado satisfactoriamente';
            $scope.getHallList();
          }else{
            showErrorMsg(data.status);
          }
          $scope.getHallList();
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

    $scope.goEdit = function(hall) {
      cleanMsg();
      $scope.option = 'edit';
      $scope.hall = hall;
      $scope.step = 2;
    };

    $scope.goDelete = function(hall) {
      cleanMsg();
      var modalInstance = $modal.open({
        template : '<div class="modal-header"><h3>Confirmar eliminación</h3></div>'+
              '<div class="modal-body"><p>¿Desea eliminar el ambiente ' + hall.name + ' </p></div>'+
              '<div class="modal-footer"><button class="btn btn-success" ng-click="ok()">Aceptar</button>' +
              '<button class="btn btn-danger" ng-click="closeModal()">Cancelar</button></div>',
        controller : 'modalCtr'
      });
      modalInstance.result.then(function () {
        $scope.loading = true;
        var request = { id:hall.id, update_date:hall.update_date, userRoleId: userRoleId};
        hdSrv.put({entity:'hall',method:'desactivate'},request,function(data){
          if (data.status === 200) {
            $scope.messageSuccess = 'Dispositivo eliminado satisfactoriamente';
            $scope.hallList();
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
      $scope.hall = {};
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

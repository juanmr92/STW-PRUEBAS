define(['controllers/mainCtr','controllers/modalCtr','bootstrap'], function(mainCtr){
  'use strict';

  mainCtr.controller('deviceCtr',['$scope','$modal','hdSrv',function ($scope,$modal,hdSrv){

    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.loading = false;
    $scope.step = 1;
    $scope.deviceList = [];
    $scope.locationList = [];
    $scope.hallList = [];
    $scope.option = '';
    var userRoleId = 1;

    $scope.getDeviceList = function() {
      $scope.loadingDevices = true;
      var request = {
        "aliases":{
         "device.hall":"hall",
         "hall.location":"location"
        },
       "projections":{
         "id": "id",
         "active": "active",
         "register_date": "register_date",
         "update_date": "update_date",
         "userRoleId": "userRoleId",
         "code": "code",
         "name": "name",
         "description": "description",
         "hall.id": "hall.id",
         "hall.name": "hall.name",
         "location.id":"hall.location.id",
         "location.name":"hall.location.name"
        }
      };

      hdSrv.save({entity:'device',method:'getList'},request,function(data){
        switch (data.status) {
          case 200:
            $scope.deviceList = data.dtoList;
            break;
          case 202:
            $scope.messageInfo = 'No existe dispositivos registrados.';
            break;
          default:
            showErrorMsg();
            break;
        }
      $scope.loadingDevices = false;
      }, function(error){
        showErrorMsg(error);
      });
    };

    $scope.getDeviceList();

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

    $scope.getHallList = function(locationId) {
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
         "location.id": "location.id"
         },
        "restrictions":[
          {
           "type": "conjuntion",
           "subType": "eq",
           "propertyType": "integer",
           "propertyName": "location.id",
           "value": locationId
          }
        ]
      };

      hdSrv.save({entity:'hall',method:'getList'},request,function(data){
        switch (data.status) {
          case 200:
            $scope.hallList = data.dtoList;
            break;
          case 202:
            $scope.messageInfo = 'No existe ambientes registrados.';
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

    $scope.changeLocation = function(locationId) {
      $scope.hallList = [];
      if(locationId){
        $scope.getHallList(locationId);
      }
    };

    $scope.saveDevice = function(device) {
      cleanMsg();
      $scope.loading = true;
      device.userRoleId = userRoleId;

      if($scope.option === 'add') {
        hdSrv.save({entity:'device'},device,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Dispositivo registrado satisfactoriamente.';
            $scope.device = {};
            $scope.getDeviceList();
          }else{
            showErrorMsg(data.status);
          }
          $scope.getDeviceList();
        }, function(error){
          showErrorMsg(error);
        });

      }else if($scope.option === 'edit'){
        hdSrv.put({entity:'device'},device,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Dispositivo editado satisfactoriamente.';
            $scope.getDeviceList();
          }else{
            showErrorMsg(data.status);
          }
          $scope.getDeviceList();
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

    $scope.goEdit = function(device) {
      cleanMsg();
      $scope.option = 'edit';
      $scope.device = device;
      $scope.getHallList(device.hall.location.id);
      $scope.step = 2;
    };

    $scope.goDelete = function(device) {
      cleanMsg();
      var modalInstance = $modal.open({
        template : '<div class="modal-header"><h3>Confirmar eliminación</h3></div>'+
              '<div class="modal-body"><p>¿Desea eliminar el dispositivo ' + device.name + ' </p></div>'+
              '<div class="modal-footer"><button class="btn btn-success" ng-click="ok()">Aceptar</button>' +
              '<button class="btn btn-danger" ng-click="closeModal()">Cancelar</button></div>',
        controller : 'modalCtr'
      });
      modalInstance.result.then(function () {
        $scope.loading = true;
        var request = { id:device.id, update_date:device.update_date, userRoleId: userRoleId};
        hdSrv.put({entity:'device',method:'desactivate'},request,function(data){
          if (data.status === 200) {
            $scope.messageSuccess = 'Dispositivo eliminado satisfactoriamente';
            $scope.getDeviceList();
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
      $scope.device = {};
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

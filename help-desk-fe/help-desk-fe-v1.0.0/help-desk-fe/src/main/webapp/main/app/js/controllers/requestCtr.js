define(['controllers/mainCtr'], function(mainCtr){
  'use strict';

  mainCtr.controller('requestCtr',['$scope','hdSrv',function ($scope,hdSrv){

    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.loading = false;
    $scope.request = {};
    $scope.categoryList = [];
    $scope.deviceRequired = false;
    var provider = 1;
    $scope.test = 'pinga';

    $scope.changeTypeRequest = function(reqType) {
      $scope.loading = true;
      $scope.deviceRequired = false;
      if(reqType){
        var request = {
          "restrictions":[
            {
             "type": "conjuntion",
             "subType": "eq",
             "propertyType": "string",
             "propertyName": "type",
             "value": reqType
            }
          ]
        };

        hdSrv.save({entity:'category',method:'getList'},request,function(data){
        switch (data.status) {
          case 200:
            $scope.categoryList = data.dtoList;
            break;
          case 202:
            $scope.messageInfo = 'No existe categorias registradas.';
            break;
          default:
            showErrorMsg();
            break;
        }
        $scope.loading = false;
        }, function(error){
          showErrorMsg(error);
        });
      }else{
        $scope.categoryList = {};
      }
    };

    $scope.getLocationList = function(){
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

    $scope.changeCategoryRequest = function(reqCategory) {
      if(reqCategory){
        $scope.request.category = {id: reqCategory.id};
        if(reqCategory.device_required === '1'){
          $scope.deviceRequired = true;
        }else{
          $scope.deviceRequired = false;
        }
      }else{
         $scope.deviceRequired = false;
      }
    };

    $scope.changeLocationRequest = function(reqLocation) {
      if(reqLocation){
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
             "value": reqLocation
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
      }
    };

    $scope.changeHallRequest = function(reqHall) {
      $scope.deviceList = [];
      if(reqHall){
        provider = reqHall.provider;
        $scope.loading = true;
        var request = {
          "aliases":{
            "device.hall":"hall"
          },
          "restrictions":[
            {
             "type": "conjuntion",
             "subType": "eq",
             "propertyType": "integer",
             "propertyName": "hall.id",
             "value": reqHall.id
            }
          ]
        };
        hdSrv.save({entity:'device',method:'getList'},request,function(data){
          switch (data.status) {
            case 200:
              $scope.deviceList = data.dtoList;
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
      }else{
        provider = 0;
      }

    };

    $scope.saveRequest = function() {
      cleanMsg();

      var request = $scope.request;

      if($scope.request.description.length < 5) {
        $scope.messageInfo = 'La descripción debe ser mayor a 10 digitos.';
      } else {
        $scope.loading = true;
        request.userRoleId = localStorage.getItem('userRoleId');
        request.user_req = {id: localStorage.getItem('userRoleId')};
        var fd = new FormData();

        if($scope.files){
          var blobImage = new Blob([$scope.files[0]], { type: "image/jpeg"});
          fd.append('file',blobImage,'image.jpg');
        }
        var blobRequest = new Blob([JSON.stringify(request)], { type: "application/json; charset=UTF-8"});
        fd.append('requestDto',blobRequest);

        hdSrv.sendFile({entity:'request',method:'add',id:provider},fd,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Solicitud registrada satisfactoriamente.';
            $scope.request = {};
            $scope.request_type = "";
            $scope.categoryList = [];
            $scope._location = {};
            $scope.hallList = [];
            $scope.deviceRequired = false;
            if($scope.files)
              $scope.files = null;
          }else{
            showErrorMsg(data.status);
          }

        }, function(error){
          showErrorMsg(error);
        });
      }

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

    cleanMsg();

  }]);

});

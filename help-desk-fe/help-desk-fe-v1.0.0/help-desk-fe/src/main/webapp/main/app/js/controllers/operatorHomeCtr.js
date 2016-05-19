define(['controllers/mainCtr'], function(mainCtr){
  'use strict';

  mainCtr.controller('operatorHomeCtr',['$scope','$location','hdSrv',function ($scope,$location,hdSrv){

    var userRoleId = parseInt(localStorage.getItem('userRoleId'));
    $scope.userName = localStorage.getItem('userName');
    $scope.sca_container = {'background' : '#F8F9F4'};
    sessionStorage.removeItem('request');
    $scope.loading = false;
    $scope.requestList = [];

    $scope.getRequestList = function(state) {
      $scope.loading = true;
      cleanMsg();
      $scope.requestList = [];
      var request = {
         "aliases":{
           "request.category":"category",
           "request.device":"device",
           "request.user_ope":"user_ope",
           "request.user_req":"user_req",
           "device.hall":"hall",
           "hall.location":"location"
         },
         "projections":{
           "id": "id",
           "active": "active",
           "update_date": "update_date",
           "code": "code",
           "user_req.name": "user_req.name",
           "user_req.paternal_name": "user_req.paternal_name",
           "user_req.maternal_name": "user_req.maternal_name",
           "description": "description",
           "observation": "observation",
           "file": "file",
           "expired": "expired",
           "start_date": "start_date",
           "end_date": "end_date",
           "state": "state",
           "category.name": "category.name",
           "category.type": "category.type",
           "device.name": "device.name",
           "device.code": "device.code",
           "hall.name":"device.hall.name",
           "location.name":"device.hall.location.name"
         },
         "restrictions":[
            {
             "type": "conjuntion",
             "subType": "eq",
             "propertyType": "integer",
             "propertyName": "user_ope.id",
             "value": userRoleId
            }
          ],
          "orders" : {
            "start_date":"desc"
          }
      };

      if(state){
        var reqState = {
         "type": "conjuntion",
         "subType": "eq",
         "propertyType": "string",
         "propertyName": "state",
         "value": state
        };
        request.restrictions.push(reqState);
      }

      hdSrv.save({entity:'request',method:'getList'},request,function(data){
        switch (data.status) {
          case 200:
            $scope.requestList = data.dtoList;
            break;
          case 202:
            $scope.messageInfo = 'No existe solicitudes registradas.';
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

    $scope.getRequestList();

    $scope.attendRequest = function(request){
      sessionStorage.setItem('request', JSON.stringify(request));
      $location.path('/operador/atender');
    };

    $scope.logout = function(){
      localStorage.clear();
      $location.path('/login');
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

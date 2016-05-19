define(['controllers/mainCtr'], function(mainCtr){
  'use strict';

  mainCtr.controller('adminHomeCtr',['$scope','$location','hdSrv',function ($scope,$location,hdSrv){

    var roleId = localStorage.getItem('roleId');
    $scope.userName = localStorage.getItem('userName');
    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.loading = false;
    $scope.requestList = [];
    $scope.operatorList = [];
    var provider = '';
    var operatorType = '';

    if(roleId === '4'){
      provider = '1';
      operatorType = '2';
    }else if(roleId === '5'){
      provider = '2';
      operatorType = '3';
    }

    $scope.getRequestList = function() {
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
           "user_ope.id": "user_ope.id",
           "user_ope.name": "user_ope.name",
           "user_ope.paternal_name": "user_ope.paternal_name",
           "user_ope.maternal_name": "user_ope.maternal_name",
           "description": "description",
           "observation": "observation",
           "file": "file",
           "expired": "expired",
           "start_date": "start_date",
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
             "propertyType": "string",
             "propertyName": "hall.provider",
             "value": provider
            }
          ],
         "orders" : {
            "start_date":"desc"
          }
      };

      if($scope.stateId){
        var reqState = {
         "type": "conjuntion",
         "subType": "eq",
         "propertyType": "string",
         "propertyName": "state",
         "value": $scope.stateId
        };
        request.restrictions.push(reqState);
      }

      if($scope.operatorId){
        var reqOperator = {
         "type": "conjuntion",
         "subType": "eq",
         "propertyType": "integer",
         "propertyName": "user_ope.id",
         "value": $scope.operatorId
        };
        request.restrictions.push(reqOperator);
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

    $scope.getOperatorList = function(){
      $scope.loading = true;
      cleanMsg();
      $scope.operatorList = [];
      var request = {
         "projections":{
           "id": "id",
           "name": "name",
           "paternal_name": "paternal_name",
           "maternal_name": "maternal_name",
           },
          "restrictions":[
            {
             "type": "conjuntion",
             "subType": "eq",
             "propertyType": "integer",
             "propertyName": "role",
             "value": operatorType
            }
          ]
        };

      hdSrv.save({entity:'user',method:'getList'},request,function(data){
        switch (data.status) {
          case 200:
            $scope.operatorList = data.dtoList;
            break;
          case 202:
            $scope.messageInfo = 'No existe usuarios registrados.';
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

    $scope.getOperatorList();

    $scope.editRequest = function(request){
      sessionStorage.setItem('request', JSON.stringify(request));
      $location.path('/admin/editar');
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

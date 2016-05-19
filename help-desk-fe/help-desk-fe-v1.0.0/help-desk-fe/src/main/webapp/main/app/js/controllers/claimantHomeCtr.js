define(['controllers/mainCtr','filters/hdFtr'], function(mainCtr){
  'use strict';

  mainCtr.controller('claimantHomeCtr',['$scope','$location','hdSrv',function ($scope,$location,hdSrv){

    var userRoleId = parseInt(localStorage.getItem('userRoleId'));
    $scope.userName = localStorage.getItem('userName');
    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.loading = false;
    $scope.requestList = [];

    $scope.getRequestList = function() {
      cleanMsg();
      $scope.requestList = [];
      $scope.loading = true;
      var request = {
         "aliases":{
           "request.category":"category",
           "request.device":"device",
           "request.user_req":"user_req",
           "device.hall":"hall",
           "hall.location":"location"
         },
         "projections":{
           "id": "id",
           "active": "active",
           "code": "code",
           "description": "description",
           "observation": "observation",
           "file": "file",
           "end_date": "end_date",
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
             "propertyType": "integer",
             "propertyName": "user_req.id",
             "value": userRoleId
            }
          ],
          "orders" : {
            "start_date":"desc"
          }
      };

      hdSrv.save({entity:'request',method:'getList'},request,function(data){
        switch (data.status) {
          case 200:
            $scope.requestList = data.dtoList;
            break;
          case 202:
            $scope.messageInfo = 'No existe solicitudes registradas';
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

    $scope.detailRequest = function(request){
      sessionStorage.setItem('request', JSON.stringify(request));
      $location.path('/solicitante/detalle');
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

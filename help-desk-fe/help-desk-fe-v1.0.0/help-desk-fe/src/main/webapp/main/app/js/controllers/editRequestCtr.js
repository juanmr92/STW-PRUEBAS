define(['controllers/mainCtr','moment'], function(mainCtr,moment){
  'use strict';

  mainCtr.controller('editRequestCtr',['$scope','hdSrv',function ($scope,hdSrv){

    var userRoleId = parseInt(localStorage.getItem('userRoleId'));
    var roleId = localStorage.getItem('roleId');
    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.loading = false;
    $scope.request = JSON.parse(sessionStorage.getItem('request'));
    var operatorType = '';

    if(roleId === '4')
      operatorType = '2';
    else if(roleId === '5')
      operatorType = '3';

    $scope.saveRequest = function() {
      var request = {
        id : $scope.request.id,
        observation : $scope.request.observation,
        state : $scope.request.state,
        update_date : $scope.request.update_date,
        userRoleId : userRoleId
      };

      if($scope.request.state === '4' || $scope.request.state === '5')
        request.end_date = moment(new Date()).format('YYYY-MM-DD HH:mm:ss');

      if($scope.request.user_ope){
        request.user_ope = {id:$scope.request.user_ope.id};
      }

      hdSrv.put({entity:'request'},request,function(data){
        if(data.status === 200){
          $scope.messageSuccess = 'Solicitud editada satisfactoriamente.';
        }else{
          showErrorMsg(data.status);
        }
        $scope.loading = false;
      }, function(error){
        showErrorMsg(error);
      });
    };

    if(operatorType !== ''){
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
    }

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

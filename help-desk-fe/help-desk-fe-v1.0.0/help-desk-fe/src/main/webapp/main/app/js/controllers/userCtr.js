define(['controllers/mainCtr','controllers/modalCtr','filters/hdFtr','bootstrap'], function(mainCtr){
  'use strict';

  mainCtr.controller('userCtr',['$scope','$modal','hdSrv',function ($scope,$modal,hdSrv){

    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.loading = false;
    $scope.step = 1;
    $scope.userList = [];
    $scope.option = '';
    var userRoleId = 1;

    $scope.getUserList = function() {
      $scope.loading = true;
      var request = {};

      hdSrv.save({entity:'user',method:'getList'},request,function(data){
        switch (data.status) {
          case 200:
            $scope.userList = data.dtoList;
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

    $scope.getUserList();

    $scope.saveUser = function(user) {
      cleanMsg();
      $scope.loading = true;
      user.userRoleId = userRoleId;

      if($scope.option === 'add') {
        hdSrv.save({entity:'user'},user,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Usuario registrado satisfactoriamente';
            $scope.user = {};
            $scope.getUserList();
          }else{
            showErrorMsg(data.status);
          }
        }, function(error){
          showErrorMsg(error);
        });

      }else if($scope.option === 'edit') {
         hdSrv.put({entity:'user'},user,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Usuario editado satisfactoriamente';
            $scope.user = {};
            $scope.getUserList();
          }else{
            showErrorMsg(data.status);
          }
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

    $scope.goEdit = function(user) {
      cleanMsg();
      $scope.option = 'edit';
      $scope.user = user;
      $scope.step = 2;
    };

    $scope.goDelete = function(user) {
      cleanMsg();
      var modalInstance = $modal.open({
        template : '<div class="modal-header"><h3>Confirmar eliminación</h3></div>'+
              '<div class="modal-body"><p>¿Desea eliminar al usuario ' + user.name + ' ' +user.paternal_name + ' </p></div>'+
              '<div class="modal-footer"><button class="btn btn-success" ng-click="ok()">Aceptar</button>' +
              '<button class="btn btn-danger" ng-click="closeModal()">Cancelar</button></div>',
        controller : 'modalCtr'
      });
      modalInstance.result.then(function () {
        $scope.loading = true;
        var request = { id:user.id, update_date:user.update_date, userRoleId: userRoleId};
        hdSrv.put({entity:'user',method:'desactivate'},request,function(data){
          if (data.status === 200) {
            $scope.messageSuccess = 'Usuario eliminado satisfactoriamente';
            $scope.getUserList();
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

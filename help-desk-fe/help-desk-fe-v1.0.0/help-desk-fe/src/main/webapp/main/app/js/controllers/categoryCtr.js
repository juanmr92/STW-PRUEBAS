define(['controllers/mainCtr','controllers/modalCtr','filters/hdFtr','bootstrap'], function(mainCtr){
  'use strict';

  mainCtr.controller('categoryCtr',['$scope','$modal','hdSrv',function ($scope,$modal,hdSrv){

    $scope.sca_container = {'background' : '#F8F9F4'};
    $scope.loading = false;
    $scope.step = 1;
    $scope.categoryList = [];
    $scope.option = '';
    var userRoleId = 1;

    $scope.getCategoryList = function() {
      $scope.loading = true;
      var request = {};

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
    };

    $scope.getCategoryList();

    $scope.saveCategory = function(category) {
      cleanMsg();
      $scope.loading = true;
      category.userRoleId = userRoleId;

      if($scope.option === 'add') {
        hdSrv.save({entity:'category'},category,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Categoria registrado satisfactoriamente';
            $scope.category = {};
            $scope.getCategoryList();
          }else{
            showErrorMsg(data.status);
          }
        }, function(error){
          showErrorMsg(error);
        });

      }else if($scope.option === 'edit') {
         hdSrv.put({entity:'category'},category,function(data){
          $scope.loading = false;
          if (data.status === 200) {
            $scope.messageSuccess = 'Categoria editada satisfactoriamente';
            $scope.getCategoryList();
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

    $scope.goEdit = function(category) {
      cleanMsg();
      $scope.option = 'edit';
      $scope.category = category;
      $scope.step = 2;
    };

    $scope.goDelete = function(category) {
      cleanMsg();
      var modalInstance = $modal.open({
        template : '<div class="modal-header"><h3>Confirmar eliminación</h3></div>'+
              '<div class="modal-body"><p>¿Desea eliminar la categoria ' + category.name + ' </p></div>'+
              '<div class="modal-footer"><button class="btn btn-success" ng-click="ok()">Aceptar</button>' +
              '<button class="btn btn-danger" ng-click="closeModal()">Cancelar</button></div>',
        controller : 'modalCtr'
      });
      modalInstance.result.then(function () {
        $scope.loading = true;
        var request = { id:category.id, update_date:category.update_date, userRoleId: userRoleId};
        hdSrv.put({entity:'category',method:'desactivate'},request,function(data){
          if (data.status === 200) {
            $scope.messageSuccess = 'Categoria eliminado satisfactoriamente';
            $scope.category = {};
            $scope.getCategoryList();
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
      $scope.category = {};
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

define(['controllers/mainCtr','services/loginSrv'], function(mainCtr){
  'use strict';

	mainCtr.controller('loginCtr',['$scope','$location','hdSrv',function ($scope,$location,hdSrv){

    localStorage.clear();
		$scope.sca_container = {'background' : '#9A0024'};
		$scope.user={};
		$scope.loading = false;

		$scope.loginUser = function(){
			$scope.loading = true;
      var request = {
          "restrictions":[
            {
             "type": "conjuntion",
             "subType": "eq",
             "propertyType": "string",
             "propertyName": "account",
             "value": $scope.user.account
            },
            {
             "type": "conjuntion",
             "subType": "eq",
             "propertyType": "string",
             "propertyName": "password",
             "value": $scope.user.password
            }
          ]
        };
      hdSrv.save({entity:'user',method:'getList'},request,function(data){
				$scope.loading = false;
				switch (data.status) {
				case 200:
          localStorage.setItem('userRoleId',data.dtoList[0].id);
          localStorage.setItem('roleId',data.dtoList[0].role);
          localStorage.setItem('userName',data.dtoList[0].name + ' ' +
                                data.dtoList[0].paternal_name);
          switch(data.dtoList[0].role){
            case '0' : $location.path('/superadmin/inicio');break;
            case '1' : $location.path('/solicitante/inicio');break;
            case '2' : $location.path('/operador/inicio');break;
            case '3' : $location.path('/operador/inicio');break;
            case '4' : $location.path('/admin/inicio');break;
            case '5' : $location.path('/admin/inicio');break;
          }
					break;
				case 202:
					$scope.messageError = 'La contraseña ingresada no es correcta, vuelva a intentarlo.';
					break;
				default:
					showErrorMsg(data.status);
					break;
				}
			}, function(error){
				$scope.loading = false;
				showErrorMsg(error);
			});
		};

		function showErrorMsg(error){
			$scope.messageError = 'Ocurrió un error, recargue la página.';
      console.log('error -> ',error);
		}

	}]);

});

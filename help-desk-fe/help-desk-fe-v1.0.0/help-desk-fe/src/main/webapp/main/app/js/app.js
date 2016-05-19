define(["controllers/mainCtr","controllers/loginCtr","controllers/homeCtr",
  "controllers/userCtr","controllers/categoryCtr","controllers/locationCtr",
  "controllers/hallCtr","controllers/deviceCtr","controllers/claimantHomeCtr",
  "controllers/requestCtr","controllers/operatorHomeCtr","controllers/adminHomeCtr",
  "controllers/editRequestCtr","controllers/detailRequestCtr",
  "services/authSrv","angularRoute","bootstrapui"],function(){
  'use strict';

	var app = angular.module("app",[
		'ngRoute',
		'mainCtr',
		'ui.bootstrap'
	]);

	app.config(['$routeProvider',
		function($routeProvider){
			$routeProvider
			.when("/login",{
				templateUrl : "templates/login.html",
				controller  : "loginCtr"
			})
      .when("/superadmin/inicio",{
        templateUrl : "templates/superadmin/home.html",
        controller : "homeCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/superadmin/usuarios",{
        templateUrl : "templates/superadmin/user.html",
        controller  : "userCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/superadmin/categorias",{
        templateUrl : "templates/superadmin/category.html",
        controller  : "categoryCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/superadmin/pabellones",{
        templateUrl : "templates/superadmin/location.html",
        controller  : "locationCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/superadmin/ambientes",{
        templateUrl : "templates/superadmin/hall.html",
        controller  : "hallCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/superadmin/dispositivos",{
        templateUrl : "templates/superadmin/device.html",
        controller  : "deviceCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/solicitante/inicio",{
        templateUrl : "templates/client/home.html",
        controller  : "claimantHomeCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/solicitante/solicitar",{
        templateUrl : "templates/client/requestForm.html",
        controller  : "requestCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/solicitante/detalle",{
        templateUrl : "templates/client/detailRequest.html",
        controller  : "detailRequestCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/operador/inicio",{
        templateUrl : "templates/operator/home.html",
        controller  : "operatorHomeCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/operador/atender",{
        templateUrl : "templates/operator/attendRequest.html",
        controller  : "editRequestCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/admin/inicio",{
        templateUrl : "templates/admin/home.html",
        controller  : "adminHomeCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
      .when("/admin/editar",{
        templateUrl : "templates/admin/editRequest.html",
        controller  : "editRequestCtr",
        resolve :  {
          'auth' :function(authSrv){
            return authSrv.auth();
          }
        }
      })
			.otherwise({ redirectTo : "/login"});
		}]);
	angular.bootstrap(document, ['app']);
	return app;
});

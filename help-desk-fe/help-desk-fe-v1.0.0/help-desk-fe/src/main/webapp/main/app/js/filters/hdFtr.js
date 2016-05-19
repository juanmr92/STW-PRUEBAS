define(['filters/mainFtr'], function(mainFtr) {
  'use strict';

  mainFtr.filter('hdFtr', function() {
    return function(input,type) {
      if (!angular.isUndefined(input)) {
        var hdFilter = {
          "role" : {
            "0" : "Administrador",
            "1" : "Solicitante",
            "2" : "Operador ServiceDesk",
            "3" : "Operador Microtec",
            "4" : "Administrador ServiceDesk",
            "5" : "Administrador Microtec"
          },
          "state" : {
            "0" : "Deshabilitado",
            "1" : "Habilitado"
          },
          "request_type" : {
            "1" : "Requerimiento",
            "2" : "Incidencia"
          },
          "request_state" : {
            "1" : "Asignado",
            "2" : "En proceso",
            "3" : "En espera",
            "4" : "No resuelto",
            "5" : "Resuelto"
          },
          "required" : {
            "0" : "No Requerido",
            "1" : "Requerido"
          },
          "provider" : {
            "1" : "ServiceDesk",
            "2" : "Microtec"
          }
        };
        return hdFilter[type][input];
      }else{
        return input;
      }

    };
  });

});

define(['app','controllers/requestCtr', 'angularMocks'],
  function(app,requestCtr,angularMocks) {

  describe('Emitir Solicitud',function() {
    beforeEach(module("app"));

    describe('Probando RequestCtr ... ',function() {
      var scope = {}, requestCtr;
      beforeEach(inject(function($controller) {
        requestCtr = $controller('requestCtr', {
          $scope: scope
        });
      }));
      it('La variable messageInfo debe tener contenido',
        function() {
          scope.request = {
            category : {
              id: 7
            },
            device : {
              id: 2
            },
            user_req : {
              id : 1
            },
            userRoleId : '1',
            description:'hola'
          };
          scope.saveRequest();
          expect(scope.messageInfo).not.toBe(null);
        }
      );
    });

  });
});

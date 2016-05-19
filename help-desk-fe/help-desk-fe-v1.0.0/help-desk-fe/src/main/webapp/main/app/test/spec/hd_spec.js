define(['app','controllers/requestCtr', 'angularMocks'],
  function(app,requestCtr,angularMocks) {

  describe('Emitir Solicitud',function() {
    var $httpBackend;
    var $rootScope;

    beforeEach(module("app"));

    beforeEach(inject(function ($injector) {
      $httpBackend = $injector.get('$httpBackend');
      $rootScope = $injector.get('$rootScope');
      // OriginalResource = $injector.get('OriginalResource');
    }));

    describe('Probando RequestCtr ... ',function() {
      var scope = {}, requestCtr;
      beforeEach(inject(function($controller) {
        requestCtr = $controller('requestCtr', {
          $scope: scope
        });
      }));
      it('La variable messageInfo debe tener contenido',
        function() {
          $httpBackend.expect('POST','http://192.168.1.36:8080/stw/request/add/1')
            .respond(500,'error');
          $httpBackend.flush();
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

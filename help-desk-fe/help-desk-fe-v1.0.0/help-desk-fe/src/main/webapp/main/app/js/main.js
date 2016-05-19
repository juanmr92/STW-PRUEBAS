require.config({
  paths: {
    angular     : '../lib/angular/angular',
    angularRoute  : '../lib/angular-route/angular-route',
    angularResource : '../lib/angular-resource/angular-resource',
    bootstrap   : '../lib/bootstrap/dist/js/bootstrap',
    bootstrapui   : '../lib/angular-bootstrap/ui-bootstrap-tpls',
    angularUpload : '../lib/angular-file-upload/angular-file-upload',
    esShim      : '../lib/es5-shim/es5-sham',
    jquery      : '../lib/jquery/dist/jquery',
    moment      : '../lib/moment/moment'
  },
  shim:  {
    angular :{
      exports :"angular"
    },
    angularRoute :{
      deps  :["angular"]
    },
    angularResource :{
      deps  :["angular"]
    },
    esShim :{
      exports :"esShim"
    },
    jquery :{
      exports :"jquery"
    },
    bootstrap :{
      deps  :["jquery"]
    },
    bootstrapui :{
      deps  :["angular"]
    },
    moment :{
      exports :"moment"
    },
    app : {
      deps  :["angular"]
    }
  },
  config: {
    moment: {
      noGlobal: true
    }
  },
//  urlArgs: "bust=" + (new Date()).getTime(),
  waitSeconds: 0

});
requirejs(["app"]);

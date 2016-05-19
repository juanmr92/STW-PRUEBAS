// var allTestFiles = [];
// var TEST_REGEXP = /(_spec|_test)\.js$/i;

// // Get a list of all the test files to include
// Object.keys(window.__karma__.files).forEach(function(file) {
//   if (TEST_REGEXP.test(file)) {
//     // Normalize paths to RequireJS module names.
//     // If you require sub-dependencies of test files to be loaded as-is (requiring file extension)
//     // then do not normalize the paths
//     var normalizedTestModule = file.replace(/^\/base\/|\.js$/g, '');
//     allTestFiles.push(normalizedTestModule);
//   }
// });

var allTestFiles = [];
var TEST_REGEXP = /(_spec|_test)\.js$/i;
for (var file in window.__karma__.files) {
  if (TEST_REGEXP.test(file)) allTestFiles.push(file);
}

require.config({
  // Karma serves files under /base, which is the basePath from your config file
  baseUrl: '/base/app/js',

  // dynamically load all test files
  deps: allTestFiles,

  // we have to kickoff jasmine, as it is asynchronous
  callback: window.__karma__.start,

  paths: {
    angular: '/base/app/lib/angular/angular',
    angularRoute  : '/base/app/lib/angular-route/angular-route',
    angularResource : '/base/app/lib/angular-resource/angular-resource',
    angularMocks: '/base/app/lib/angular-mocks/angular-mocks',
    bootstrap   : '/base/app/lib/bootstrap/dist/js/bootstrap',
    bootstrapui   : '/base/app/lib/angular-bootstrap/ui-bootstrap-tpls',
    angularUpload : '/base/app/lib/angular-file-upload/angular-file-upload',
    esShim      : '/base/app/lib/es5-shim/es5-sham',
    jquery      : '/base/app/lib/jquery/dist/jquery',
    moment      : '/base/app/lib/moment/moment'
  },
  shim: {
    angular: {
      exports: 'angular'
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
    angularMocks: {
      deps: ['angular']
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
  }
});

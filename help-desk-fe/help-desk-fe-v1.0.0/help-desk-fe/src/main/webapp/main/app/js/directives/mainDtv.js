define(['angular'],function(angular){
  'use strict';
  var mainDtv = angular.module('mainDtv',[]);

  mainDtv.directive('fileInput',['$parse',function ($parse){
    return {
      restrict: 'A',
      link:function(scope,elm,attrs){
        elm.bind('change',function(){
          $parse(attrs.fileInput)
          .assign(scope,elm[0].files);
          scope.$apply();
        });
        scope.$watch(attrs.fileInput,function(file){
          if(!file)
            elm.val(null);
        });
      }
    };
  }]);

  return mainDtv;
});

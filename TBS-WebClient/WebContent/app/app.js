var TBSApp = angular.module("TBSApp", ['ngAnimate', 'ngRoute']);

TBSApp.controller("mainController", function ($scope, tokenService) {

	$scope.token = '';
	$scope.login = '';

	$scope.initGlobVars = function() {
		$scope.token = tokenService.getToken();
		$scope.login = tokenService.getLogin();
	}

	$scope.logout = function() {
		resetGlobVars();
	}
	var resetGlobVars = function() {
		$scope.token = '';
		$scope.login = '';
	}

	$scope.isLogged = function() {
		return $scope.login != '';
	}
});

TBSApp.config(function($routeProvider) {
	$routeProvider
	    .when('/myTranslations', {templateUrl: 'app/views/myTranslations.html'})
	    .when('/signup', {templateUrl: 'app/views/signUpp.html'})
	    .when('/', {templateUrl: 'app/views/translationsBySubtitles.html'})
	    .when('/login', {templateUrl: 'app/views/login.html'})
	    .when('/translation/:languageFrom/:languageTo/:exprToTranslate', {
		    templateUrl: 'app/views/translationsBySubtitles.html',    
		})
	    .otherwise({
	        redirectTo: '/'
	      })
});
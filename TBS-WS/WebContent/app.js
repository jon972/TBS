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
//	.when('/', {templateUrl: 'home.html'})
	    .when('/myTranslations', {templateUrl: 'myTranslations.html'})
	    .when('/signup', {templateUrl: 'SignUp.html'})
	    .when('/', {templateUrl: 'translationsBySubtitles.html'})
	    .when('/login', {templateUrl: 'login.html'})
	    .otherwise({
	        redirectTo: '/signup'
	      })
});

TBSApp.controller("translationController", function ($scope, $http, tokenService) {
	var isLoading = false;
	var currentSelect = -1;
	$scope.translateRequest = function () {
		isLoading = true;
		$scope.clicked = false;
		console.log("hello");
		console.log(tokenService.getToken());
		$http.get('rest/translationService/' + $scope.wordToTranslate + 
				  '/' + $scope.languageFrom + '/' + $scope.languageTo)
	                                .then(function (response) {
	                                	$scope.translations = response.data;
	                                	isLoading = false;
	                                });
	}

	$scope.isLoadingFromServer = function () {
		return isLoading;
	}

	$scope.getAllLanguages = function () {
		$http.get('rest/availableLanguages/allLanguages')
	                                .then(function (response) {
	                                	$scope.availableLanguages = response.data;
	                                	$scope.languageTo = $scope.availableLanguages [0];
	                                	$scope.languageFrom = $scope.availableLanguages[1]
	                                	console.log($scope.availableLanguages);
	                                });
	}
});

TBSApp.directive('ngHello', function ($http) {
	return {
		templateUrl : 'tanslationEntity.html',
		restrict : 'E',
		link : function (scope, element, attributes) {
			var index = attributes["index"];
			var translation = attributes["translation"];
			scope.isSavable = attributes["issavable"];
			scope.clicked = false;
			scope.mouseIsOver = false;
			scope.onTranslationClick = function (){
				scope.clicked = !scope.clicked;
				if(scope.clicked) {
					zenscroll.to(element.children()[0]);
				}
			};

			scope.saveTranslation = function() {
				var req = {
				 method: 'POST',
				 url: 'rest/translationService/saveTranslation',
				 headers: {
				   'Content-Type': 'application/json',
				   'token': scope.token, 
				   'expr1': JSON.parse(translation).exprToTranslate,
				   'expr2': JSON.parse(translation).exprTranslated
				 },
				};
				$http(req).then(function (response) {
                });
			}
		}
	}
})

TBSApp.service('tokenService', function () {
    var token;
    var login;
    return {
        getToken: function () {
            return token;
        },
        setToken: function(value) {
        	token = value;
        },
        getLogin: function () {
            return login;
        },
        setLogin: function(value) {
        	login = value;
        }
    };
});
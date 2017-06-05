TBSApp.controller("translationController", function ($scope, $http, tokenService, $routeParams, $location) {
	var isLoading = false;
	var currentSelect = -1;

	$scope.setTranslationUrl = function (languageFrom, languageTo, exprToTranslate) {
		$location.path('/translation/' + languageFrom + '/' + languageTo + '/' + exprToTranslate).replace();
	}
	$scope.translateRequest = function () {

		if($routeParams.exprToTranslate === undefined) {	
			return;
		}
		isLoading = true;
		$scope.clicked = false;
		var req = {
			 method: 'GET',
			 url: '/TBS-WS/rest/translation/' + $routeParams.exprToTranslate + 
			  '/' + $routeParams.languageFrom + '/' + $routeParams.languageTo,
			 headers: {
			   'Content-Type': 'application/json',
			   'token': tokenService.getToken(), 
			 }
		};
		$http(req).then(function (response) {
        	$scope.translations = response.data;
        	$scope.languageTo = $routeParams.languageTo;
        	$scope.languageFrom = $routeParams.languageFrom;
        	$scope.exprToTranslate = $routeParams.exprToTranslate;
        	isLoading = false;
        });
	}

	$scope.isLoadingFromServer = function () {
		return isLoading;
	}
});

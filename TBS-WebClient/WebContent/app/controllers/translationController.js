TBSApp.controller("translationController", function ($scope, $http, tokenService, $routeParams) {
	var isLoading = false;
	var currentSelect = -1;

	$scope.setTranslationUrl = function () {
		window.location.replace('#/translation/' + $scope.languageFrom + '/' + $scope.languageTo + '/' + $scope.exprToTranslate);
	}
	$scope.translateRequest = function () {

		if($routeParams.exprToTranslate === undefined) {	
			return;
		}
		isLoading = true;
		$scope.clicked = false;
		var req = {
			 method: 'GET',
			 url: 'rest/translation/' + $routeParams.exprToTranslate + 
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

	$scope.getAllLanguages = function () {
		$http.get('rest/availableLanguages/allLanguages').then(function (response) {
        	$scope.languageTo = $routeParams.languageTo;
        	$scope.languageFrom = $routeParams.languageFrom;
        	$scope.availableLanguages = response.data;
        	console.log($scope.availableLanguages);
        	if($routeParams.exprToTranslate === undefined) {	
    			$scope.languageFrom = $scope.availableLanguages[1];
    			$scope.languageTo = $scope.availableLanguages[0];
    		}
         });
	}
});

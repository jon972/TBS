TBSApp.controller("translationController", function ($scope, $http, tokenService, $routeParams, $location, $cookieStore, entityVideoDetailsService) {
	var isLoading = false;

	$scope.$on('selectedEvent', function (event, index) {
	  for (var i = 0; i < $scope.translations.length; i++) {
	  	$scope.translations[i].isSelected = false;
	  }
	  $scope.translations[index].isSelected = true;
	  $scope.anElementIsSelected = true;
	});

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
			   'token': $cookieStore.get('token'), 
			 }
		};
		$http(req).then(function (response) {
        	$scope.translations = response.data;
        	$scope.languageTo = $routeParams.languageTo;
        	$scope.languageFrom = $routeParams.languageFrom;
        	$scope.exprToTranslate = $routeParams.exprToTranslate;
        	isLoading = false;
        	for (var i = 0; i < $scope.translations.length; i++) {
			  	$scope.translations[i].isSelected = false;
			}
			$scope.translations[0].isSelected = true;
        });
	}
	
	$scope.isLoadingFromServer = function () {
		return isLoading;
	}

	$scope.resetTranslationsAround = function () {
		entityVideoDetailsService.setTranslationsAround(null);
		entityVideoDetailsService.setEntityVideoDTO(null);
	}
});

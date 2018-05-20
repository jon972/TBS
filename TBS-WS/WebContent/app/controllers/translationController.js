TBSApp.controller("translationController", function ($scope, $http, tokenService, $routeParams, $location, $cookieStore, entityVideoDetailsService) {
	var isLoading = false;
	var isLoadingAllsubsOfAnEntityVideoFromServer = false;

	$scope.$on('selectedEvent', function (event, index) {
	  for (var i = 0; i < $scope.translations.length; i++) {
	  	$scope.translations[i].isSelected = false;
	  }
	  $scope.translations[index].isSelected = true;
	  $scope.anElementIsSelected = true;
	  $scope.currentTranslationSelected = $scope.translations[index];
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
        });
	}

	$scope.getAllSubtitles = function () {

		if($routeParams.exprToTranslate === undefined) {	
			return;
		}

		isLoadingAllsubsOfAnEntityVideoFromServer = true; 
	
		var req = {
			 method: 'GET',
			 url: '/TBS-WS/rest/translation/allTranslations',
			 headers: {
			   'Content-Type': 'application/json',
			   'token': $cookieStore.get('token'),
			   'translation': JSON.stringify($scope.currentTranslationSelected)
			 }
		};
		$http(req).then(function (response) {
        	entityVideoDetailsService.setTranslationsAround(response.data);
        	isLoadingAllsubsOfAnEntityVideoFromServer = false;
        });
	}
	
	// In progress
	$scope.getSubtitles = function () {

		if($routeParams.exprToTranslate === undefined) {	
			return;
		}
		isLoading = true;
		$scope.clicked = false;
		var req = {
			 method: 'GET',
			 url: '/TBS-WS/rest/translation/Pretty little liars/8/5/ENGLISH/FRENCH/',
			 headers: {
			   'Content-Type': 'application/json',
			   'token': $cookieStore.get('token'), 
			 }
		};
		$http(req).then(function (response) {
        	console.log(response);
        });
	}
	
	$scope.isLoadingFromServer = function () {
		return isLoading;
	}

	$scope.isLoadingAllsubsOfAnEntityVideoFromServer = function () {
		return isLoadingAllsubsOfAnEntityVideoFromServer;
	}

	$scope.resetTranslationsAround = function () {
		entityVideoDetailsService.setTranslationsAround(null);
		entityVideoDetailsService.setEntityVideoDTO(null);
	}
});

TBSApp.controller("translationController", function ($scope, $http, tokenService) {
	var isLoading = false;
	var currentSelect = -1;
	$scope.translateRequest = function () {
		isLoading = true;
		$scope.clicked = false;
		console.log("hello");
		console.log(tokenService.getToken());
		var req = {
				 method: 'GET',
				 url: 'rest/translation/' + $scope.wordToTranslate + 
				  '/' + $scope.languageFrom + '/' + $scope.languageTo,
				 headers: {
				   'Content-Type': 'application/json',
				   'token': tokenService.getToken(), 
				 }
				};
		$http(req).then(function (response) {
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

var TBSApp = angular.module("TBSApp", []);

TBSApp.controller("mainController", function ($scope) {

});

TBSApp.controller("translationController", function ($scope, $http) {
	var hideImg = true;
	$scope.translateRequest = function () {
		hideImg = false;
		$http.get('rest/translationService/' + $scope.wordToTranslate + 
				  '/' + $scope.languageFrom + '/' + $scope.languageTo)
	                                .then(function (response) {
	                                	$scope.translations = response.data;
	                                	hideImg = true;
	                                });
	}
	
	$scope.hideImgOrNot = function () {
		return hideImg;
	}
});
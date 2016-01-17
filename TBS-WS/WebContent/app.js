var TBSApp = angular.module("TBSApp", []);

TBSApp.controller("mainController", function ($scope) {

});

TBSApp.controller("translationController", function ($scope, $http) {
	var hideImg = true;
	var hideTranslationFound = true;
	$scope.translateRequest = function () {
		hideImg = false;
		hideTranslationFound = true;
		$http.get('rest/translationService/' + $scope.wordToTranslate + 
				  '/' + $scope.languageFrom + '/' + $scope.languageTo)
	                                .then(function (response) {
	                                	$scope.translations = response.data;
	                                	hideImg = true;
	                                	if($scope.translations.length == 0) {
	                                		hideTranslationFound = false;
	                                	} else {
	                                		hideTranslationFound = true;
	                                	}
	                                });
	}
	
	$scope.hideImgOrNot = function () {
		return hideImg;
	}
	
	$scope.hideTranslationFoundOrNot = function () {
		return hideTranslationFound;
	}
});
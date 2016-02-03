var TBSApp = angular.module("TBSApp", ['ngAnimate']);

TBSApp.controller("mainController", function ($scope) {

});

TBSApp.controller("translationController", function ($scope, $http) {
	var isLoading = false;
	var currentSelect = -1;
	$scope.translateRequest = function () {
		isLoading = true;
		$scope.clicked = false;
		$http.get('http://localhost:8081/TBS-WS/rest/translationService/' + $scope.wordToTranslate + 
				  '/' + $scope.languageFrom + '/' + $scope.languageTo)
	                                .then(function (response) {
	                                	$scope.translations = response.data;
	                                	isLoading = false;
	                                });
	}

	$scope.isLoadingFromServer = function () {
		return isLoading;
	}
});

TBSApp.directive('ngHello', function () {
	return {
		templateUrl : 'tanslationEntity.html',
		restrict : 'E',
		link : function (scope, element, attributes) {
			var index = attributes["index"];
			var translation = attributes["translation"];
			scope.clicked = false;
			scope.mouseIsOver = false;
			scope.onTranslationClick = function (){
				scope.clicked = !scope.clicked;
				if(scope.clicked) {
					zenscroll.to(element.children()[0]);
				}
			};
		}
	}
})
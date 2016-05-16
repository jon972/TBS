TBSApp.controller("languagesController", function ($scope, $http, tokenService, $routeParams) {
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
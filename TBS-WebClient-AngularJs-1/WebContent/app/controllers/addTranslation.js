TBSApp.controller("addTranslationController", function ($scope, $http) {
	$scope.addTranslation = function () {
		var translation = new Translation(null, new SubtitleDTO(null, $scope.exprFrom, $scope.languageFrom),
				new SubtitleDTO(null, $scope.exprTo, $scope.languageTo), false, true);
		var req = {
		 method: 'POST',
		 url: '/TBS-WS/rest/translation/addTranslation',
		 headers: {
		   'Content-Type': 'application/json',
		   'token': $scope.token,
		   'translation' : JSON.stringify(translation),
		 },
		};
		$http(req).then(function(response){
		}, function(data){
			console.log(data);
		});
		
		$scope.exprFrom = "";
		$scope.exprTo = "";
	}
});
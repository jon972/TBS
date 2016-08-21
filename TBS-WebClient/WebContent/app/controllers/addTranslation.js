TBSApp.controller("addTranslationController", function ($scope, $http) {
	$scope.addTranslation = function () {
		var translation = new Translation(null, new SubtitleDTO(null, $scope.exprFrom, $scope.languageFrom),
				new SubtitleDTO(null, $scope.exprTo, $scope.languageTo), false);
		var req = {
		 method: 'POST',
		 url: 'rest/translation/addTranslation',
		 headers: {
		   'Content-Type': 'application/json',
		   'token': $scope.token,
		   'translation' : JSON.stringify(translation),
		 },
		};
		$http(req).then(function(response){
			$scope.userTranslations = response.data;
		}, function(data){
			console.log(data);
		});
	}
});
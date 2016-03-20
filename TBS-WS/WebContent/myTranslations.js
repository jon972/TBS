TBSApp.controller("myTranslationsController", function ($scope, $http) {
	$scope.retrieveMyTranslations = function () {
		var req = {
		 method: 'POST',
		 url: 'rest/translationService/retrieveMyTranslations',
		 headers: {
		   'Content-Type': 'application/json',
		   'token': $scope.token
		 },
		};
		$http(req).then(function(response){
			$scope.userTranslations = response.data;
		}, function(data){
			console.log(data);
		});
	}
});
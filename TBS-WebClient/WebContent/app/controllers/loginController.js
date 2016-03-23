TBSApp.controller("loginController", function ($scope, $http, tokenService) {
	$scope.login = function () {
		$scope.requestFailed = false;
		$scope.isLoading = true;
		var req = {
		 method: 'GET',
		 url: 'rest/account/login',
		 headers: {
		   'Content-Type': 'application/json',
		   'email': $scope.email, 
		   'password': $scope.password
		 },
		};
		$http(req).then(function(response){
			tokenService.setLogin(response.data.login);
			tokenService.setToken(response.data.token);
			$scope.initGlobVars();
			$scope.isLoading = false;
			window.location.replace("#/");
		}, function(data){
			$scope.isLoading = false;
			$scope.requestFailed = true;
		});
	}
});
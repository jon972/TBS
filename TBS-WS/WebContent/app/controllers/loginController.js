TBSApp.controller("loginController", function ($scope, $http, tokenService, $cookieStore) {
	$scope.login = function () {
		$scope.requestFailed = false;
		$scope.isLoading = true;
		var req = {
		 method: 'GET',
		 url: '/TBS-WS/rest/account/login',
		 headers: {
		   'Content-Type': 'application/json',
		   'email': $scope.email, 
		   'password': $scope.password
		 },
		};
		$http(req).then(function(response){
			tokenService.setLogin(response.data.login);
			tokenService.setToken(response.data.token);
			$cookieStore.put('token', response.data.token);
			$scope.initGlobVars();
			$scope.isLoading = false;
			window.location.replace("#/");
		}, function(data){
			$scope.isLoading = false;
			$scope.requestFailed = true;
			$scope.password = "";
			$scope.email = "";
		});
	}
});
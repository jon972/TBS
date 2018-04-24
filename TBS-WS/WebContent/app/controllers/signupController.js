TBSApp.controller("signupController", function ($scope, $http, tokenService, $cookieStore) {
	$scope.submitUserDatas = function () {
		$scope.requestFailed = false;
		$scope.isLoading = true;
		var req = {
		 method: 'POST',
		 url: '/TBS-WS/rest/account/createAccount',
		 headers: {
		   'Content-Type': 'application/json',
		   'email': $scope.email, 
		   'password': $scope.password,
		   'login': $scope.login
		 }
		};
		$http(req).then(function(response){
			tokenService.setToken(response.data.token);
			$cookieStore.put('token', response.data.token);
			$scope.initGlobVars();
			$scope.isLoading = false;
			window.location.replace("#/");
		}, function(data){
			console.log(data);
		});
	}
});
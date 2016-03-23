TBSApp.controller("signupController", function ($scope, $http, tokenService) {
	$scope.submitUserDatas = function () {
		var req = {
		 method: 'POST',
		 url: 'rest/signup/createAccount',
		 headers: {
		   'Content-Type': 'application/json',
		   'email': $scope.email, 
		   'password': $scope.password,
		   'login': $scope.login
		 },
		};
		$http(req).then(function(response){
			$scope.token = response.data;
			console.log("signupController");
			console.log($scope.token);
			console.log(response.data);
			tokenService.setToken(response.data.token);
			$scope.login = response.data.login;
		}, function(data){
			console.log(data);
		});
	}
});
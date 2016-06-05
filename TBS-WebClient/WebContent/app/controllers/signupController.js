TBSApp.controller("signupController", function ($scope, $http, tokenService) {
	$scope.submitUserDatas = function () {
		var req = {
		 method: 'POST',
		 url: 'rest/account/createAccount',
		 headers: {
		   'Content-Type': 'application/json',
		   'email': $scope.email, 
		   'password': $scope.password,
		   'login': $scope.login
		 }
		};
		$http(req).then(function(response){
			tokenService.setToken(response.data.token);
		}, function(data){
			console.log(data);
		});
	}
});
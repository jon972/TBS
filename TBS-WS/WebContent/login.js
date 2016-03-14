TBSApp.controller("loginController", function ($scope, $http, tokenService) {
	$scope.login = function () {
		var req = {
		 method: 'GET',
		 url: 'rest/signup/login',
		 headers: {
		   'Content-Type': 'application/json',
		   'email': $scope.email, 
		   'password': $scope.password
		 },
		};
		$http(req).then(function(response){
			$scope.token = response.data;
			console.log("loginController");
			console.log($scope.token);
			console.log(response.data);
			tokenService.setLogin(response.data.login);
			tokenService.setToken(response.data.token);
			$scope.initGlobVars();
			window.location.replace("#/");
		}, function(data){
			console.log(data);
		});
	}
});
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
			tokenService.setLogin(response.data.login);
			tokenService.setToken(response.data.token);
			$scope.initGlobVars();
			window.location.replace("#/");
		}, function(data){
			console.log(data);
		});
	}
});
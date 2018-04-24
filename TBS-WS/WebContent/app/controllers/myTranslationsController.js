TBSApp.controller("myTranslationsController", function ($scope, $http, $cookieStore) {
	$scope.retrieveMyTranslations = function (languageFrom, languageTo) {
		var req = {
		 method: 'POST',
		 url: '/TBS-WS/rest/translation/retrieveMyTranslations',
		 headers: {
		   'Content-Type': 'application/json',
		   'token': $cookieStore.get('token'),
		   'languageFrom' : languageFrom,
		   'languageTo' : languageTo
		 },
		};
		$http(req).then(function(response){
			$scope.userTranslations = response.data;
		}, function(data){
			console.log(data);
		});
	}
});
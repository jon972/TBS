TBSApp.controller("myTranslationsController", function ($scope, $http, $cookieStore) {
	$scope.entityVideosDisplayed = true;
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

	$scope.retrieveMyEntityvideos = function (languageFrom, languageTo) {
		var req = {
		 method: 'POST',
		 url: '/TBS-WS/rest/translation/retrieveMyEntityvideos',
		 headers: {
		   'Content-Type': 'application/json',
		   'token': $cookieStore.get('token'),
		   'languageFrom' : languageFrom,
		   'languageTo' : languageTo
		 },
		};
		$http(req).then(function(response){
			$scope.myEntityVideos = response.data;
			$scope.entityVideosDisplayed = true;
		}, function(data){
			console.log(data);
		});
	}

	$scope.retrieveMyTranslationsOfAnEntityVideo = function (languageFrom, languageTo, entityvideo) {
		var req = {
		 method: 'POST',
		 url: '/TBS-WS/rest/translation/retrieveMyTranslationsOfAnEntityVideo',
		 headers: {
		   'Content-Type': 'application/json',
		   'token': $cookieStore.get('token'),
		   'languageFrom' : $scope.languageFrom,
		   'languageTo' : $scope.languageTo,
		   'entityVideo' : JSON.stringify(entityvideo)
		 },
		};
		$http(req).then(function(response){
			$scope.userTranslations = response.data;
			$scope.entityVideosDisplayed = false;
		}, function(data){
			console.log(data);
		});
	}
});
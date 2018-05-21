TBSApp.controller("myTranslationsController", function ($scope, $http, $cookieStore) {
	$scope.entityVideosDisplayed = false;
	$scope.seasonEpisodeSelectionDisplayed = false;
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
			$scope.seasonEpisodeSelectionDisplayed = false;
			$scope.filterMyTranslationsFiltered = new Array();
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
			$scope.entityVideosDisplayed = true;
			$scope.initSeasonsAndEpisode();
			$scope.currentEntityVideo = entityvideo;
		}, function(data){
			console.log(data);
		});
	}

	$scope.initSeasonsAndEpisode = function () {
		$scope.seasons = new Set();
		$scope.episodes = new Set();

		$scope.seasons.add("All");
		$scope.episodes.add("All");

		for(var i = 0; i < $scope.userTranslations.length; i++) {
			$scope.seasons.add($scope.userTranslations[i].subtitleDTOToTranslate.entityvideoDTO.numSeason);
			$scope.episodes.add($scope.userTranslations[i].subtitleDTOToTranslate.entityvideoDTO.numEpisode);
		}
		$scope.seasons = Array.from($scope.seasons);
		$scope.episodes = Array.from($scope.episodes);

		$scope.seasonEpisodeSelectionDisplayed = true;
		$scope.entityVideosDisplayed = false;
	}

	$scope.filterMyTranslationBySeasonAndEpisode = function(entitySeason, entityEpisode) {
		var j = 0;
		if (entitySeason == "All" && entityEpisode == "All") {
			$scope.filterMyTranslationsFiltered = $scope.userTranslations;
			return;
		}
		if (entitySeason == "All") {
			for(var i = 0; i < $scope.userTranslations.length; i++) {
				if ($scope.userTranslations[i].subtitleDTOToTranslate.entityvideoDTO.numEpisode == entityEpisode) {
					$scope.filterMyTranslationsFiltered[j] = $scope.userTranslations[i];
					j++;
				}
			}
			return;
		}
		if (entityEpisode == "All") {
			for(var i = 0; i < $scope.userTranslations.length; i++) {
				if ($scope.userTranslations[i].subtitleDTOToTranslate.entityvideoDTO.numSeason == entitySeason) {
					$scope.filterMyTranslationsFiltered[j] = $scope.userTranslations[i];
					j++;
				}
			}
			return;
		}
		for(var i = 0; i < $scope.userTranslations.length; i++) {
			if ($scope.userTranslations[i].subtitleDTOToTranslate.entityvideoDTO.numSeason == entitySeason &&
			    $scope.userTranslations[i].subtitleDTOToTranslate.entityvideoDTO.numEpisode == entityEpisode) {
				$scope.filterMyTranslationsFiltered[j] = $scope.userTranslations[i];
				j++;
			}
		}
	}
});
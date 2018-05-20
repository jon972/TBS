TBSApp.directive('ngTransEntity', function ($http, $cookieStore, entityVideoDetailsService) {
	return {
		templateUrl : 'app/views/tanslationEntity.html',
		restrict : 'E',
		link : function (scope, element, attributes) { 
			var translation = attributes["translation"];
			scope.isSavable = attributes["issavable"];
			scope.location = attributes["location"];
			scope.index = attributes["index"];
			scope.isSaved = JSON.parse(translation).isSaved;
			scope.mouseIsOver = false;
			scope.entityVideoDTO = null;
			scope.isSelected = null;

			scope.saveTranslation = function() {
				var req = {
					method : 'POST',
					url : '/TBS-WS/rest/translation/saveTranslation',
					headers : {
						'Content-Type' : 'application/json',
						'token' : $cookieStore.get('token'),
						'translation' : translation
					},
				};
				$http(req).then(function(response) {
					scope.isSaved = true;
				});
			}

			scope.unsaveTranslation = function() {
				var req = {
					method : 'POST',
					url : '/TBS-WS/rest/translation/removeMyTranslation',
					headers : {
						'Content-Type' : 'application/json',
						'token' : $cookieStore.get('token'),
						'translation' : translation
					},
				};
				$http(req).then(function(response) {
					for(var i = 0; i < scope.userTranslations.length; i++) {
						if(scope.userTranslations[i].id == scope.translation.id) {
							scope.userTranslations.splice(i, 1);
						}
					}
				});
			}

			scope.getInfos = function() {
				var req = {
					method : 'POST',
					url : '/TBS-WS/rest/translation/translationInfos',
					headers : {
						'Content-Type' : 'application/json',
						'translation' : translation
					},
				};
				$http(req).then(function(entityVideoDTO) {
					scope.entityVideoDTO = entityVideoDTO.data;
				});
			}

			scope.setInfosAreDisplayed = function() {
				var req = {
					method : 'POST',
					url : '/TBS-WS/rest/translation/translationInfos',
					headers : {
						'Content-Type' : 'application/json',
						'translation' : translation
					},
				};
				$http(req).then(function(entityVideoDTO) {
					scope.entityVideoDTO = entityVideoDTO.data;
					entityVideoDetailsService.setEntityVideoDTO(entityVideoDTO.data);
					entityVideoDetailsService.setIndex(scope.$index);
					entityVideoDetailsService.setPositionY(window.scrollY);

					if(scope.location === "translationsList") {
						scope.$emit('selectedEvent', scope.$index);
					}
				});
			}
			
			scope.getTranslationsAround = function () {
				var req = {
					method : 'GET',
					url : '/TBS-WS/rest/translation/translationsAround',
					headers : {
						'Content-Type' : 'application/json',
						'translation' : translation,
						'token' : $cookieStore.get('token')
					},
				};
				$http(req).then(function(translations) {
					entityVideoDetailsService.setTranslationsAround(translations.data);
				});
			}

			scope.getTimeBegin = function () {
			    var transObj = JSON.parse(attributes["translation"]); 
			    var minutes = Math.trunc(transObj.subtitleDTOToTranslate.timebegin / 60);
				var seconds = transObj.subtitleDTOToTranslate.timebegin % 60;
				return minutes + ':' + (seconds < 10 ? "0" + seconds : seconds);
			}
		}
	}
});
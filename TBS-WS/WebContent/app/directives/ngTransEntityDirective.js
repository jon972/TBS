TBSApp.directive('ngTransEntity', function ($http, $cookieStore, entityVideoDetailsService) {
	return {
		templateUrl : 'app/views/tanslationEntity.html',
		restrict : 'E',
		link : function (scope, element, attributes) { 
			var translation = attributes["translation"];
			scope.isSavable = attributes["issavable"];
			scope.isSaved = JSON.parse(translation).isSaved;
			scope.clicked = false;
			scope.mouseIsOver = false;
			scope.entityVideoDTO = null;

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

					// scroll le div des details
					/*var detailsElement = document.getElementById("machin");
					if(detailsElement.offsetTop < window.scrollY+1) {
						detailsElement.style = "position : absolute; top : " + window.scrollY + "px";
					} else {
						detailsElement.style = "position : relative; top : 0px";
					}*/
					
				});
			}
		

			scope.infosAreDisplayed = function() {
				return entityVideoDetailsService.index == scope.$index;
			}
		}
	}
});
TBSApp.directive('ngTransEntity', function ($http, $cookieStore) {
	return {
		templateUrl : 'app/views/tanslationEntity.html',
		restrict : 'E',
		link : function (scope, element, attributes) {
			var translation = attributes["translation"];
			scope.isSavable = attributes["issavable"];
			scope.isSaved = JSON.parse(translation).isSaved;
			scope.clicked = false;
			scope.mouseIsOver = false;

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
		}
	}
});
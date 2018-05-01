TBSApp.controller("entityVideoDetailsController", function ($scope, entityVideoDetailsService) {
	$scope.translationsAround = null;

	$scope.getEntityVideoDTO = function() {
		return entityVideoDetailsService.getEntityVideoDTO();
	}

	$scope.getPositionY = function() {
		return entityVideoDetailsService.getPositionY();
	}

	$scope.getTranslationsAround = function() {
		return entityVideoDetailsService.getTranslationsAround();
	}
});
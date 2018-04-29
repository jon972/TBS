TBSApp.controller("entityVideoDetailsController", function ($scope, entityVideoDetailsService) {
	$scope.getEntityVideoDTO = function() {
		return entityVideoDetailsService.getEntityVideoDTO();
	}

	$scope.getPositionY = function() {
		return entityVideoDetailsService.getPositionY();
	}
});
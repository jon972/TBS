TBSApp.controller("integrationSubtitlesFiles", function ($scope, $http) {
	$scope.uploadFile = function () {
		var fd = new FormData();
		fd.append('file', $scope.fileSRT);
		$http.post("/TBS-WS/rest/populate/integrateFile", fd, {
            transformRequest: angular.identity,
           
            headers: {
            	'Content-Type': undefined,
            	'serieName' : $scope.name,
                'episodeNumber' : $scope.episodeNumber,
                'seasonNumber' : $scope.seasonNumber,
                'language' : $scope.language
            }
        })
        .success(function(){
        })
        .error(function(){
        });
		
	}
})
.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
angular.module('filmApp', ['ngMaterial', 'jkAngularRatingStars'])

.controller('RatingController', function($scope) {
    $scope.firstRate = 0;
    $scope.secondRate = 3;
    $scope.readOnly = true;
    $scope.onItemRating = function(rating){
      alert('On Rating: ' + rating);
    };
});
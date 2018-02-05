//var controllers = angular.module('userControllers', []).
angular.module('myApp')
.controller('userController',
		['userService', 'subForumService', '$scope', '$location', '$rootScope',
function(userService, subForumService, $scope, $location, $rootScope){
	$scope.user = {};
	
	$rootScope.trendingTopics = {};
	$rootScope.savedForums ={};
	$scope.badCredentials = false;
	
	$scope.signUp = function(){
		$scope.user.role='DEFAULT';
		//$scope.user.role='ADMIN';
		userService.addUser($scope.user);
		$location.path("/subForums");
	};
	
	$scope.login = function(){
		userService.login($scope.user.userID, $scope.user.password).
			then(function(data){
				if(data.data !=""){
					
					$scope.badCredentials = false;
					$rootScope.User = {};
					
					$rootScope.User.userID = $scope.user.userID;
					$rootScope.User.password = $scope.user.password;
					$rootScope.User.name = data.data.name;
					$rootScope.User.surname = data.data.surname;
					$rootScope.User.role = data.data.role;
					$rootScope.User.phoneNumber = data.data.phoneNumber;
					$rootScope.User.email = data.data.email;
					$rootScope.User.regDate = data.data.regDate;
					$rootScope.User.comments = data.data.comments;
					$rootScope.User.topics = data.data.topics;
					$rootScope.User.forums = data.data.forums;
					$rootScope.User.savedForums = data.data.savedForums;
					$rootScope.loggedIn = true;
					
					//localStorage.setItem("user", $rootScope.User);
					
					
				}
				else{
					$scope.badCredentials = true;
				}
			})
	};
	
	
	
	$scope.logout = function() {
		$rootScope.loggedIn = false;
		$rootScope.User = {};
		$scope.user.userID = "";
		$scope.user.password = "";
		$location.path("/subForums");
	}
/*	
	subForumService.getTrendingTopics().then(function(response){
		$rootScope.trendingTopics = response.data;
	})
	*/
	
	
	

}])

.controller("userMngController",
		["$rootScope", "$scope" , "userService",
			function($rootScope, $scope, userService){
			$scope.users= {};
			
			
	userService.getAllUsers().then(function(data){
		$scope.users = data.data;
	})
	
	$scope.setRole = function(userID, role, adminID, adminPass){
		userService.setRole(userID, role, $rootScope.User.userID, $rootScope.User.password )
	}
	
	
	
}])



;
//var services = angular.module('userServices',[]).
angular.module('myApp').
service('userService',['$http','$rootScope',function($http, $rootScope){
	
	this.addUser = function(user){
		return $http({
			method: 'POST',
			url:'http://localhost:8080/forum/webapi/users',
			data: user
		})
	}
	
	this.getAllUsers = function(){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/forum/webapi/users'
		})
	}
	
	this.login = function(userID, password){
		return $http({
			method: 'POST',
			url: 'http://localhost:8080/forum/webapi/users/'+userID,
			data: password
		})
	}
	
	this.setRole = function(userID, role){
		return $http({
			method: 'PUT',
			url: 'http://localhost:8080/forum/webapi/users/'+ userID +'/'+ role,
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.searchUsers = function(search){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/forum/webapi/users/search/'+search
		})
	}
	
	this.saveForum = function(forumTitle){
		return $http({
			method: 'PUT',
			url:'http://localhost:8080/forum/webapi/users/saveForum/'+forumTitle,
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.saveTopic = function(forumTitle, topicTitle){
		return $http({
			method: 'PUT',
			url:'http://localhost:8080/forum/webapi/users/saveTopic/'+ forumTitle + '/' + topicTitle,
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.saveComment = function(forumTitle, topicTitle, id, comment){
		return $http({
			method: 'PUT',
			url:'http://localhost:8080/forum/webapi/users/saveComment/'+forumTitle + '/' + topicTitle + '/' + id,
			data: comment,
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.getSavedForums = function(){
		return $http({
			method: 'GET',
			url:'http://localhost:8080/forum/webapi/users/savedForums',
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.getSavedTopics = function(){
		return $http({
			method: 'GET',
			url:'http://localhost:8080/forum/webapi/users/savedTopics',
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.getSavedComments = function(){
		return $http({
			method: 'GET',
			url:'http://localhost:8080/forum/webapi/users/savedComments',
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	
}]);
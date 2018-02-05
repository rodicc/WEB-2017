angular.module('myApp')
.service('subForumService',['$http', '$rootScope', function($http, $rootScope){
	
	this.getAllSubForums = function(){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/forum/webapi/subforums',
		})
	}
	
	
	this.addSubForum = function(subForum){
		return $http({
			method: 'POST',
			url: 'http://localhost:8080/forum/webapi/subforums',
			data: subForum,
			headers: {
				ownerID: $rootScope.User.userID,
				ownerPass: $rootScope.User.password
			}
		})
	}
	
	this.deleteSubForum = function(title){
		return $http({
			method: 'DELETE',
			url: 'http://localhost:8080/forum/webapi/subforums/'+title,
			headers:{
				userID: $rootScope.User.userID,
				userPass:$rootScope.User.password
			}
		})
	}
	
	this.getSubForum = function(title){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/forum/webapi/subforums/'+title,
			
		})
	}
	
	this.searchSubForums = function(search){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/forum/webapi/subforums/search',
			headers:{
				titleSearch: search.title,
				descSearch: search.desc,
				modSearch: search.mod
			}
		})
	}
	
	this.getTrendingTopics = function(){
		return $http({
			method:"GET",
			url:'http://localhost:8080/forum/webapi/subforums/trending'
		})
	}
}]);
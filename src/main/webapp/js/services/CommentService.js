angular.module("myApp")
.service('commentService',['$http','$rootScope', function($http, $rootScope){
	
	this.addComment = function(parentForumTitle, parentTopicTitle, newComment){
		
		return $http({
			method:'POST',
			url:'http://localhost:8080/forum/webapi/'+ parentForumTitle +'/topics/'+parentTopicTitle+'/comment',
			data: newComment,
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.removeComment = function(parentForumTitle, parentTopicTitle, id){
		
		return $http({
			method:'DELETE',
			url:'http://localhost:8080/forum/webapi/'+ parentForumTitle +'/topics/'+parentTopicTitle+'/comment/'+id ,
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.editComment = function(parentForumTitle, parentTopicTitle, id, newContent){
		
		return $http({
			method:'PUT',
			url:'http://localhost:8080/forum/webapi/'+ parentForumTitle +'/topics/'+parentTopicTitle+'/comment/'+id ,
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			},
			data: newContent
		
		})
	}
	
	this.commentVote = function(parentForumTitle, parentTopicTitle, id, vote){
		return $http({
			method:'POST',
			url:'http://localhost:8080/forum/webapi/'+ parentForumTitle +'/topics/'+parentTopicTitle+'/comment/'+id+'/vote',
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			},
			data: vote
			
		})
	}
	
	
	
}])
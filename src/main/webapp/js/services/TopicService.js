angular.module('myApp')
.service('topicService',[ '$http', '$rootScope', function( $http, $rootScope){
	
	
	
	this.addTopic = function (parentForum, newTopic){
		
		return $http({
			method: 'POST',
			url: 'http://localhost:8080/forum/webapi/'+ parentForum +'/topics',
			data: newTopic,
			headers: {
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.getAllTopics = function (parentForum){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/forum/webapi/'+ parentForum +'/topics',
			
		})
	}
	
	this.getTopic = function (parentForum, topicTitle){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/forum/webapi/'+ parentForum +'/topics/'+topicTitle
			
		})
	}
	
	this.removeTopic = function (parentForum, topicTitle){
		return $http({
			method: 'DELETE',
			url:'http://localhost:8080/forum/webapi/'+ parentForum +'/topics/'+topicTitle,
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.editTopic = function (parentForum, topic, topicTitle){
		return $http({
			method: 'PUT',
			url: 'http://localhost:8080/forum/webapi/'+ parentForum +'/topics/'+topicTitle ,
			data: topic,
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.topicVote = function(parentForumTitle, parentTopicTitle, vote){
		return $http({
			method:'POST',
			url:'http://localhost:8080/forum/webapi/'+ parentForumTitle +'/topics/'+parentTopicTitle+'/vote',
			headers:{
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			},
			data: vote
			
		})
	}
	
	this.searchTopics = function (parentForumTitle, search){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/forum/webapi/'+ parentForumTitle +'/topics/search',
			headers:{
				titleSearch: search.title,
				contentSearch: search.content,
				authorSearch: search.author
			}
		})
	}
	
}])
angular.module("myApp")
.service('chatService', ['$http', '$rootScope', function($http, $rootscope){
	
	this.sendMessage = function(reciever, message){
		return $http({
			method: 'POST',
			url: 'http://localhost:8080/forum/webapi/messages/'+reciever,
			headers: {
				userID: $rootscope.User.userID,
				userPass: $rootscope.User.password
			},
			data: message
		})
	}
	
	this.getChat = function(reciever){
		return $http({
			method: 'GET',
			url: 'http://localhost:8080/forum/webapi/messages/'+reciever,
			headers: {
				userID: $rootscope.User.userID,
				userPass: $rootscope.User.password
			}
		})
	}
	
	
}])
angular.module("myApp")
.controller('chatController', ["$scope","$rootScope", "$anchorScroll", "$location",
	  "chatService", "userService", function($scope, $rootScope, $anchorScroll, $location, chatService, userService){
	
	$scope.newMessage = "";
	$scope.messages = [];
	$scope.reciever = {};
	$scope.users = {};
	$scope.search = "";
	$scope.savedForums = {};
	$scope.savedTopics = {};
	$scope.savedComments = {};
	
	userService.getAllUsers().then(function(response){
		$scope.users = response.data;
		
	})
	
	$scope.searchUsers = function(){
		var s = $scope.search;
		if(s == undefined || s == ""){
			s = "$";
		}
		
		userService.searchUsers(s).then(function(response){
			$scope.users = response.data;
			$scope.search = "";
		})
	}
	
	$scope.getChat = function(reciever){
		chatService.getChat(reciever).then(function(response){
			$scope.messages = response.data;
			$scope.reciever = reciever;
			$location.hash("end");
			$anchorScroll();
		})
	}
	
	$scope.sendMessage = function(){
		var message ={};
		message.sender = $rootScope.User.userID;
		message.reciever = $scope.reciever;
		message.content = $scope.newMessage;
		chatService.sendMessage($scope.reciever, message).then(function(){
			$scope.newMessage = "";
			$scope.messages.push(message);
			$location.hash("end");
			$anchorScroll();
		})
	}
	
	userService.getSavedComments().then(function(response){
		$scope.savedComments = response.data;
	})
	
	userService.getSavedTopics().then(function(response){
		$scope.savedTopics = response.data;
	})
	
}])
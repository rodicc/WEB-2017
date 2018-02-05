angular.module('myApp')
.controller('topicController',[ 'topicService', 'commentService','userService','reportService','$scope','$rootScope','$stateParams',
	function(topicService, commentService, userService, reportService, $scope, $rootScope, $stateParams){
	
	
	$scope.topic = {};
	$scope.newComment = {};
	$scope.report = {};
	$scope.commentToEdit = {};
	

	topicService.getTopic($stateParams.title, $stateParams.topic).then(function(response){
		$scope.topic = response.data;
	})
	
	$scope.removeTopic = function(){
		topicService.removeTopic($stateParams.title, $stateParams.topic);
	}
	
	$scope.topicVote = function(vote){
		topicService.topicVote($stateParams.title, $stateParams.topic, vote).then(function(response){
			if((vote == "+") && (response.data == 'true')){
				$scope.topic.likes++;
			}else if((vote == "-") && (response.data == 'true')){
				$scope.topic.disslikes++;
			}
		})
	}
	
	
	$scope.addComment = function(){
		commentService.addComment($stateParams.title, $stateParams.topic, $scope.newComment).then(function(response){
			topicService.getTopic($stateParams.title, $stateParams.topic).then(function(response){
				$scope.topic = response.data;
			})
		})
		$scope.newComment = {};
		
	}
	
	$scope.removeComment = function(commentID){
		commentService.removeComment($stateParams.title, $stateParams.topic, commentID).
		then(function(response){
			topicService.getTopic($stateParams.title, $stateParams.topic).then(function(response){
				$scope.topic = response.data;
			})
		})
	}
	
	$scope.editComment = function(commentID, newContent){
		commentService.editComment($stateParams.title, $stateParams.topic, commentID, newContent);
		modalComment.style.display = "none";
	}
	
	$scope.commentVote = function(commentID, vote){
		commentService.commentVote($stateParams.title, $stateParams.topic, commentID, vote).then(function(response){
			for(var i=0; i<$scope.topic.comments.length; i++){
				if($scope.topic.comments[i].commentID == commentID){
					if((vote == "+") && (response.data == 'true')){
						$scope.topic.comments[i].likes++;
					}else if((vote == "-") && (response.data == 'true')){
						$scope.topic.comments[i].disslikes++;
					}
				}
			}
		})
	}
	
	$scope.saveTopic = function(){
		userService.saveTopic($stateParams.title, $stateParams.topic);
	}
	
	$scope.saveComment = function(commentID, comment){
		userService.saveComment($stateParams.title, $stateParams.topic, commentID, comment);
	}
	
	
	
	$scope.editTopic = function(){
		topicService.editTopic($stateParams.title, $scope.topic, $scope.topic.title);
		modal.style.display = "none";	
	}
	
	
	
	$scope.setFile = function(element) {
		  $scope.currentFile = element.files[0];
		   var reader = new FileReader();

		  reader.onload = function(event) {
			 $scope.topic.imgString = event.target.result
		    $scope.$apply()

		  }
		  // when the file is read it triggers the onload event above.
		  reader.readAsDataURL(element.files[0]);
	}
	
	
	
	$scope.sendReport = function(){
		if($scope.report.commentID == undefined){
			$scope.report.subject = $stateParams.title+":"+$stateParams.topic;
		}
		else{
			$scope.report.subject = $stateParams.title+":"+$stateParams.topic+":"+$scope.report.commentID;
		}
		reportService.sendReport($scope.report);
		$scope.report = {};
		modalReport.style.display = "none";
	}
	
	
	
	
	var modal = document.getElementById('editTopicModal');
	var modalReport = document.getElementById('reportModal');
	var modalComment = document.getElementById('editCommentModal');

	$scope.editTopicModal = function() {
	    modal.style.display = "block";
	}
	
	$scope.editCommentModal = function(comment) {
		$scope.commentToEdit = comment;
		modalComment.style.display = "block"
	}

	$scope.reportModal = function(ID) {
		$scope.report.commentID = ID;
	    modalReport.style.display = "block";
	}
	
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	    else if(event.target == modalReport) {
	    	 modalReport.style.display = "none";
	    }
	    else if(event.target == modalComment) {
	    	modalComment.style.display = "none";
	    }
	}
	


	
}])


	
	
	

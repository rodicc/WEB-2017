angular.module('myApp')
.controller('subForumListController', ['subForumService','$scope', '$location', function(subForumService, $scope, $location){
	
	
	$scope.subForums = [];
	$scope.newForum = {};
	$scope.search = {
			title : "",
			mod : "",
			desc : ""
	};
	
	$scope.addSubForum = function(){
		subForumService.addSubForum($scope.newForum);
		$scope.subForums.push($scope.newForum);
		$scope.newForum = {};
		modal.style.display = "none";
	}
	
	
	subForumService.getAllSubForums().then(function(response){
		 $scope.subForums = response.data;
	});
	
	$scope.deleteSubForum = function(title){
		subForumService.deleteSubForum(title).then(function(){
			subForumService.getAllSubForums().then(function(response){
				$scope.subForums = response.data;
				})
		})
		
	}
	
	$scope.searchSubForums = function(){
		
		var s = $scope.search;
		
		if(s.title == "" || s.title == undefined)
			s.title = "$";
		if(s.mod == "" || s.mod == undefined)
			s.mod = "$";
		if(s.desc == "" ||s .desc == undefined)
			s.desc = "$";
		
		
		
		subForumService.searchSubForums(s).then(function(response){
				
			$scope.subForums = response.data;
				
			$scope.search.title = "";
			$scope.search.mod = "";
			$scope.search.desc = "";
		})
	}
	
	var modal = document.getElementById('addForumModal');

	var btn = document.getElementById("addForum");

	btn.onclick = function() {
	    modal.style.display = "block";
	}

	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
	
	
	
	
}])

.controller('subForumViewController', [ 'subForumService', 'topicService', 'userService','reportService', '$scope', '$rootScope', '$stateParams',
	function(subForumService, topicService, userService, reportService, $scope, $rootScope, $stateParams){
	
	
	$scope.forum = {};
	$scope.topics = [];
	$scope.search = {
			title : "",
			author : "",
			content : ""
	};
	$scope.newTopic = {
			imgString : ""
	};	
	
	
	subForumService.getSubForum($stateParams.title).then(function(response){
		$scope.forum = response.data;
		$scope.topics = $scope.forum.topics;
	});
	
	
	$scope.removeTopic = function(parentForumTitle, topicTitle){
		topicService.removeTopic(parentForumTitle, topicTitle).then(function(){
			subForumService.getSubForum($stateParams.title).then(function(response){
				$scope.forum = response.data;
				$scope.topics = $scope.forum.topics;
			});
		});
	}
	
	
	$scope.saveForum = function(){
		userService.saveForum($stateParams.title);
		
		
	}
	
	
	$scope.searchTopics = function(parentForumTitle){
		
		var s = $scope.search; 
		
		if(s.title == "" || s.title == undefined)
			s.title = "$";
		if(s.author == "" || s.author == undefined)
			s.author = "$";
		if(s.content == "" || s.desc == undefined)
			s.content = "$";
		
		
		
		topicService.searchTopics(parentForumTitle, s).then(function(response){
				
			$scope.search.title = "";
			$scope.search.author = "";
			$scope.search.content = "";
			$scope.topics = response.data;
			
		})
	}
	
	
	$scope.addTopic = function(){
		
		topicService.addTopic($stateParams.title, $scope.newTopic);
		$scope.topics.push($scope.newTopic);
		$scope.newTopic =  {
				imgString : ""
		};	
		
		modalTopic.style.display = "none";
	}
	
	$scope.sendForumReport = function(){
		$scope.report.subject = $stateParams.title;
		reportService.sendReport($scope.report);
		$scope.report = {};
		modalReport.style.display = "none";
	}
	
	$scope.setFile = function(element) {
		 $scope.currentFile = element.files[0];
		 var reader = new FileReader();

		  reader.onload = function(event) {
			 $scope.newTopic.imgString = event.target.result
		    $scope.$apply()

		  }
		  // when the file is read it triggers the onload event above.
		  reader.readAsDataURL(element.files[0]);
	}
	
	
	var modalTopic = document.getElementById('addTopicModal');

	var newTopic = document.getElementById("newTopic");
	
	var modalReport = document.getElementById('reportForumModal');
	
	$scope.reportModal = function() {
	    modalReport.style.display = "block";
	}
	
	newTopic.onclick = function() {
	    modalTopic.style.display = "block";
	}

	
	window.onclick = function(event) {
	    if (event.target == modalTopic) {
	        modalTopic.style.display = "none";
	    }
	    else if(event.target == modalReport){
	        modalReport.style.display = "none";
	    }
	}
	
	
	
	
	
}]);
angular.module("myApp")
.service('reportService',['$http', '$rootScope', function($http, $rootScope){
		
	this.sendReport = function(report){
		return $http({
			method:'POST',
			url:'http://localhost:8080/forum/webapi/report',
			data: report,
			headers: {
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.respondToReport = function(reportWrapper){
		return $http({
			method:'PUT',
			url:'http://localhost:8080/forum/webapi/report',
			data: reportWrapper,
			headers: {
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.getAllReportsFor = function(){
		return $http({
			method:'GET',
			url:'http://localhost:8080/forum/webapi/report',
			headers: {
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
	this.getReport = function(id){
		return $http({
			method:'GET',
			url: 'http://localhost:8080/forum/webapi/report/'+id,
			headers: {
				userID: $rootScope.User.userID,
				userPass: $rootScope.User.password
			}
		})
	}
	
}])
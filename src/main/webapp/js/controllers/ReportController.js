angular.module('myApp')

.controller('reportsController',['reportService','$rootScope','$scope','$stateParams',
	function(reportService, $rootScope, $scope, $stateParams){
	
	$scope.reportList = {};
	
	reportService.getAllReportsFor().then(function(response){
		$scope.reportList = response.data;
	})
	
	
	
}])

.controller('reportMngController',['reportService','$rootScope', '$scope',  '$stateParams',
	function(reportService, $rootScope, $scope, $stateParams){
	
	$scope.report = {};
	$scope.reportWrapper = {};
	
	reportService.getReport($stateParams.id).then(function(response){
		$scope.report = response.data;
	})
	
	
	$scope.respondToReport = function(wrapper){
		wrapper.report = $scope.report;
		reportService.respondToReport(wrapper);
		$scope.wrapper = {};
	}
	
}])
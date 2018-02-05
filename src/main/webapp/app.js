//var myApp = angular.module("myApp", ['ui.router','userControllers', 'userServices']);
var myApp = angular.module("myApp", ['ui.router']);



myApp.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){
	
	$stateProvider
	
	.state('welcome',{
		url: '/',
		templateUrl : 'view/welcomePage.html',
		controller : 'userController'
	})
	
	
	.state('signup',{
		url: '/signup',
		templateUrl : 'view/signup.html',
		controller : 'userController'
	})
	
	.state('login',{
		url: '/login',
		templateUrl : 'view/login.html',
		controller : 'userController'
	})
	
	.state('user_mng',{
		url: '/user_mng',
		templateUrl: 'view/users.html',
		controller : 'userMngController',
		loginRequired : true
	})
	
	.state('subForums', {
		url:'/subForums',
		templateUrl:'view/subForum.html',
		controller: 'subForumListController'
	
	})
	
	.state('userPage',{
		url:'/home',
		templateUrl: 'view/userPage.html',
		controller: 'chatController',
		loginRequired : true
	})
	
	.state('reports', {
		url:'/reports',
		templateUrl:'view/reportView.html',
		controller: 'reportsController',
		loginRequired : true
	
	})
	
	.state('report_mng', {
		url:'/reports/:id',
		templateUrl:'view/reportMng.html',
		controller: 'reportMngController',
		loginRequired : true
	
	})
	
	
	.state('subForum',{
		url:'/:title',
		templateUrl:'view/forumView.html',
		controller:'subForumViewController'
	})
	
	.state('topic',{
		url: '/:title/:topic',
		templateUrl:'view/topicView.html',
		controller: 'topicController'
	})
	
	$urlRouterProvider.otherwise('/');
	
	
	
	
	
	
}])

.run(function($rootScope, $location) {
    $rootScope.$on( "$stateChangeStart", function(event, next, current) {
     if ($rootScope.loggedIn == null) {
        // no logged user, redirect to /login
        if ( next.templateUrl === 'view/signup.html' ||
        		 next.templateUrl === 'view/login.html' ||
        		 next.templateUrl === 'view/subForum.html' ||
        		 next.templateUrl === 'view/forumView.html' ||
        		 next.templateUrl === 'view/topicView.html' ||
        		 next.templateUrl === 'view/welcomePage.html') {
        }else {
          $location.path("/login");
        }
      }/*var postLogInRoute;
      //if login required and you're logged out, capture the current path
      if (next.loginRequired && !$rootScope.loggedIn) {
        postLogInRoute = $location.path();
        $location.path('/login').replace();
      } else if (postLogInRoute && $rootScope.loggedIn) {
    	  //once logged in, redirect to the last route and reset it
        $location.path(postLogInRoute).replace();
        postLogInRoute = null;
      }
      */
      
      
    });
  });
	
	
	
	
        	

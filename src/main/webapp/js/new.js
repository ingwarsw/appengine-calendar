var app = angular.module('gapiExample', ['dinoboff.gapi']);
		
		app.run(function($rootScope, dinoGapiClientLoader) {
				// note that $scope.gapi will only be a promise
				$rootScope.gapi = dinoGapiClientLoader({
					name: 'visits',
					version: 'v1',
					root: 'http://localhost:8080/_ah/api'
				});
			});

		app.filter('formatDate', function(){
			return function (date) {
				var d = new Date(date);
				
				return d.toLocaleTimeString();
			};
		});

		app.controller('GuestbookCtrl', function ($scope, $timeout, dinoGapiClientLoader) {
			var gapi,
				updateTimer;

			// Flag to disable the new message form
			$scope.disabled = true;

			// Method to update the list of message.
			// 
			// Query the server for messages and set timer to repeat
			// the query every 5s
			$scope.list = function () {
				if (updateTimer) {
					$timeout.cancel(updateTimer);
				}

				gapi.client.guestbook.messages.list().execute(function(resp) {
					
					if (resp.result && resp.result.messages) {
						$scope.messages = resp.result.messages;
						$scope.$apply();
					}

					// should us google app engine channel instead
					updateTimer = $timeout($scope.list, 5000);
				});
			};

			// Method to insert a new message.
			// 
			// Update the list of message after a insert
			$scope.insert = function() {
				if(!gapi) {
					return;
				}
				
				message = {
					"createdAt" : new Date(),
					"createdBy" : $scope.createdBy,
					"content" : $scope.content
				};

				$scope.disabled = true;
				gapi.client.guestbook.messages.insert(message).execute(function(){
					$scope.disabled = false;
					$scope.content = "";
					$scope.list();
				});
			};

			// Load the google end point client
			// 
			// enable creation of new message, and load existing message
			// when the client is ready.
			$scope.gapi.then(function (loadedApi) {
				gapi = loadedApi;
				$scope.disabled = false;
				$scope.list();
			});
		});
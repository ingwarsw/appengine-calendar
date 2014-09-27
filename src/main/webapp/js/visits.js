'use strict';

function init() {
    window.ginit();
}

//var visitsApp = angular.module('visitsApp', ['ui.calendar', 'ui.bootstrap']);
var visitsApp = angular.module('visitsApp', ['ui.bootstrap']);

visitsApp.controller('VisitsCtrl', function($scope) {

    window.ginit = function() {
        $scope.$apply($scope.load_visits_lib);
    };

    $scope.today = function() {
        $scope.start = new Date();
    };
    $scope.today();

    // Disable weekend selection
    $scope.disabled = function(date, mode) {
        return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
    };
    
    $scope.open = function($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };

    $scope.count = 10;

    $scope.all_days = [
        {name: 'Mon', selected: true},
        {name: 'Tue', selected: false},
        {name: 'Wed', selected: false},
        {name: 'Thu', selected: true},
        {name: 'Fri', selected: false},
        {name: 'Sat', selected: false},
        {name: 'Sun', selected: false}
    ];
    
    $scope.calculate = function() {
        $scope.query = {
            start: $scope.start,
            count: $scope.count,
            days:  $scope.all_days
        };
        console.log("Plan to send: " + $scope.query.start + " : " + $scope.query.count + ":" + $scope.query.days);
        gapi.client.visits.plan($scope.query).execute(function(resp) {
            $scope.plan = resp;
            $scope.$apply();
            console.log("recv: " + resp);
        });
    };

    $scope.load_visits_lib = function() {
        gapi.client.load('visits', 'v1', function() {
            $scope.is_backend_ready = true;
            console.log("Api loaded");
            $scope.$apply();
        }, '/_ah/api');
        $scope.is_backend_ready = true;
    };

    $scope.eventSource = {
        url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
        className: 'gcal-event', // an option!
        currentTimezone: 'America/Chicago' // an option!
    };
});


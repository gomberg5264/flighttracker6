angular.module('ticketApp', []).controller('TicketController', ['$scope', '$http', function($scope, $http){
    const base_url = '/api/search/vacation/';
    $scope.flights = [{"price":1264,"destination":"Taiwan","flightDetail":{"outboundAirline":"China Southern","inboundAirline":"AirAsia X","destination":"Taipei Sung Shan"}},{"price":234,"destination":"Turkey","flightDetail":{"outboundAirline":"Turkish Airlines","inboundAirline":"Turkish Airlines","destination":"Istanbul Ataturk"}},{"price":509,"destination":"Egypt","flightDetail":{"outboundAirline":"Germania","inboundAirline":"flythomascook","destination":"Hurghada"}},{"price":508,"destination":"United Arab Emirates","flightDetail":{"outboundAirline":"KLM","inboundAirline":"Norwegian","destination":"Dubai"}},{"price":1134,"destination":"Japan","flightDetail":{"outboundAirline":"KLM","inboundAirline":"KLM","destination":"Tokyo Narita"}},{"price":169,"destination":"Finland","flightDetail":{"outboundAirline":"Air France","inboundAirline":"Air France","destination":"Helsinki Vantaa"}},{"price":193,"destination":"Spain","flightDetail":{"outboundAirline":"Germania","inboundAirline":"Germania","destination":"Gran Canaria Las Palmas"}},{"price":268,"destination":"Luxembourg","flightDetail":{"outboundAirline":"KLM","inboundAirline":"KLM","destination":"Luxembourg"}},{"price":194,"destination":"Romania","flightDetail":{"outboundAirline":"Air France","inboundAirline":"Air France","destination":"Bucharest Otopeni"}},{"price":170,"destination":"Sweden","flightDetail":{"outboundAirline":"Air France","inboundAirline":"Air France","destination":"Stockholm Arlanda"}},{"price":179,"destination":"Denmark","flightDetail":{"outboundAirline":"KLM","inboundAirline":"KLM","destination":"Copenhagen"}},{"price":181,"destination":"Italy","flightDetail":{"outboundAirline":"Air France","inboundAirline":"Air France","destination":"Rome Fiumicino"}},{"price":398,"destination":"Croatia","flightDetail":{"outboundAirline":"Lufthansa","inboundAirline":"Croatia Airlines","destination":"Zadar"}},{"price":335,"destination":"Greece","flightDetail":{"outboundAirline":"Air France","inboundAirline":"KLM","destination":"Athens International"}},{"price":231,"destination":"Ireland","flightDetail":{"outboundAirline":"KLM","inboundAirline":"KLM","destination":"Dublin"}},{"price":167,"destination":"Netherlands","flightDetail":{"outboundAirline":"KLM","inboundAirline":"KLM","destination":"Amsterdam"}},{"price":172,"destination":"Switzerland","flightDetail":{"outboundAirline":"Air France","inboundAirline":"Air France","destination":"Basel Mulhouse Freiburg"}},{"price":166,"destination":"France","flightDetail":{"outboundAirline":"Air France","inboundAirline":"Air France","destination":"Nice"}},{"price":34,"destination":"United Kingdom","flightDetail":{"outboundAirline":"Ryanair","inboundAirline":"Ryanair","destination":"London Stansted"}},{"price":171,"destination":"Czech Republic","flightDetail":{"outboundAirline":"Air France","inboundAirline":"Air France","destination":"Prague"}},{"price":228,"destination":"Germany","flightDetail":{"outboundAirline":"Lufthansa","inboundAirline":"Lufthansa","destination":"Frankfurt am Main"}}];

    $scope.ticket_page = false;
    $scope.main_page = true;
    $scope.universityInput = '';

    $scope.university_map = {
       'Jacobs University': 1,
       'Uni Bremen': 2,
       'Hamburg University': 3
    };

    //$scope.flights = [];

    //$scope.possible_days = [];
    $scope.possible_days = [
        {start_date: '2018-8-25',
        end_date: '2018-9-1'}
    ];

<<<<<<< HEAD
=======
    $scope.ticket_page = true;
    $scope.main_page = false;
    $scope.university_input = '';
>>>>>>> 16bc9da4176c26c00f2e12c8695c9ff4cbff5158


    $scope.university_select = function(universityInput) {
        console.log(universityInput);
        let uni_id = $scope.university_map[universityInput];
        if(uni_id === undefined){
            console.log("oops, not found.")
            uni_id = 1;
        }
        $http({method: 'GET', url: '/api/search/university/' + uni_id.toString()}).then(function(resp){
               $scope.possible_days = resp.data;
               $scope.main_page = false;
               $scope.ticket_page = true;
        }, function (reason) { console.log(reason); });
    };

    $scope.select_day = function(day) {
        $scope.flights = day.destinations;
    };



}]);

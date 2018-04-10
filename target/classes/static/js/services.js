sdp.service('getService', function($http, $q) {

    this.promiseGet = function (endpoint) {
        var deferred = $q.defer();
        $http.get(endpoint)
            .then(function (data) {
                deferred.resolve(data);
            });
        return deferred.promise;
    };
});
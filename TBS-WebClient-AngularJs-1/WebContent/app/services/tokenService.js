TBSApp.service('tokenService', function () {
    var token;
    var login;
    return {
        getToken: function () {
            return token;
        },
        setToken: function(value) {
        	token = value;
        },
        getLogin: function () {
            return login;
        },
        setLogin: function(value) {
        	login = value;
        }
    };
});
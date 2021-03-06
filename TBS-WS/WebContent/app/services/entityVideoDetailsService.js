TBSApp.service('entityVideoDetailsService', function () {
    var entityVideoDTO;
    var index;
    var positionX;
    var positionY;
    var translationsAround = null;
    return {
        getEntityVideoDTO: function () {
            return entityVideoDTO;
        },
        setEntityVideoDTO: function(value) {
        	entityVideoDTO = value;
        },
        getIndex: function () {
        	return index;
        },
        setIndex: function(value) {
        	index = value;
        },
        getPositionX: function() {
        	return positionX;
        },
        setPositionX: function(value) {
        	positionX = value;
        },
        getPositionY: function() {
        	return positionY;
        },
        setPositionY: function(value) {
        	positionY = value;
        },
        setTranslationsAround: function(value) {
        	translationsAround = value;
        },
        getTranslationsAround: function() {
        	return translationsAround;
        }
        
    };
});
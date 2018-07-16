$(document).ready(function () {

    var ELEMENTS = {
        LOG: '.jsLog'
    };

    var
        FIVE_SECOND_IN_MILLIS = 5000;
        $log = $(ELEMENTS.LOG);

    setInterval(getUpdates, FIVE_SECOND_IN_MILLIS);

    function getUpdates() {
        $.ajax({
            url: 'orchestra/update',
            type: 'GET',
            contentType: "application/json",
            success: function (elements) {
                $log.empty();
                for(var i = (Object.keys(elements).length - 1); i >= 0 ; i--){
                    $log.append($("<div></div>").text(elements[i]));
                }
            }
        });
    }

});
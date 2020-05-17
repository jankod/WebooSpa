
weboo = {
    onSubmit(event) {
        console.log("form submit", event);
        return false;
    }
};
weboo.onClick = function (event, widgetId, callbackFunction) {

    console.log("call fucntion ", callbackFunction);
    if(typeof window[callbackFunction] !== 'function') {
        console.log("'"+callbackFunction + "' is not function!");
        return;
    }

    if(callbackFunction) {
        window[callbackFunction](event);
        return;
    }

    console.log("onClick " + widgetId, event);
    var $data = {};
    $data.widgetId = widgetId;
    $data.type = event.type;
    console.log("saljem data ", $data);
    weboo.sendToServer($data, 'click');

};
weboo.sendToServer = function (data, eventType) {
    $.ajax({
        headers: {
            eventType: eventType
        },
        url: '/ajax',
        method: 'POST',
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (data) {
            console.log("success send data " , data);
            if(data.functionName) {
                window[data.functionName](data.parameters);
            }

        },
    })
}

$(function () {
    $.ajaxSetup({
        headers: {'tabId': TAB_ID}
    });

    $("a").on('click', function (e) {
        e.stopPropagation();
        let goto = $(this).data('goto');
        if(goto == null) {
            return ;
        }
        let url = "/page?name=" + goto;
        $("#content").load(url, '', function (response, status, xhr) {
            console.log("load finish");
        });
        console.log(url + " " + goto);
    });

    window.addEventListener("unload", function logData() {
        navigator.sendBeacon("/ajax", "close");
    });

    console.log("dela 22 22");


});
$(function () {
    $.ajaxSetup({
        headers: {'tabId': TAB_ID}
    });

    $("a").on('click', function (e) {
        e.stopPropagation();
        let goto = $(this).data('goto');

        let url = "/page?name=" + goto;
        $("#content").load(url, '', function (response, status, xhr) {
            console.log("load finish");
        });
        console.log(url + " " + goto);
    });
    console.log("dela");


});
weboo = {};
weboo.onClick = function (event, widgetId) {
    console.log("onClick " + widgetId, event);
};

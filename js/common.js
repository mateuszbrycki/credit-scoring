$(document).ready(function () {

    $(document).on('submit', '#credit-scoring-form', function (e) {
        var frm = $('#credit-scoring-form');
        e.preventDefault();

        var data = {};

        $.each(this, function (i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        console.log(JSON.stringify(data));

        if (frm.valid()) {
            $.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: "POST",
                url: "http://localhost:8080/api/credit",
                data: JSON.stringify(data),
                success: function (callback) {
                    console.log(callback);
                },
                error: function (callback) {
                    console.log(callback);
                }
            });
        }
    });

});
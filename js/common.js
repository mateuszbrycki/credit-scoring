function appendResult(data, form) {


    var resultTable = document.getElmentById("result-table");
    var row = document.createElement("tr");

    var income = document.createElement("td");
    income.appendChild(new Text(form["income"]));

    var age = document.createElement("td");
    age.appendChild(new Text(form["age"]));

    var loan = document.createElement("td");
    loan.appendChild(new Text(form["loan"]));

    var lti = document.createElement("td");
    lti.appendChild(new Text(form["lti"]));

    var result = document.createElement("td");
    if(data.neuralResponse > 0 && data.fuzzyResponse > 0.5) {
        result.appendChild(new Text("Approved"));
        row.setAttribute("class", "success");
    } else {
        result.appendChild(new Text("Rejected"));
        row.setAttribute("class", "danger");
    }

    var comparision = document.createElement("td");
    income.appendChild(new Text(data.neuralResponse + "/" + data.fuzzyResponse));

    row.appendChild(income);
    row.appendChild(age);
    row.appendChild(loan);
    row.appendChild(lti);
    row.appendChild(result);
    row.appendChild(comparision);

    resultTable.appendChild(row);

}

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
                    appendResult(callback, data);
                },
                error: function (callback) {
                    console.log(callback);
                }
            });
        }
    });

});
let x;
let y;
let r;

function handleForm() {
    clearError();
    if (validateForm()) {
        sendRequest(x, y, r)
    }
    return false;
}

function sendRequest(x, y, r) {
    $.ajax({
        url: "./controller",
        type: "GET",
        data: {'x': x, 'y': y, 'r': r},
        success: function (response) {
            // alert("succ: " + response);
            window.location.reload(true);
            // let data = JSON.parse(response);
            // addNewRow(data.result, data.x, data.y, data.r, data.current, data.execution);
        },
        error: function (response) {
            alert("err: " + response);
        }
    });
}

function validateForm() {
    let x = checkX();
    let y = checkY();
    let r = checkR();
    return x && y && r;

}

function checkX() {
    if (!validateRadio("X")) {
        showMessage("Значение X не выбрано")
        return false;
    }
    x = getRadio("X").eq(0).val();
    return true;
}

function checkY() {
    let yField = $('#y-text');
    let yVal = yField.val().replace(',', '.');

    if (yVal === "") {
        showMessage("Значение Y не введено")
        return false;
    } else if (yVal.match(/^[+-]0$/) || yVal.match(/^[+-]0\.0+$/) ||
        yVal.match(/^[+-]?00+$/) || yVal.match(/^[+-]?00+\.[0-9]+$/) ||
        !yVal.match(/^-?[0-9]+\.[0-9]+$/) && !yVal.match(/^-?[0-9]+$/)) {
        showMessage("Значение Y должно быть числом");
        return false;
    }
    if ((yVal <= -5 || yVal >= 5) && ((yVal.substring(0, 2) !== "4.") && (yVal.substring(0, 3) !== "-4.") && (yVal.substring(0, 2) !== "4,") && (yVal.substring(0, 3) !== "-4,"))) {
        showMessage("Значение Y не входит в интервал (-5,5)");
        return false;
    } else {
        if (yVal.substring(0, 1) === "-") {
            y = yVal.substring(0, 17);
        } else {
            y = yVal.substring(0, 16);
        }
        return true;
    }
}

function checkR() {
    let checkR = false;
    $('input[type=checkbox]').each(function () {
        if ($(this).prop('checked')) {
            checkR = true;
        }
    });
    if (!checkR) {
        showMessage("Значение R не выбрано");
        return false;
    }
    r = $('#r :checked').val();
    return true;
}

function validateRadio(name) {
    return getRadio(name).length === 1;
}

function getRadio(name) {
    return $(`:input[name = "${name}"]:checked`);
}

function showMessage(message) {
    let errorMessage = document.createElement("div");
    errorMessage.textContent = message;
    $('#errors').append(errorMessage);
}

function clearError() {
    $('#errors').empty();
}

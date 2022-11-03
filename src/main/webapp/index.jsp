<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>webLab2</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/html.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/form.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<table class="creator">
    <tr>
        <td class="student">ФИО</td>
        <td class="group">Группа</td>
        <td class="variant">Вариант</td>
    </tr>
    <tr>
        <td class="student">Чернова Анна Ивановна</td>
        <td class="group">P32301</td>
        <td class="variant">3116</td>
    </tr>
</table>
<canvas id="areas" width="800" height="800" style="float: right; margin-right: 2%">
    <img id="areas_picture" src="graph/newAreas.svg" alt="areas picture">
</canvas>
<form id="main-form" method="get" onsubmit="return handleForm()">
    <table id="parameter">
        <tr>
            <td class="text-param">
                Значение X:
            </td>
            <td id="x" class=elements>
                <label>
                    <input type="radio" name="X" value="-5">
                    -5
                </label>
                <label>
                    <input type="radio" name="X" value="-4">
                    -4
                </label>
                <label>
                    <input type="radio" name="X" value="-3">
                    -3
                </label>
                <label>
                    <input type="radio" name="X" value="-2">
                    -2
                </label>
                <label>
                    <input type="radio" name="X" value="-1">
                    -1
                </label>
                <label>
                    <input type="radio" name="X" value="0">
                    0
                </label>
                <label>
                    <input type="radio" name="X" value="1">
                    1
                </label>
                <label>
                    <input type="radio" name="X" value="2">
                    2
                </label>
                <label>
                    <input type="radio" name="X" value="3">
                    3
                </label>
            </td>
        </tr>
        <tr>
            <td class="text-param">
                Значение Y:
            </td>
            <td id="y" class="elements">
                <label>
                    <input type="text" name="Y" id="y-text" size=100% placeholder="Введите число в интервале (-5; 5)"
                           maxlength="50">
                </label>
            </td>
        </tr>
        <tr>
            <td class="text-param">
                Значение R:
            </td>
            <td id="r" class="elements">
                <input type="checkbox" name="R" value="1" class="chb">
                1
                <input type="checkbox" name="R" value="1.5" class="chb">
                1.5
                <input type="checkbox" name="R" value="2" class="chb">
                2
                <input type="checkbox" name="R" value="2.5" class="chb">
                2.5
                <input type="checkbox" name="R" value="3" class="chb">
                3
            </td>
        </tr>
    </table>
    <table id="submit">
        <tr id="button" class="elements">
            <td>
                <input type="submit" value="Отправить">
            </td>
        </tr>
        <p>
        <div id="errors"></div>
    </table>
</form>
<table id="response_table">
    <thead>
    <td>X</td>
    <td>Y</td>
    <td>R</td>
    <td>Текущее время</td>
    <td>Время работы скрипта</td>
    <td>Результат</td>
    </thead>
    <tbody id="response_body">
    <jsp:useBean id="table" scope="session" class="chernova.weebo.beans.TableBean"/>
    <c:forEach var="row" items="${table.responses}">
        <tr>
            <td>
                    ${row.x}
            </td>
            <td>
                    ${row.y}
            </td>
            <td>
                    ${row.r}
            </td>
            <td>
                    ${row.currentTime}
            </td>
            <td>
                    ${row.executionTime} ms
            </td>
            <td>
                    ${row.result}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script src="js/checkboxSwitch.js"></script>
<script src="js/main.js"></script>
<script src="js/clickHandler.js"></script>
<script src="js/areasDrawer.js"></script>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2>
    <spring:message code="persons.rest.search.title"/>
</h2>

<div class="person">
    <spring:url value="/rest-search/perform" var="searchUrl"/>
        <table>
            <tr>
                <th>
                    <spring:message code="label.Criteria.fieldname"/> :
                </th>

                <td>
                    <label>
                        <spring:message code="label.Person.firstname"/>
                    </label>
                </td>
                <td></td>
            </tr>
            <tr>
                <th>
                     <span class="man">*</span> <spring:message code="label.Criteria.fieldvalue"/> :
                </th>
                <td><input name="fieldValue" id="fieldValue"/>
                </td>
                <td>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colpan="2">
                    <input type="checkbox" id="exactMatch" />
                    <spring:message code="label.Criteria.exactmatch"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input id="searchButton" type="submit" value="<spring:message code="command.search"/>"/>
                </td>
                <td>
                    <input id="cancelButton" type="submit" value="<spring:message code="command.cancel"/>"/>
                </td>
            </tr>
            <tr>
                <td colspan="3"><label class="error" id="noResults"/></td>
            </tr>
        </table>
</div>

<div id="resultDiv">
    <table>
        <thead>
        <tr>
            <td>
                <spring:message code="label.ic.pnc"/>
            </td>
            <td>
                <spring:message code="label.Person.firstname"/>
            </td>
            <td>
                <spring:message code="label.Person.lastname"/>
            </td>
        </tr>
        </thead>
        <tbody id="resultTable">
        </tbody>
    </table>
</div>

<script type="text/javascript">

    $(document).ready(function () {
        $("#resultDiv").hide();
        $("#noResults").hide();

        $("#searchButton").click(
                function (event) {
                    event.preventDefault();
                    sendAjaxReq();
                });

        $("#cancelButton").click(
                function (event) {
                    event.preventDefault();
                    $("#resultDiv").fadeOut('fast');
                    $("#noResults").fadeOut('fast');
                    $("#resultTable").empty();
                });
    });

    function displayResults(results) {
        console.log(results.length);
        if (results.length == 0) {
            $("#noResults").text("No results for search");
            $("#resultTable").empty();
            $("#resultDiv").fadeOut('fast');
            $("#noResults").fadeIn('fast');
        } else {
            $("#noResults").fadeIn('fast');
            $("#noResults").hide();
            results.forEach(function(person){
                $("#resultTable").append('<tr><td><a href="#" onclick="getPersonDetails(\''+ person.identityCard.pnc +'\')">'
                        + person.identityCard.pnc +'</a></td>' +
                        '<td>'+ person.firstName+ '</td>' + '<td>'+ person.lastName+ '</td>' +
                        '</tr>');
            });
            $("#resultDiv").fadeIn('fast');
        }
    }

    function sendAjaxReq(){
        var fieldValue = $("#fieldValue").val();
        var exactMatch = $("#exactMatch").is(':checked');
        console.log('Criteria:' + fieldValue + ', ' + exactMatch);

        if (fieldValue != '') {
            $.postJSON("${searchUrl}",
                    JSON.stringify({'fieldName': 'firstName',
                    'fieldValue': fieldValue,
                    'exactMatch': exactMatch}))
            .done(function(results) {
                displayResults(results);
            }).fail(function (){
                alert("ERROR!");
            });
        }
    }

    //function is added tot the jQuery object so it can be called with $.postJSON
    $.postJSON = function(url, data, callback) {
        return $.ajax({
            'type': 'POST',
            'url': url,
            'contentType': 'application/json',
            'data': data,
            'dataType': 'json'
        });
    };

    function getPersonDetails(data) {
        $.getJSON('${personsUrl}/' + data, function(person) {
            var s = "=== Person Details ===\n\n";
            s += "First Name: " + person.firstName + "\n";
            s += "Last Name: " + person.lastName + "\n";
            s += "Date of birth: " + person.dateOfBirth + "\n";
            s += "Personal Numeric Code: " + person.identityCard.pnc + "\n";
            s += "Series: " + person.identityCard.series + "\n";
            s += "Number: " + person.identityCard.number;
            alert(s);
        });
    }
</script>
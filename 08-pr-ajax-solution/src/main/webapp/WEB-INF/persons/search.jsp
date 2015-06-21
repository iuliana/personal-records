<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>
    <spring:message code="persons.search.title"/>
</h2>

<div class="person">
    <spring:url value="/persons" var="personsUrl"/>
    <sf:form modelAttribute="criteriaDto" method="get">

        <table>
            <tr>
                <th>
                    <label for="fieldName">
                        <spring:message code="label.Criteria.fieldname"/> :
                    </label>
                </th>

                <td>
                    <sf:select path="fieldName" id="fieldName">
                        <sf:option value="firstName"><spring:message code="label.Person.firstname"/></sf:option>
                        <sf:option value="lastName"><spring:message code="label.Person.lastname"/></sf:option>
                        <sf:option value="dob"><spring:message code="label.Person.dob"/></sf:option>
                        <sf:option value="pnc"><spring:message code="label.ic.pnc"/></sf:option>
                        <sf:option value="hospital"><spring:message code="label.Hospital.name"/></sf:option>
                    </sf:select>
                </td>
                <td></td>
            </tr>
            <tr>
                <th>
                    <label for="fieldValue">
                        <span class="man">*</span> <spring:message code="label.Criteria.fieldvalue"/> :
                    </label>
                </th>
                <td><sf:input path="fieldValue" id="fieldValue"/>
                    <em><br><spring:message code="label.dateFormat.accepted"/></em>
                </td>
                <td><label class="error" id="fieldValueError"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colpan="2">
                    <sf:checkbox path="exactMatch" id="exactMatch"/>
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
    </sf:form>
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
    //global variables
    var fieldValueErrMessage = "${fieldValueErrMessage}";
    var fieldDateErrMessage = "${fieldDateErrMessage}";

    $(document).ready(function () {
        $(".error").hide();
        $("#resultDiv").hide();

        $("#searchButton").click(
                function (event) {
                    event.preventDefault();
                    var fieldName = $("#fieldName").val();
                    var fieldValue = $("#fieldValue").val();
                    var exactMatch = $("#exactMatch").is(':checked');
                    //console.log('Criteria:' + fieldName + ", " + fieldValue + ", " + exactMatch);

                    if (isValid(fieldName, fieldValue)) {
                        var params = {
                            fieldName: fieldName,
                            fieldValue: fieldValue,
                            exactMatch: exactMatch
                        }
                        $(".error").hide();
                        $.getJSON("${personsUrl}/ajax", params, displayResults);
                    }
                    return false;
                });


        $("#cleanButton").click(
                function (event) {
                    event.preventDefault();
                    $("#resultDiv").fadeOut('fast');
                    $(".error").hide();
                    $("#resultTable").empty();
                    return false;
                });
    });

    function displayResults(results) {
        if (results.length == 0) {
            $("#noResults").text("No results for search");
        } else {
            $("#resultTable").empty();
            results.forEach(function(person){
                $("#resultTable").append('<tr><td><a href="#" onclick="getPersonDetails(\''+ person.identityCard.pnc +'\')">'
                + person.identityCard.pnc +'</a></td>' +
                '<td>'+ person.firstName+ '</td>' + '<td>'+ person.lastName+ '</td>' +
                '</tr>');
            });
            $("#resultDiv").fadeIn('fast');
        }
    }

    function isValid(fieldName, fieldValue){
        var err='';
        //console.log(fieldName, fieldValue, fieldValue.length);
        if(fieldValue.length == 0) {
            err = fieldValueErrMessage;
        } else if(fieldName == 'dob' && !isValidDate(fieldValue)) {
            err = fieldDateErrMessage;
        }

        //console.log(err, err.length);
        if(err.length > 0) {
            $("#fieldValue").focus();
            $("#fieldValueError").text(err);
            $("#fieldValueError").fadeIn('fast');
            return false;
        }
        return true;
    }

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

    function isValidDate(dateString) {
        var regEx = /^\d{4}-\d{2}-\d{2}$/;
        return dateString.match(regEx) != null;
    }
</script>
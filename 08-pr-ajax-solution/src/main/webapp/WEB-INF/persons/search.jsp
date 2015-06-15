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
                <td><sf:errors cssClass="error" path="fieldValue"/>
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
                <td colspan="3"><sf:errors cssClass="error" path="noResults"/></td>
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
    $(document).ready(function () {
        $("#resultDiv").hide();
        $("#searchButton").bind('click', sendAjaxReq);
        $("#cleanButton").click (function(){
            event.preventDefault();
            $("#resultDiv").fadeOut('fast');
            $("#resultTable").empty();
        });
    });

    function sendAjaxReq(event) {
        event.preventDefault();
        var fieldName = $("#fieldName").val();
        var fieldValue = $("#fieldValue").val();
        var exactMatch = $("#exactMatch").is(':checked');
        console.log('Criteria:' + fieldName + ", " + fieldValue + ", " + exactMatch);
        if (fieldValue.length == 0) {
            $("#fieldValue").focus();
        } else {
            var params = {
                fieldName: fieldName,
                fieldValue: fieldValue,
                exactMatch: exactMatch
            }
            $.getJSON("${personsUrl}/ajax", params, displayResults);

        }
        return false;
    }

    function displayResults(results) {
        if (results.length == 0) {
            alert("No results for search");
        } else {
            $("#resultTable").empty();
            for (var i = 0; i < results.length; i++) {
                // First pass: $("#resultTable").append('<tr><td>' + results[i].number + '</td><td>' + results[i].name + '</td></tr>');
                $("#resultTable").append('<tr><td><a href="#" onclick="getPersonDetails(' + results[i].id + ')" >'
                + results[i].identityCard.pnc + '</a></td><td>' + results[i].firstName + '</td><td>' + results[i].lastName
                + '</td></tr>');
            }
            $("#resultDiv").fadeIn('fast');
        }
    }

    function getPersonDetails(data) {
        //TODO
    }
</script>
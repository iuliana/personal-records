<!-- Created by iuliana.cosmina on 3/29/15. -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

</body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>
    <fmt:message key="persons.search.title"/>
</h2>

<div class="person">
    <spring:url value="/persons/go" var="personsUrl"/>
    <!-- TODO 14. Configure the sf:form element properly -->
    <sf:form action="${personsUrl}" >

        <table>
            <tr>
                <th>
                    <label for="fieldName">
                        <fmt:message key="label.Criteria.fieldname"/> :
                    </label>
                </th>

                <td>
                    <sf:select path="fieldName">
                        <sf:option value="firstName"><fmt:message key="label.Person.firstname"/></sf:option>
                        <sf:option value="lastName"><fmt:message key="label.Person.lastname"/></sf:option>
                        <sf:option value="dob"><fmt:message key="label.Person.dob"/></sf:option>
                        <sf:option value="pnc"><fmt:message key="label.ic.pnc"/></sf:option>
                        <sf:option value="hospital"><fmt:message key="label.Hospital.name"/></sf:option>
                    </sf:select>
                </td>
                <td></td>
            </tr>
            <tr>
                <th>
                    <label for="fieldValue">
                        <span class="man">*</span> <fmt:message key="label.Criteria.fieldvalue"/> :
                    </label>
                </th>
                <td><sf:input path="fieldValue"/>
                    <em><br><fmt:message key="label.dateFormat.accepted"/></em>
                </td>
                <td><sf:errors cssClass="error" path="fieldValue"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colpan="2">
                    <sf:checkbox path="exactMatch"/>
                    <fmt:message key="label.Criteria.exactmatch"/>
                </td>
            </tr>
            <tr>
                <td>
                    <button id="searchButton" type="submit">
                        <fmt:message key="command.search"/>
                    </button>
                </td>
                <td>
                    <a href="<c:url value="/"/>">
                        <fmt:message key="command.cancel"/>
                    </a>
                </td>
            </tr>
            <tr>
                <td colspan="3"><sf:errors cssClass="error" path="noResults"/></td>
            </tr>
        </table>
    </sf:form>
</div>
</html>

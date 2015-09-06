<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>
    <spring:message code="persons.search.title"/>
</h2>

<div class="person">
    <spring:url value="/persons" var="personsUrl"/>
    <sf:form action="${personsUrl}" modelAttribute="criteriaDto" method="get">

        <table>
            <tr>
                <th>
                    <label for="fieldName">
                        <spring:message code="label.Criteria.fieldname"/> :
                    </label>
                </th>

                <td>
                    <sf:select path="fieldName">
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
                <td><sf:input path="fieldValue"/>
                    <em><br><spring:message code="label.dateFormat.accepted"/></em>
                </td>
                <td><sf:errors cssClass="error" path="fieldValue"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td colpan="2">
                    <sf:checkbox path="exactMatch"/>
                    <spring:message code="label.Criteria.exactmatch"/>
                </td>
            </tr>
            <tr>
                <td>
                    <button id="searchButton" type="submit">
                        <spring:message code="command.search"/>
                    </button>
                </td>
                <td>
                    <a href="<c:url value="/"/>">
                        <spring:message code="command.cancel"/>
                    </a>
                </td>
            </tr>
        </table>
        <div><sf:errors cssClass="error" path="noResults"/></div>
    </sf:form>
</div>
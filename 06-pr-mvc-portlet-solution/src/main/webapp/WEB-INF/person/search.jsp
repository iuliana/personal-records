<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<portlet:defineObjects />

<portlet:actionURL var="searchRequestURL">
    <portlet:param name="javax.portlet.action" value="search"/>
</portlet:actionURL>

<portlet:actionURL var="deletePersonUrl">
    <portlet:param name="javax.portlet.action" value="delete"/>
</portlet:actionURL>

<div id="person">
    <h3>
        <spring:message code="persons.search.title"/>
    </h3>

    <form action="${searchRequestURL}" method="post">

        <table>
            <tr>
                <th>
                    <spring:message code="label.Criteria.fieldname"/> :
                </th>

                <td>
                    <select id="fieldName" name="fieldName">
                        <option value="firstName" <c:if test="${fieldName eq 'firstName'}"> selected</c:if>>
                            <spring:message code="label.Person.firstname"/></option>
                        <option value="lastName" <c:if test="${fieldName eq 'lastName'}"> selected</c:if>>
                            <spring:message code="label.Person.lastname"/></option>
                        <option value="dob" <c:if test="${fieldName eq 'dob'}"> selected</c:if>>
                            <spring:message code="label.Person.dob"/></option>
                        <option value="pnc" <c:if test="${fieldName eq 'pnc'}"> selected</c:if>>
                            <spring:message code="label.ic.pnc"/></option>
                        <option value="hospital" <c:if test="${fieldName eq 'hospital'}"> selected</c:if>>
                            <spring:message code="label.Hospital.name"/></option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                    <spring:message code="label.Criteria.fieldvalue"/> :
                </th>
                <td valign="top">
                    <input type="text" id="fieldValue" name="fieldValue" value="${fieldValue}"/> <em><br><spring:message code="label.dateFormat.accepted"/></em>
                    <c:if test="${not empty error}">
                        <span class="error">${error}</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="checkbox" id="exactMatch" name="exactMatch"
                            <c:if test="${!empty exactMatch}"> checked="checked"</c:if>
                            />
                    <spring:message code="label.Criteria.exactmatch"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input id="searchButton" type="submit" value="<spring:message code='command.search'/>"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<c:if test="${ not empty persons}">
    <div id="resultContent">
        <h3>
            <spring:message code="persons.list.title"/>
        </h3>

        <div class="persons">
            <table>
                <thead>
                <tr>
                    <td>
                        <spring:message code="label.count"/>
                    </td>
                    <td>
                        <spring:message code="label.Person.firstname"/>
                    </td>
                    <td>
                        <spring:message code="label.Person.lastname"/>
                    </td>
                    <td>
                        <spring:message code="label.Person.dob"/>
                    </td>
                    <td>
                        <spring:message code="label.Person.gender"/>
                    </td>
                    <td>
                        <spring:message code="label.Hospital.name"/>
                    </td>
                    <td></td>
                </tr>
                </thead>
                <c:forEach var="person" items="${persons}">
                    <tr>
                        <td>
                                ${person.identityCard.pnc}
                        </td>
                        <td>
                                ${person.firstName}
                        </td>
                        <td>
                                ${person.lastName}
                        </td>
                        <td>
                            <fmt:formatDate value="${person.dateOfBirth}"/>
                        </td>
                        <td>
                                ${person.gender.initial}
                        </td>
                        <td>
                                ${person.hospital.name}
                        </td>
                        <td><a href="${deletePersonUrl}&personId=${person.id}"><spring:message code="command.delete"/></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>
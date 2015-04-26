<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="localization/global"/>
<portlet:defineObjects/>

<portlet:actionURL var="searchRequestURL">
    <portlet:param name="javax.portlet.action" value="search"/>
</portlet:actionURL>

<div id="searchContent">
    <h3>
        <fmt:message key="persons.search.title"/>
    </h3>

    <form action="${searchRequestURL}" method="post">

        <table>
            <tr>
                <th>
                    <fmt:message key="label.Criteria.fieldname"/> :
                </th>

                <td>
                    <select id="fieldName" name="fieldName">
                        <option value="firstName" <c:if test="${fieldName eq 'firstName'}"> selected</c:if>>
                            <fmt:message key="label.Person.firstname"/></option>
                        <option value="lastName" <c:if test="${fieldName eq 'lastName'}"> selected</c:if>>
                            <fmt:message key="label.Person.lastname"/></option>
                        <option value="dob" <c:if test="${fieldName eq 'dob'}"> selected</c:if>>
                            <fmt:message key="label.Person.dob"/></option>
                        <option value="pnc" <c:if test="${fieldName eq 'pnc'}"> selected</c:if>>
                            <fmt:message key="label.ic.pnc"/></option>
                        <option value="hospital" <c:if test="${fieldName eq 'hospital'}"> selected</c:if>>
                            <fmt:message key="label.Hospital.name"/></option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                    <fmt:message key="label.Criteria.fieldvalue"/> :
                </th>
                <td>
                    <input type="text" id="fieldValue" name="fieldValue" value="${fieldValue}"/>
                    <em><br><fmt:message key="label.dateFormat.accepted"/></em>
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
                    <fmt:message key="label.Criteria.exactmatch"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input id="searchButton" type="submit" value="<fmt:message key='command.search'/>"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<c:if test="${ not empty persons}">
    <div id="resultContent">
        <h3>
            <fmt:message key="persons.list.title"/>
        </h3>

        <div class="persons">
            <table>
                <thead>
                <tr>
                    <td>
                        <fmt:message key="label.count"/>
                    </td>
                    <td>
                        <fmt:message key="label.Person.firstname"/>
                    </td>
                    <td>
                        <fmt:message key="label.Person.lastname"/>
                    </td>
                    <td>
                        <fmt:message key="label.Person.dob"/>
                    </td>
                    <td>
                        <fmt:message key="label.Person.gender"/>
                    </td>
                    <td>
                        <fmt:message key="label.Hospital.name"/>
                    </td>
                </tr>
                </thead>
                <c:forEach var="person" items="${persons}">
                    <tr>
                        <td>
                                ${person.id}
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
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>
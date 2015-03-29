<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2>
    <fmt:message key="persons.list.title"/>
</h2>

<h4><fmt:message key="label.Person.db"/></h4>
<ul>
    <li>
        <spring:url var="xls" value=".xls"/>
        <a href="${xls}">XLS</a>
    </li>
    <li>
        <spring:url var="json" value=".json"/>
        <a href="${json}">JSON</a>
    </li>
</ul>

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
                    <spring:url var="showUrl" value="{id}">
                        <spring:param name="id" value="${person.id}"/>
                    </spring:url>
                    <a href="${showUrl}">${person.id}</a>
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
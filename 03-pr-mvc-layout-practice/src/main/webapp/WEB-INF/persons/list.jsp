<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>
    <spring:message code="persons.list.title"/>
</h2>

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
                    <!-- TODO 7. Put a link on this value pointing to the method developed for TODO6-->
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
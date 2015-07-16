<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>
    <spring:message code="person.title"/>
</h2>

<div class="person">
    <table>
        <tr>
            <th><spring:message code="label.Person.firstname"/></th>
            <td>${person.firstName}</td>
        </tr>
        <tr>
            <th><spring:message code="label.Person.middlename"/></th>
            <td>${person.middleName}</td>
        </tr>
        <tr>
            <th><spring:message code="label.Person.lastname"/></th>
            <td>${person.lastName}</td>
        </tr>
        <tr>
            <th><spring:message code="label.Person.dob"/></th>
            <td><fmt:formatDate value="${person.dateOfBirth}"/></td>
        </tr>

        <tr>
            <th><spring:message code="label.Person.gender"/></th>
            <td>${person.gender.initial}</td>
        </tr>
        <tr>
            <th><spring:message code="label.Hospital.name"/></th>
            <td>${person.hospital.name}</td>
        </tr>

        <tr>
            <th><spring:message code="label.ic.pnc"/></th>
            <td>${person.identityCard.pnc}</td>
        </tr>
        <tr>
            <th><spring:message code="label.ic.series"/>:<spring:message code="label.ic.number"/></th>
            <td>${person.identityCard.series}:${person.identityCard.number}</td>
        </tr>
        <tr>
            <th><spring:message code="label.ic.address"/></th>
            <td>${person.identityCard.address}</td>
        </tr>
        <tr>
            <th><spring:message code="label.ic.validity"/></th>
            <td><fmt:formatDate value="${person.identityCard.emittedAt}"/> - <fmt:formatDate value="${person.identityCard.expiresAt}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <spring:url var="editUrl" value="{id}/edit">
                    <spring:param name="id" value="${person.id}" />
                </spring:url>
                <a href="${editUrl}"><spring:message code="command.edit" /></a>
            </td>
        </tr>
    </table>
</div>
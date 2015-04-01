<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2>
    <fmt:message key="person.title"/>
</h2>

<div class="person">
    <table>
        <tr>
            <th><fmt:message key="label.Person.firstname"/></th>
            <td>${person.firstName}</td>
        </tr>
        <tr>
            <th><fmt:message key="label.Person.middlename"/></th>
            <td>${person.middleName}</td>
        </tr>
        <tr>
            <th><fmt:message key="label.Person.lastname"/></th>
            <td>${person.lastName}</td>
        </tr>
        <tr>
            <th><fmt:message key="label.Person.dob"/></th>
            <td><fmt:formatDate value="${person.dateOfBirth}"/></td>
        </tr>

        <tr>
            <th><fmt:message key="label.Person.gender"/></th>
            <td>${person.gender.initial}</td>
        </tr>
        <tr>
            <th><fmt:message key="label.Hospital.name"/></th>
            <td>${person.hospital.name}</td>
        </tr>

        <tr>
            <th><fmt:message key="label.ic.pnc"/></th>
            <td>${person.identityCard.pnc}</td>
        </tr>
        <tr>
            <th><fmt:message key="label.ic.series"/>:<fmt:message key="label.ic.number"/></th>
            <td>${person.identityCard.series}:${person.identityCard.number}</td>
        </tr>
        <tr>
            <th><fmt:message key="label.ic.address"/></th>
            <td>${person.identityCard.address}</td>
        </tr>
        <tr>
            <th><fmt:message key="label.ic.validity"/></th>
            <td><fmt:formatDate value="${person.identityCard.emittedAt}"/> - <fmt:formatDate value="${person.identityCard.expiresAt}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <spring:url var="editUrl" value="{id}/edit">
                    <spring:param name="id" value="${person.id}" />
                </spring:url>
                <a href="${editUrl}"><fmt:message key="command.edit" /></a>
            </td>
        </tr>
    </table>
</div>
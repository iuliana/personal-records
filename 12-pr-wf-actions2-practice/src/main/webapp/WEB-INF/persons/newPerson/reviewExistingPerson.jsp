<!-- Created by iuliana.cosmina on 7/09/15. -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>
    <spring:message code="persons.review.title"/>
</h2>

<div class="person">
    <sf:form id="reviewPerson" method="POST">
        <table>
            <tr>
                <th>
                    <label> <spring:message code="label.Person.firstname"/> : </label>
                </th>
                <td><label>${person.firstName}</label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.Person.middlename"/> : </label>
                </th>
                <td><label>${person.middleName}</label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.Person.lastname"/> : </label>
                </th>
                <td><label>${person.lastName}</label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.Person.dob"/> : </label>
                </th>
                <td><label>${person.dateOfBirth}</label></td>
            </tr>

            <tr>
                <th>
                    <label> <spring:message code="label.Person.gender"/> : </label>
                </th>
                <!-- Internationalize this-->
                <td><label><spring:message code="label.${person.gender}"/></label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.Hospital"/> : </label>
                </th>
                <td><label>${person.hospital.name}</label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.ic.pnc"/> : </label>
                </th>
                <td><label>${person.pnc}</label></td>
            </tr>
            <tr>
                <td>
                    <button type="submit" name="_eventId_continue">
                        <spring:message code="command.continue"/>
                    </button>
                </td>
                <td>
                    <button type="submit" name="_eventId_cancel">
                        <spring:message code="command.cancel"/>
                    </button>
                </td>
            </tr>
        </table>

    </sf:form>
</div>

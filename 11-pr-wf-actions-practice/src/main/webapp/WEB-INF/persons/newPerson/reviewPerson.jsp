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
                <td><label>${newPerson.firstName}</label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.Person.middlename"/> : </label>
                </th>
                <td><label>${newPerson.middleName}</label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.Person.lastname"/> : </label>
                </th>
                <td><label>${newPerson.lastName}</label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.Person.dob"/> : </label>
                </th>
                <td><label>${newPerson.dateOfBirth}</label></td>
            </tr>

            <tr>
                <th>
                    <label> <spring:message code="label.Person.gender"/> : </label>
                </th>
                <!-- Internationalize this-->
                <td><label><spring:message code="label.${newPerson.gender}"/></label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.Hospital"/> : </label>
                </th>
                <td><label>${newPerson.hospital.name}</label></td>
            </tr>
            <tr>
                <th>
                    <label> <spring:message code="label.ic.pnc"/> : </label>
                </th>
                <td><label>${newPerson.pnc}</label></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" name="_eventId_proceed">
                        <spring:message code="command.proceed"/>
                    </button>
                </td>
            </tr>
        </table>

    </sf:form>
</div>

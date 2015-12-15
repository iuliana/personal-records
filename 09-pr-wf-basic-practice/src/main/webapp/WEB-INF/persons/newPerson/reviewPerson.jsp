<!-- Created by iuliana.cosmina on 7/09/15. -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>
    <spring:message code="persons.review.title"/>
</h2>

<div class="person">
    <sf:form id="reviewForm" method="POST">
        <table>
            <tr>
                <th>
                    <label for="firstName">
                        <spring:message code="label.Person.firstname"/> :
                    </label>
                </th>
                <td>${newPerson.firstName}</td>
            </tr>
            <tr>
                <th>
                    <label for="middleName">
                        <spring:message code="label.Person.middlename"/> :
                    </label>
                </th>
                <td>${newPerson.middleName}</td>
            </tr>
            <tr>
                <th>
                    <label for="lastName">
                        <spring:message code="label.Person.lastname"/> :
                    </label>
                </th>
                <td>${newPerson.lastName}</td>
            </tr>
            <tr>
                <th>
                    <label for="dateOfBirth">
                        <span class="man">*</span> <spring:message code="label.Person.dob"/> :
                    </label>
                </th>
                <td>${newPerson.dateOfBirth}</td>
            </tr>

            <tr>
                <th>
                    <label for="gender">
                        <spring:message code="label.Person.gender"/> :
                    </label>
                </th>
                <!-- Internationalize this-->
                <td>${newPerson.gender}</td>
            </tr>
            <tr>
                <th>
                    <label for="hospital">
                        <spring:message code="label.Hospital"/> :
                    </label>
                </th>
                <td>${newPerson.hospital.name}</td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" name="_eventId_confirm">
                        <fmt:message key="command.confirm"/>
                    </button>
                </td>
            </tr>
        </table>

    </sf:form>
</div>

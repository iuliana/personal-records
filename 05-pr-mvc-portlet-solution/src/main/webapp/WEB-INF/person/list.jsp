<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<fmt:setBundle basename="localization/global"/>

<h2>
    <fmt:message key="persons.list.title"/>
</h2>

<div class="persons">
    <table>
        <thead>
        <tr>
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
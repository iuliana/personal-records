<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="localization/global"/>
<portlet:defineObjects/>

<style type="text/css">
    table {
        background-color: #FFFFE0;
        border-collapse: collapse;
        color: #FFFFFF;
    }

    table thead {
        background-color: #BDB76B;
        color: white;
        width: 50%;
        font-weight: bold;
    }

    table td {
        padding: 5px;
        border: 0;
    }

    table td {
        border-bottom: 1px dotted #BDB76B;
    }
</style>

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
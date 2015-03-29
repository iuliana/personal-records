<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2>
    <fmt:message key="hospitals.list.title"/>
</h2>

<div class="hospitals">
    <table>
        <thead>
        <tr>
            <td> <fmt:message key="label.count"/></td>
            <td>
                <fmt:message key="label.Hospital.name"/>
            </td>
            <td>
                <fmt:message key="label.Hospital.code"/>
            </td>
            <td>
                <fmt:message key="label.Hospital.address"/>
            </td>
            <td>
                <fmt:message key="label.Hospital.location"/>
            </td>
        </tr>
        </thead>
        <c:forEach var="hospital" items="${hospitals}">
            <tr>
                <td>
                        ${hospital.id}
                </td>
                <td>
                        ${hospital.name}
                </td>
                <td>
                    <spring:url var="showUrl" value="{code}">
                        <spring:param name="code" value="${hospital.code}"/>
                    </spring:url>
                    <a href="${showUrl}">${hospital.code}</a>
                </td>
                <td>
                        ${hospital.address}
                </td>
                <td>
                        ${hospital.location}
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
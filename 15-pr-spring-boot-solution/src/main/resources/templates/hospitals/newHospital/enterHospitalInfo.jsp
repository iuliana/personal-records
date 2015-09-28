<!-- Created by iuliana.cosmina on 7/09/15. -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>
    <spring:message code="hospital.new.title"/>
</h2>

<div class="person">

    <sf:form id="newHospitalForm" method="POST" modelAttribute="hospital">
        <table>
            <tr>
                <th>
                    <label for="name">
                        <span class="man">*</span> <spring:message code="label.Hospital.name"/> :
                    </label>
                </th>
                <td><sf:input path="name"/></td>
                <td><sf:errors cssClass="error" path="name"/></td>
            </tr>
            <tr>
                <th>
                    <label for="code">
                        <span class="man">*</span><spring:message code="label.Hospital.code"/> :
                    </label>
                </th>
                <td><sf:input path="code"/></td>
                <td><sf:errors cssClass="error" path="code"/></td>
            </tr>
            <tr>
                <th>
                    <label for="address">
                        <spring:message code="label.Hospital.address"/> :
                    </label>
                </th>
                <td><sf:input path="address"/></td>
                <td></td>
            </tr>
            <tr>
                <th>
                    <label for="location">
                        <span class="man">*</span> <spring:message code="label.Hospital.location"/> :
                    </label>
                </th>
                <td><sf:input path="location"/></td>
                <td><sf:errors cssClass="error" path="location"/></td>
            </tr>


            <tr>
                <td>
                    <button id="save" name="_eventId_save" type="submit">
                        <spring:message code="command.save" />
                    </button>
                </td>
                <td>
                    <button id="cancel" name="_eventId_cancel" type="submit">
                        <spring:message code="command.cancel" />
                    </button>
                </td>
            </tr>
        </table>

    </sf:form>

</div>

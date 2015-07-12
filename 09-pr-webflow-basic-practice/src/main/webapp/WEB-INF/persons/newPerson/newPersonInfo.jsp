<!-- Created by iuliana.cosmina on 7/09/15. -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>
    <spring:message code="persons.new.title"/>
</h2>

<div class="person">

    <sf:form id="newPersonForm" method="POST" modelAttribute="person">
        <table>
            <tr>
                <th>
                    <label for="firstName">
                        <span class="man">*</span> <spring:message code="label.Person.firstname"/> :
                    </label>
                </th>
                <td><sf:input path="firstName"/></td>
                <td><sf:errors cssClass="error" path="firstName"/></td>
            </tr>
            <tr>
                <th>
                    <label for="middleName">
                        <spring:message code="label.Person.middlename"/> :
                    </label>
                </th>
                <td><sf:input path="middleName"/></td>
                <td><sf:errors cssClass="error" path="middleName"/></td>
            </tr>
            <tr>
                <th>
                    <label for="lastName">
                        <span class="man">*</span> <spring:message code="label.Person.lastname"/> :
                    </label>
                </th>
                <td><sf:input path="lastName"/></td>
                <td><sf:errors cssClass="error" path="lastName"/></td>
            </tr>
            <tr>
                <th>
                    <label for="dateOfBirth">
                        <span class="man">*</span> <spring:message code="label.Person.dob"/> :
                    </label>
                </th>
                <td><sf:input path="dateOfBirth"/></td>
                <td><sf:errors cssClass="error" path="dateOfBirth"/></td>
            </tr>

            <tr>
                <th>
                    <label for="gender">
                        <spring:message code="label.Person.gender"/> :
                    </label>
                </th>
                <td>
                    <sf:radiobutton path="gender" value="MALE"/> <spring:message code="label.Person.male"/>
                    <sf:radiobutton path="gender" value="FEMALE"/> <spring:message code="label.Person.female"/>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="hospital">
                        <span class="man">*</span> <spring:message code="label.Hospital"/> :
                    </label>
                </th>
                <td>
                    <sf:select path="hospital">
                        <c:choose>
                            <c:when test="${person == null}">
                                <sf:option value=""><spring:message code='label.choose'/></sf:option>
                            </c:when>
                            <c:otherwise>
                                <sf:option value="${person.hospital.id}">${person.hospital.name}</sf:option>
                            </c:otherwise>
                        </c:choose>

                        <sf:options items="${hospitalList}" itemValue="id" itemLabel="name"/>
                    </sf:select>

                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button id="rewardButton" name="_eventId_submit" type="submit">
                        <fmt:message key="command.save" />
                    </button>
                </td>
            </tr>
        </table>

    </sf:form>

</div>

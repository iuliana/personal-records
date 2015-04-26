<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<h3>
    <fmt:message key="persons.add.title"/>
</h3>

<portlet:actionURL var="addPersonUrl">
    <portlet:param name="javax.portlet.action" value="add"/>
</portlet:actionURL>

<portlet:renderURL var="cleanPersonUrl">
    <portlet:param name="action" value="clean" />
</portlet:renderURL>

<div class="person">

    <c:if test="${message ne null}">
        <div class="success"><c:out value="${message}"/></div>
    </c:if>
    <c:if test="${error ne null}">
        <div class="error"><c:out value="${error}"/></div>
    </c:if>
    <sf:form name="person" modelAttribute="person" action="${addPersonUrl}" method="POST">
        <table>
            <tr>
                <th>
                    <label for="firstName">
                        <span class="man">*</span> <fmt:message key="label.Person.firstname"/> :
                    </label>
                </th>
                <td><sf:input path="firstName"/></td>
                <td><sf:errors cssClass="error" path="firstName"/></td>
            </tr>
            <tr>
                <th>
                    <label for="middleName">
                        <fmt:message key="label.Person.middlename"/> :
                    </label>
                </th>
                <td><sf:input path="middleName"/></td>
                <td><sf:errors cssClass="error" path="middleName"/></td>
            </tr>
            <tr>
                <th>
                    <label for="lastName">
                        <span class="man">*</span> <fmt:message key="label.Person.lastname"/> :
                    </label>
                </th>
                <td><sf:input path="lastName"/></td>
                <td><sf:errors cssClass="error" path="lastName"/></td>
            </tr>
            <tr>
                <th>
                    <label for="dateOfBirth">
                        <span class="man">*</span> <fmt:message key="label.Person.dob"/> :
                    </label>
                </th>
                <td><sf:input path="dateOfBirth"  sf:placeholder="YYYY-MM-DD"/></td>
                <td><sf:errors cssClass="error" path="dateOfBirth"/></td>
            </tr>

            <tr>
                <th>
                    <label for="gender">
                        <span class="man">*</span> <fmt:message key="label.Person.gender"/> :
                    </label>
                </th>
                <td>
                    <sf:radiobutton path="gender" value="MALE"/> <fmt:message key="label.Person.male"/>
                    <sf:radiobutton path="gender" value="FEMALE"/> <fmt:message key="label.Person.female"/>
                </td>
                <td><sf:errors cssClass="error" path="gender"/></td>
            </tr>
            <tr>
                <th>
                    <label for="hospital">
                        <span class="man">*</span> <fmt:message key="label.Hospital"/> :
                    </label>
                </th>
                <td>
                    <sf:select path="hospital">
                        <c:choose>
                            <c:when test="${person == null}">
                                <sf:option value=""><fmt:message key='label.choose'/></sf:option>
                            </c:when>
                            <c:otherwise>
                                <sf:option value="${person.hospital.id}">${person.hospital.name}</sf:option>
                            </c:otherwise>
                        </c:choose>

                        <sf:options items="${hospitalList}" itemValue="id" itemLabel="name"/>
                    </sf:select>
                </td>
                <td><sf:errors cssClass="error" path="hospital"/></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value=" <fmt:message key='command.save'/>">
                </td>
                <td>
                    <a href="${cleanPersonUrl}"><fmt:message key="command.cancel"/></a>
                </td>
            </tr>
        </table>
    </sf:form>
</div>
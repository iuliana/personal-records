<!-- Created by iuliana.cosmina on 7/09/15. -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>
    <spring:message code="ic.new.title"/>
</h2>

<div class="person">

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
            <td><label>${person.gender}</label></td>
        </tr>
        <tr>
            <th>
                <label> <spring:message code="label.Hospital"/> : </label>
            </th>
            <td><label>${person.hospital.name}</label></td>
        </tr>
    </table>

    <sf:form id="newIdentityCard" method="POST" modelAttribute="identityCard">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table>
            <tr>
                <th>
                    <label for="pnc">
                        <span class="man">*</span> <spring:message code="label.ic.pnc"/> :
                    </label>
                </th>
                <td><sf:input path="pnc" readonly="true" value="${person.pnc}"/></td>
                <td><sf:errors cssClass="error" path="pnc"/></td>
            </tr>
            <tr>
                <th>
                    <label for="series">
                        <span class="man">*</span> <spring:message code="label.ic.series"/> :
                    </label>
                </th>
                <td><sf:input path="series"/></td>
                <td><sf:errors cssClass="error" path="series"/></td>
            </tr>
            <tr>
                <th>
                    <label for="number">
                        <span class="man">*</span><spring:message code="label.ic.number"/> :
                    </label>
                </th>
                <td> <sf:input path="number"/></td>
                <td><sf:errors cssClass="error" path="number"/></td>
            </tr>
            <tr>
                <th>
                    <label for="address">
                        <span class="man">*</span> <spring:message code="label.ic.address"/> :
                    </label>
                </th>
                <td><sf:input path="address"/></td>
                <td><sf:errors cssClass="error" path="address"/></td>
            </tr>
            <tr>
                <th>
                    <label for="emittedAt">
                        <span class="man">*</span> <spring:message code="label.ic.emittedAt"/> :
                    </label>
                </th>
                <td><sf:input path="emittedAt"/></td>
                <td><sf:errors cssClass="error" path="emittedAt"/></td>
            </tr>
            <tr>
                <th>
                    <label for="expiresAt">
                        <span class="man">*</span> <spring:message code="label.ic.expiresAt"/> :
                    </label>
                </th>
                <td><sf:input path="expiresAt"/></td>
                <td><sf:errors cssClass="error" path="expiresAt"/></td>
            </tr>
            <tr>
                <td>
                    <button id="newIdentityCardButton" name="_eventId_save" type="submit">
                        <spring:message code="command.save"/>
                    </button>
                </td>
                <td>
                    <button id="cancelButton" name="_eventId_cancel" type="submit">
                        <spring:message code="command.cancel"/>
                    </button>
                </td>
            </tr>
        </table>

    </sf:form>

</div>

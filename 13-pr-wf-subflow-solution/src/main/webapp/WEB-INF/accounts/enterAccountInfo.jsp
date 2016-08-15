<!-- Created by iuliana.cosmina on 7/09/15. -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h2>
    <spring:message code="account.new.title"/>
</h2>

<div class="account">

    <sf:form id="newAccountForm" method="POST" modelAttribute="account">
        <table>
            <tr>
                <th>
                    <label for="bank">
                        <span class="man">*</span> <spring:message code="label.Account.bank"/> :
                    </label>
                </th>
                <td><sf:input path="bank" /></td>
                <td><sf:errors cssClass="error" path="bank"/></td>
            </tr>
            <tr>
                <th>
                    <label for="iban">
                        <span class="man">*</span> <spring:message code="label.Account.iban"/> :
                    </label>
                </th>
                <td><sf:input path="iban"/></td>
                <td><sf:errors cssClass="error" path="iban"/></td>
            </tr>
            <tr>
                <th>
                    <label for="amount">
                        <spring:message code="label.Account.amount"/> :
                    </label>
                </th>
                <td><sf:input path="amount"/></td>
                <td><sf:errors cssClass="error" path="amount"/></td>
            </tr>
            <tr>
                <th>
                    <label for="status">
                        <span class="man">*</span> <spring:message code="label.Account.status"/> :
                    </label>
                </th>
                <td>
                    <sf:radiobutton path="status" value="ACTIVE"/> <spring:message code="label.ACTIVE"/>
                    <sf:radiobutton path="status" value="CLOSED"/> <spring:message code="label.CLOSED"/>
                    <sf:radiobutton path="status" value="BLOCKED"/> <spring:message code="label.BLOCKED"/>
                </td>
            </tr>
            <tr>
                <td>
                    <button id="newAccount" name="_eventId_add" type="submit">
                        <spring:message code="command.add"/>
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

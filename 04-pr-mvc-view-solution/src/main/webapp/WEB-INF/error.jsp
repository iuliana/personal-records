<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty problem}">
    <h3><spring:message code="error.message"/></h3>

    <div class="error">
        ${problem}
    </div>
</c:if>

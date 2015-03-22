<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty problem}">
    <h3><fmt:message key="error.message"/></h3>

    <div class="error">
            ${problem}
    </div>
</c:if>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h3><spring:message code="error.message"/></h3>


<div class="error">
    <%
        Exception exception = (Exception) request.getAttribute("flowExecutionException");
        Exception cause = (Exception) request.getAttribute("rootCauseException");
    %>

    <h3>Exception occurred while executing the newPerson web flow: <%=exception.getMessage()%></h3>
    <p>
        <%
            exception.printStackTrace(new java.io.PrintWriter(out));
        %>
    </p>

    <% if (cause != null) { %>
    <h3>Cause: <%=cause.getMessage()%></h3>
    <p>
        <%
            cause.printStackTrace(new java.io.PrintWriter(out));
        %>
    </p>
    <%} %>
</div>

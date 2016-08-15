<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <spring:theme var="cssStyle" code="css.style"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="${cssStyle}" />"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/styles/general.css" />"/>
    <title>
        <fmt:message>
            <tiles:insertAttribute name="pageTitle"/>
        </fmt:message>
    </title>
</head>
<body>
<div class="page">
    <div class="banner"></div>
    <div class="themeLocal">
        <c:choose>
            <c:when test="${requestContext.locale.language eq 'en'}">
                <c:url var="localeUrl" value="/">
                    <c:param name="locale" value="de"/>
                </c:url>
                <a href="${localeUrl}"><spring:message code="locale.de"/></a>
            </c:when>
            <c:otherwise>
                <c:url var="localeUrl" value="/">
                    <c:param name="locale" value="en"/>
                </c:url>
                <a href="${localeUrl}"><spring:message code="locale.en"/></a>
            </c:otherwise>
        </c:choose> |
        <c:choose>
            <c:when test="${requestContext.theme.name eq 'green'}">
                <c:url var="themeUrl" value="/">
                    <c:param name="theme" value="blue"/>
                </c:url>
                <a href="${themeUrl}"><spring:message code="theme.Blue"/></a>
            </c:when>
            <c:otherwise>
                <c:url var="themeUrl" value="/">
                    <c:param name="theme" value="green"/>
                </c:url>
                <a href="${themeUrl}"><spring:message code="theme.Green"/></a>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="menu">
        <ul>
            <li><c:if test="${menuTab eq 'home'}">
                <strong><a href="<c:url value="/"/>"><spring:message code="menu.home"/></a></strong>
            </c:if>
                <c:if test="${menuTab != 'home'}">
                    <a href="<c:url value="/"/>"><spring:message code="menu.home"/></a>
                </c:if>
            </li>
            <li><c:if test="${menuTab eq 'persons'}">
                <strong><a href="<c:url value="/persons/search"/>"><spring:message code="menu.persons"/></a></strong>
            </c:if>
                <c:if test="${menuTab != 'persons'}">
                    <a href="<c:url value="/persons/search"/>"><spring:message code="menu.persons"/></a>
                </c:if>
            </li>
            <li><c:if test="${menuTab eq 'hospitals'}">
                <strong><a href="<c:url value="/hospitals/"/>"><spring:message code="menu.hospitals"/></a></strong>
            </c:if>
                <c:if test="${menuTab != 'hospitals'}">
                    <a href="<c:url value="/hospitals/"/>"><spring:message code="menu.hospitals"/></a>
                </c:if>
            </li>
           <!--TODO 50.secure this element so it will be accessible only to ADMIN users -->
                <li>
                    <c:if test="${navigationTab eq 'newPerson'}">
                        <strong>
                            <a href="<c:url value="/persons/newPerson"/>">
                                <spring:message code="menu.new.person"/>
                            </a>
                        </strong>
                    </c:if>
                    <c:if test="${navigationTab != 'newPersons'}">
                        <a href="<c:url value="/persons/newPerson"/>">
                            <spring:message code="menu.new.person"/>
                        </a>
                    </c:if>
                </li>

            <sec:authorize access="isAuthenticated()">
                <li>
                    <spring:url value="/logout" var="logoutUrl"/>
                    <form action="${logoutUrl}" id="logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <a href="#" onclick="document.getElementById('logout').submit();"><spring:message code="menu.logout"/></a>
                </li>
            </sec:authorize>
        </ul>
    </div>

    <div class="content">
        <tiles:insertAttribute name="content"/>
    </div>

    <div class="footer">
        <sec:authorize access="isAuthenticated()">
            <p><spring:message code="user.loggedin"/>:
                <sec:authentication property="principal.username"/>
            </p>
        </sec:authorize>
        <p><spring:message code="footer.text"/></p>
    </div>

</div>
</body>
</html>
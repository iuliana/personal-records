<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
        <fmt:message key="welcome.title" />
    </title>
</head>
<html>
<body>
    <div id="main">
        <div id="banner">
        <img src="<c:url value='/images/banner-green.png'/>"/>
        </div>
        <div id="body">
             <h1>
                        <fmt:message key="welcome.title" />
             </h1>
             <h4>
                <em> <fmt:message key="welcome.caption" /> </em>
                   <ul>
                       	<li>
                       		<a href="<c:url value="/persons"/>">
                       			<fmt:message key="navigate.persons" />
                       		</a>
                       		</li>
                       		<li>
                       		<a href="<c:url value="/persons"/>">
                       		    <fmt:message key="navigate.accounts" />
                       		</a>
                       	</li>
                   </ul>
             </h5>
        </div>
        <div id="footer">
        </div>
    </div>
</body>
</html
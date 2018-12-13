<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.12.2018
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${meals}" var="item">

    <javatime:format value="${item.dateTime}" pattern="yyyy-MM-dd" var="parsedDate"/>
    <c:choose>
        <c:when test="${item.exceed=='1'}">
            <font color="green">
                    ${parsedDate} ${item}
            </font>
            <br/>
        </c:when>
        <c:otherwise>
            <font color="#8b0000">
                    ${parsedDate} ${item}
            </font>
            <br/>
        </c:otherwise>
    </c:choose>

</c:forEach>

</body>
</html>

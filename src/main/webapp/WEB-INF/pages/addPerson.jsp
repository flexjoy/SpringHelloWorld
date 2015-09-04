<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.springapp.Url" %>
<html>
<head>
    <title>Add new person</title>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>

<h2>Add new person:</h2>

<form:form method="POST" modelAttribute="person">
    <table>
        <tr>
            <td><form:label path="name">ФИО: </form:label></td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:label path="age">Возраст: </form:label></td>
            <td><form:input path="age" /></td>
            <td><form:errors path="age" cssClass="error" /></td>
        </tr>
        <tr>
            <td></td>
            <td align="right"><input type="submit" /></td>
            <td></td>
        </tr>
    </table>
</form:form>
<spring:url value="<%=Url.HOME_PAGE%>" htmlEscape="true" var="url"/>
<a href="<c:out value="${url}" />">&lt; Back to person list</a>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
	<meta content="utf-8" http-equiv="encoding">
    <title>Spring MVC Form Handling</title>
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script language="javascript" type="text/javascript" src="/resources/script/script2.js" ></script>
    <link rel="stylesheet" type="text/css" href="/resources/style/style1.css" />
</head>
<body>

<h2>Student Information</h2>
<form:form method="POST" action="/addStudent"><!-- /sample-webapp1 -->
<table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="age">Age</form:label></td>
        <td><form:input path="age" /></td>
    </tr>
    <tr>
        <td><form:label path="id">id</form:label></td>
        <td><form:input path="id" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input id="sub-button" type="submit" value="Submit"/>
        </td>
    </tr>
</table>  

<div id="result"></div>
<div id="table">
	
</div>

</form:form>
</body>
</html>
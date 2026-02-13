<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view request</title>
</head>
<body>
<div style=" font-weight: bold;">
    Viewing Request
</div>
<form action="MainServlet" method="post">
<input type="hidden" name="operation" value="viewRecord">

Name: <input type="text" name="requesterName"><br></br>
Date: <input type="text" name="requestDate"><br></br>

<input type="submit" value="View">
</form>


</body>
</html>
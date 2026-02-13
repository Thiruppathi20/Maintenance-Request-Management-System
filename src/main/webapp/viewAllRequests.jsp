<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="font-weight: bold;">
    View all your Request
</div>
<form action="MainServlet" method="post">
<input type="hidden" name="operation" value="viewAllRecords">
<br>
<input type="submit" value="Show">
</br>
</form>


</body>
</html>
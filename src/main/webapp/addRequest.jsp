<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maintenance request page</title>
</head>
<body>
<div style=" font-weight: bold;">
    Add your Request
</div>
<form action="MainServlet" method="post">
<input type="hidden" name="operation" value="newRecord">

Name: <input type="text" name="requesterName"><br></br>
Issue: <input type="text" name="issueType"><br></br>
Date: <input type="text" name="requestDate" placeholder="dd-mm-yyyy"><br></br>
Priority: <input type="text" name="priority"><br></br>
Remarks: <input type="text" name="remarks"><br></br>

<input type="submit" value="Add"><br></br>
</form>


</body>
</html>
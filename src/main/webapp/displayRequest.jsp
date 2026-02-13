<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="com.wipro.maintenance.bean.MaintenanceBean" %>

<%
MaintenanceBean bean =
(MaintenanceBean)request.getAttribute("bean");

if(bean==null){
%>
No matching records exists! Please try again!
<%
}else{
%>
ID: <%=bean.getRequestId()%><br>
Name: <%=bean.getRequesterName()%><br>
Issue: <%=bean.getIssueType()%><br>
Date: <%=bean.getRequestDate()%><br>
Priority: <%=bean.getPriority()%><br>
Remarks: <%=bean.getRemarks()%><br>
<%
}
%>


</body>
</html>
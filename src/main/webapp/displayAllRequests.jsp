<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.*,com.wipro.maintenance.bean.MaintenanceBean" %>

<%
List<MaintenanceBean> list =
(List<MaintenanceBean>)request.getAttribute("list");

if(list==null || list.isEmpty()){
%>
No records available!
<%
}else{
for(MaintenanceBean b:list){
%>
<hr>
ID: <%=b.getRequestId()%><br>
Name: <%=b.getRequesterName()%><br>
Issue: <%=b.getIssueType()%><br>
Date: <%=b.getRequestDate()%><br>
Priority: <%=b.getPriority()%><br>
Remarks: <%=b.getRemarks()%><br>
<%
}
}
%>


</body>
</html>
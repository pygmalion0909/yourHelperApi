<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<table>
  		<thead>
    		<th>����</th>
    		<th>����</th>
    		<th>����</th>
    		<th>����</th>
    		<th>����</th>
    		<th>��ȸ��</th>
  		</thead>
  		<tbody>
  		<% for(int i = 1; i < ${list}.length; i++){ %>  		
    		<td>${list[i].title}</td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
  		<% } %>
  		</tbody>
	</table>

</body>
</html>
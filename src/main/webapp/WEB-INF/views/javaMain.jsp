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
    		<th>순번</th>
    		<th>제목</th>
    		<th>내용</th>
    		<th>생성</th>
    		<th>수정</th>
    		<th>조회수</th>
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
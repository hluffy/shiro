<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	${successMsg} Welcome!  
	<shiro:principal/>  
    <br><br>  
      
    <shiro:hasAnyRoles name="user">  
        <a href="http://localhost:8080/shiro/user/openuser.ll">User Page</a>  
    </shiro:hasAnyRoles>  
      
    <br><br>  
      
    <shiro:hasAnyRoles name="admin">  
        <a href="http://localhost:8080/shiro/user/openadmin.ll">Admin Page</a>  
    </shiro:hasAnyRoles>  
      
    <br><br>  
<!--     <a href="../test/logout.do">Logout</a>   -->
</body>
</html>
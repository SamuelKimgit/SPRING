<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>anno.jsp</title>
</head>
<body>
  <h2>anno.jsp</h2>
  <hr/>
  
  <form action="/fire/anno/doSelectOne.do" method="post">
  아이디: <input type="text" name="userId" id="userId"><br/>
  비번: <input type="password" name="passwd" id="passwd"><br/>
  <input type="submit" value="로그인">
  </form>
  vo:${vo }
</body>
</html>
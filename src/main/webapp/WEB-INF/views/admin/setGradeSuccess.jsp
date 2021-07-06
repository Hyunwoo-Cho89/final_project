<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var result = "${result}"
	if(result === "Y"){
		alert('이제부터 성적 정정이 가능합니다.')
		location.href='grade'	
	}
	else{
		alert('이제부터 성적 정정이 불가능합니다.')
		location.href='grade'
	}
</script>
</head>
<body>
</body>
</html>
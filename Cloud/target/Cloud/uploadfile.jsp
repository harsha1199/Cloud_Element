<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloud Element</title>
</head>
<body align="center">
<h2>Upload FIle</h2>
	<form method="POST" action="uploadfile" enctype="multipart/form-data">
		Select file: <input type="file" name="file"><br /><br> 
		<input type="submit" value="Upload"></form>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloud Element</title>
</head>
<body>
	<center>
		<h2>Welcome to Cloud Elements</h2>
		<h4>
			Current Dir:
			<c:out value="${curr_dir}" />
		</h4>
		<c:if test="${not empty Files}">
			<table border="1">
				<thead>
					<tr>
						<th>File/Folder</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="file" items="${Files}">
						<tr>
							<td>${file.getFileName()}</td>
							<td><c:if test="${file.isFile()}">
									<a href="/download/${file.getFileName()}">download</a>
								</c:if>
								 <c:if test="${not file.isFile()}">
									<a href="/listAll/UI?dir=${file.getFileName()}">open</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado</title>
</head>
<body>
    <h1>Resultado desde el Servlet</h1>
    <!-- <p>El resultado es: ${resultado}</p> -->
    <p><%= request.getAttribute("mensaje") %></p>
</body>
</html>
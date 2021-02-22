<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Товар</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
    <%@include file="styles.jsp" %>
</head>
<body>
    <div class="top">
       <div class="container">
            <h1 class="top-header">Товар</h1>
            <%@include file="menu.jsp" %>
       </div>
    </div>
    <div class="container content">
        <c:url value="/product" var="productSubmitUrl"/>
        <form action="${productSubmitUrl}" method="post">
            <input type="hidden" id="id" name="id" value="${product.id}">
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${product.name}" placeholder="Enter name">
            </div>
            <div class="form-group">
                <label>Description</label>
                <input type="text" class="form-control" id="description" name="description" name="name" value="${product.description}" placeholder="Enter description">
            </div>
            <div class="form-group">
                <label>Price</label>
                <input type="number" class="form-control" id="prise" name="prise" name="prise" value="${product.prise}" placeholder="Enter price">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>
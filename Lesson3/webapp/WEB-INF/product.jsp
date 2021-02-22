<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Каталог товаров</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
    <%@include file="styles.jsp" %>
</head>
<body>
    <div class="top">
       <div class="container">
            <h1 class="top-header">Каталог товаров</h1>
            <%@include file="menu.jsp" %>
       </div>
    </div>
    <div class="container content">
        <div class="col-12">
            <c:url value="/product/new" var="productNewUrl"/>
            <a class="btn btn-primary" href="${productNewUrl}">Добавить товар</a>
        </div>
        <table class="table table-bordered my-2">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${requestScope.products}">
                    <tr>
                        <th scope="row"><c:out value="${product.id}"/></th>
                        <td><c:out value="${product.name}"/></td>
                        <td><c:out value="${product.description}"/></td>
                        <td>$<c:out value="${product.prise}"/></td>
                        <td>
                            <c:url value="/product/edit" var="productEditUrl">
                                <c:param name="id" value="${product.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${productEditUrl}"><i class="fas fa-edit"></i></a>
                            <c:url value="/product/delete" var="productEditUrl">
                                <c:param name="id" value="${product.id}"/>
                            </c:url>
                            <a class="btn btn-danger" href="${productEditUrl}"><i class="far fa-trash-alt"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
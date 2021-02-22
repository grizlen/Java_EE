<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Категории товаров</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
    <%@include file="styles.jsp" %>
</head>
<body>
    <div class="top">
       <div class="container">
            <h1 class="top-header">Категории товаров</h1>
            <%@include file="menu.jsp" %>
       </div>
    </div>
    <div class="container content">
        <div class="col-12">
            <c:url value="/category/new" var="categoryNewUrl"/>
            <a class="btn btn-primary" href="${categoryNewUrl}">Новая категория</a>
        </div>
        <table class="table table-bordered my-2">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${requestScope.categorys}">
                    <tr>
                        <th scope="row"><c:out value="${category.id}"/></th>
                        <td><c:out value="${category.name}"/></td>
                        <td>
                            <c:url value="/category/edit" var="categoryEditUrl">
                                <c:param name="id" value="${category.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${categoryEditUrl}"><i class="fas fa-edit"></i></a>
                            <c:url value="/category/delete" var="categoryEditUrl">
                                <c:param name="id" value="${category.id}"/>
                            </c:url>
                            <a class="btn btn-danger" href="${categoryEditUrl}"><i class="far fa-trash-alt"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
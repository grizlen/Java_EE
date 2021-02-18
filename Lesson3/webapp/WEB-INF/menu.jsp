<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav-list">
    <li><a href=<c:url value="/main"/>>Главная</a></li>
    <li><a href=<c:url value="/product"/>>Товары</a></li>
    <li><a href=<c:url value="/cart"/>>Корзина</a></li>
    <li><a href=<c:url value="/order"/>>Заказ</a></li>
    <li><a href=<c:url value="/category"/>>Категории товаров</a></li>
    <li><a href=<c:url value="/user"/>>Пользователи</a></li>
</ul>

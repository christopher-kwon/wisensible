<%--
  Created by IntelliJ IDEA.
  User: moonpeter
  Date: 2021/02/18
  Time: 3:38 오후
  To change this template use File | Settings | File Templates.
--%>

<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop Homepage - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/bootstrap/css/shop-homepage.css" rel="stylesheet">

    <!-- Bootstrap core JavaScript -->
    <script src="css/bootstrap/vendor/jquery/jquery.min.js"></script>
    <script src="css/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</head>

<!-- Navigation -->
<div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
            <a class="navbar-brand" href="BoardList.bo">
                <img src="image/logo2.png" alt="" width="300px">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                    aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="BoardList.bo">
                            Home<span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <c:if test="${empty id}">
                    <li class="nav-item">
                        <a class="nav-link" href="login.com">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="join.com">Join</a>
                    </li>
                    </c:if>

                    <c:if test="${!empty id}">
                        <li class="nav-item">
                            <a class="nav-link" href="logout.com">ID:${id}(Logout)</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="memberInfo.com">Info</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="memberUpdate.com">Update</a>
                        </li>
                    </c:if>

                    <c:if test="${id=='admin'}">
                        <li class="nav-item">
                            <a class="nav-link" href="memberList.com">MemberList</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <div class="col-lg-3">
                <div>&nbsp</div>

                <div class="list-group" style="text-align: center;">
                    <button class="list-group-item" style="background-color: gray; color: white">카테고리</button>
                    <a href="BoardCategoryListAction.bo?category=과일" class="list-group-item">과일</a>


                <a href="BoardCategoryListAction.bo?category=채소" class="list-group-item">채소</a>
                <a href="BoardCategoryListAction.bo?category=곡물" class="list-group-item">곡물</a>
                <a href="BoardCategoryListAction.bo?category=축산물" class="list-group-item">축산물</a>
                <a href="BoardCategoryListAction.bo?category=해산물" class="list-group-item">해산물</a>
                <a href="BoardCategoryListAction.bom?category=경매글" class="list-group-item">경매글</a>

                <div>&nbsp</div>
                <button type="button" class="btn btn-dark" onclick="location.href='BoardWrite.bo'">글 쓰 기</button>

            </div>
            
                            <div>&nbsp</div>
                <button type="button" class="btn btn-dark" onclick="location.href='BoardWrite.bom'">경 매 글</button>

            </div>


            </div>
            <!-- /.col-lg-3 -->

            <div class="col-lg-9">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: garan
  Date: 29.03.2023
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <c:if test="${calcMessage != null}">
        <div class="alert alert-danger" role="alert">
                ${calcMessage}
        </div>
    </c:if>
    <form action="/calc" method="post" class="justify-content-start">
        <div class="row row-cols-auto align-items-center m-3">
            <div class="col">
                <div class="input-group input-group-sm">
                    <span class="input-group-text">Num1</span>
                    <input type="text" ari name="num1">
                </div>
            </div>
            <div class="col">
                <div class="input-group input-group-sm">
                    <span class="input-group-text">Num2</span>
                    <input type="text" name="num2">
                </div>
            </div>
            <div class="col">
                <div class="input-group input-group-sm">
                    <span class="input-group-text">Type</span>
                    <select class="form-select" aria-label="Default select example" name="type">
                        <option selected value="SUM">SUM</option>
                        <option value="SUB">SUB</option>
                        <option value="MUL">MUL</option>
                        <option value="DIV">DIV</option>
                    </select>
                </div>
            </div>
            <div class="col" >
                <button>Calculate</button>
            </div>
        </div>
    </form>
    <div class="row text-start px-4">
        <h3>Result = ${result}</h3>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>

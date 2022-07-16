<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="https://thumbs.dreamstime.com/b/%D0%B0%D0%B2%D1%82%D0%BE%D0%B7%D0%B0%D0%BF%D1%87%D0%B0%D1%81%D1%82%D0%B8-%D0%B2%D1%80%D1%83%D1%87%D0%B0%D1%8E%D1%82-%D0%B2%D1%8B%D1%87%D0%B5%D1%80%D1%87%D0%B5%D0%BD%D0%BD%D0%BE%D0%B5-81704942.jpg" width="50px" height="50px" style="border-radius: 100%"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarContent" aria-expanded="false" aria-label="Togglenavigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/api/customers/home">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/api/customers/customerPage">Customers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/api/carDealers/carDealerPage">Car Dealers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/api/cars/carPage">Cars</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/">Web Socket</a>
                </li>
            </ul>
        </div>
    </div>

</nav>
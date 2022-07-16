<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div style="margin-top: 30px" class="row justify-content-center">
    <div class="col-lg-8 col-md-8 col-sm-9">

        <table class="table table-striped table-dark table-hover table-striped">
            <thead>
                <tr>
                    <th style="text-align: center; scope="col">ID</th>
                    <th style="text-align: center; scope="col">Model</th>
                    <th style="text-align: center; scope="col">Year</th>
                    <th style="text-align: center; scope="col">Horsepower</th>
                    <th style="text-align: center; scope="col">EngineCapacity</th>
                    <th style="text-align: center; scope="col">CarBody</th>
                    <th style="text-align: center; scope="col">Appetite</th>
                    <th style="text-align: center; scope="col">Price</th>
                    <th style="text-align: center; scope="col">Controll</th>
                    <th style="text-align: center; scope="col">Buttons</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="car" items="${cars}">

                <tr style="border-radius: 5px;">
                    <th style="padding-top: 11px;" scope="row" width="50px">${car.getId()}</th>
                    <td style="text-align: center; padding-top: 11px;" width="100px"> <c:out value="${car.getModel()}"/> </td>
                    <td style="text-align: center; padding-top: 11px;" width="100px"> <c:out value="${car.getYear()}"/> </td>
                    <td style="text-align: center; padding-top: 11px;" width="100px"> <c:out value="${car.getHorsepower()}"/> </td>
                    <td style="text-align: center; padding-top: 11px;" width="100px"> <c:out value="${car.getEngineCapacity()}"/> </td>
                    <td style="text-align: center; padding-top: 11px;" width="100px"> <c:out value="${car.getCarBody()}"/> </td>
                    <td style="text-align: center; padding-top: 11px;" width="100px"> <c:out value="${car.getAppetite()}"/> </td>
                    <td style="text-align: center; padding-top: 11px;" width="100px"> <c:out value="${car.getPrice()}"/> </td>
                    <td style="text-align: end" width="50px">
                        <form:form width="50px" method="post" action="carPage/editCarView" modelAttribute="car">
                            <form:input type="hidden" path="id" value="${car.getId()}"/>
                            <form:input type="hidden" path="model" value="${car.getModel()}"/>
                            <form:input type="hidden" path="year" value="${car.getYear()}"/>
                            <form:input type="hidden" path="horsepower" value="${car.getHorsepower()}"/>
                            <form:input type="hidden" path="engineCapacity" value="${car.getEngineCapacity()}"/>
                            <form:input type="hidden" path="carBody" value="${car.getCarBody()}"/>
                            <form:input type="hidden" path="appetite" value="${car.getAppetite()}"/>
                            <form:input type="hidden" path="price" value="${car.getPrice()}"/>
                            <input width="50px" type="submit" class="btn-sm btn-info" value="Edit"
                                   name="Submit"/>
                        </form:form>
                    </td>
                    <td style="text-align: end" width="50px">
                        <form:form width="50px" method="post" action="carPage/deleteCar" modelAttribute="car">
                            <form:input type="hidden" path="id" value="${car.getId()}"/>
                            <form:input type="hidden" path="model" value="${car.getModel()}"/>
                            <form:input type="hidden" path="year" value="${car.getYear()}"/>
                            <form:input type="hidden" path="horsepower" value="${car.getHorsepower()}"/>
                            <form:input type="hidden" path="engineCapacity" value="${car.getEngineCapacity()}"/>
                            <form:input type="hidden" path="carBody" value="${car.getCarBody()}"/>
                            <form:input type="hidden" path="appetite" value="${car.getAppetite()}"/>
                            <form:input type="hidden" path="price" value="${car.getPrice()}"/>
                            <input width="50px" type="submit" class="btn-sm btn-danger" name="delete" value="Delete"/>
                        </form:form>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="row" width="100px">
    <div class=" d-flex justify-content-center" style="margin-top: 10px; margin-bottom: 20px;" width="100px">
        <form:form width="80px" action="carPage/addCarView" method="post">
            <input type="submit" class="btn btn-warning" value="Add new"
                   name="Submit"/>
        </form:form>
    </div>
</div>

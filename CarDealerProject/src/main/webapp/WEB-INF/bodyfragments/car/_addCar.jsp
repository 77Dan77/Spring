<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="margin-top: 30px" class="row d-flex justify-content-center">
    <div class="col-lg-6 col-md-6 col-sm-9 align-items-center">
        <c:set var = "car" scope = "session" value = "${car}"/>
        <c:choose>
            <c:when test = "${car.getId() != null}">
                <p class="d-flex justify-content-center" style=" font-size: 21px; font-family: 'Oswald', sans-serif">Edit Car</p>
                <div class="d-flex justify-content-center">
                    <form:form cssStyle="margin-bottom: 20px; margin-top: 5px" method="post" action="editCar" modelAttribute="car">
                        <div class="form-group" style="width: 300px">
                            <form:input type="hidden" path="id" value="${car.getId()}"/>
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupModel">Model</label>
                            <form:input id="formGroupModel" class="form-control" path="model" placeholder="${car.getModel()}" value="${carD.getModel()}"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupYear">Year</label>
                            <form:input id="formGroupYear" class="form-control" path="year" placeholder="${car.getYear()}" value="${car.getYear()}"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupHorsepower">Horsepower</label>
                            <form:input id="formGroupHorsepower" class="form-control" path="horsepower" placeholder="${car.getHorsepower()}" value="${car.getHorsepower()}"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupEngineCapacity">Engine Capacity</label>
                            <form:input id="formGroupEngineCapacity" class="form-control" path="engineCapacity" placeholder="${car.getEngineCapacity()}" value="${car.getEngineCapacity()}"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupCarBody">Car Body</label>
                            <form:input id="formGroupCarBody" class="form-control" path="carBody" placeholder="${car.getCarBody()}" value="${car.getCarBody()}"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupAppetite">Appetite</label>
                            <form:input id="formGroupAppetite" class="form-control" path="appetite" placeholder="${car.getAppetite()}" value="${car.getAppetite()}"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupPrice">Price</label>
                            <form:input id="formGroupPrice" class="form-control" path="price" placeholder="${car.getPrice()}" value="${car.getPrice()}"/>
                        </div>
                        <div class="d-flex justify-content-center">
                            <input type="submit" class="btn btn-danger" name="add" value="Accept"/>
                        </div>
                    </form:form>
                </div>
            </c:when>

            <c:otherwise>
                <p class="d-flex justify-content-center" style=" font-size: 21px; font-family: 'Oswald', sans-serif">Add Car</p>
                <div class="d-flex justify-content-center">
                    <form:form cssStyle="margin-bottom: 20px; margin-top: 5px" method="post" action="addCar" modelAttribute="car">
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupId">Id</label>
                            <form:input required="true" id="formGroupId" class="form-control" path="id"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupModel">Model</label>
                            <form:input required="true" id="formGroupModel" class="form-control" path="model"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupYear">Year</label>
                            <form:input  id="formGroupYear" class="form-control" path="year"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupHorsepower">Horsepower</label>
                            <form:input  id="formGroupHorsepower" class="form-control" path="horsepower"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupEngineCapacity">Engine Capacity</label>
                            <form:input  id="formGroupEngineCapacity" class="form-control" path="engineCapacity"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupCarBody">Car Body</label>
                            <form:input  id="formGroupCarBody" class="form-control" path="carBody"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupAppetite">Appetite</label>
                            <form:input id="formGroupAppetite" class="form-control" path="appetite"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupPrice">Price</label>
                            <form:input required="true" id="formGroupPrice" class="form-control" path="price"/>
                        </div>
                        <div class="d-flex justify-content-center">
                            <input type="submit" class="btn btn-danger" name="add" value="Accept"/>
                        </div>
                    </form:form>
                </div>
            </c:otherwise>

        </c:choose>
    </div>
</div>
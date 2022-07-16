<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="margin-top: 30px" class="row d-flex justify-content-center">
    <div class="col-lg-6 col-md-6 col-sm-9 align-items-center">
        <c:set var = "carDealer" scope = "session" value = "${carDealer}"/>
        <c:choose>
            <c:when test = "${carDealer.getId() != null}">
                <p class="d-flex justify-content-center" style=" font-size: 21px; font-family: 'Oswald', sans-serif">Edit Car Dealer</p>
                <div class="d-flex justify-content-center">
                    <form:form cssStyle="margin-bottom: 20px; margin-top: 5px" method="post" action="editCarDealer" modelAttribute="carDealer">
                        <div class="form-group" style="width: 300px">
                            <form:input type="hidden" path="id" value="${carDealer.getId()}"/>
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupFname">First Name</label>
                            <form:input id="formGroupFname" class="form-control" path="name" placeholder="${carDealer.getName()}" value="${carDealer.getName()}"/>
                        </div>
                        <div class="d-flex justify-content-center">
                            <input type="submit" class="btn btn-danger" name="add" value="Accept"/>
                        </div>
                    </form:form>
                </div>
            </c:when>

            <c:otherwise>
                <p class="d-flex justify-content-center" style=" font-size: 21px; font-family: 'Oswald', sans-serif">Add Car Dealer</p>
                <div class="d-flex justify-content-center">
                    <form:form cssStyle="margin-bottom: 20px; margin-top: 5px" method="post" action="addCarDealer" modelAttribute="carDealer">
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupId">Id</label>
                            <form:input required="true" id="formGroupId" class="form-control" path="id"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupName">Name</label>
                            <form:input required="true" id="formGroupName" class="form-control" path="name"/>
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
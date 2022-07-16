<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="margin-top: 30px" class="row d-flex justify-content-center">
    <div class="col-lg-6 col-md-6 col-sm-9 align-items-center">
        <c:set var = "customer" scope = "session" value = "${customer}"/>
        <c:choose>
            <c:when test = "${customer.getId() != null}">
                <p class="d-flex justify-content-center" style=" font-size: 21px; font-family: 'Oswald', sans-serif">Edit Customer</p>
                <div class="d-flex justify-content-center">
                    <form:form cssStyle="margin-bottom: 20px; margin-top: 5px" method="post" action="editCustomer" modelAttribute="customer">
                        <div class="form-group" style="width: 300px">
                            <form:input type="hidden" path="id" value="${customer.getId()}"/>
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupFname">First Name</label>
                            <form:input id="formGroupFname" class="form-control" path="fname" placeholder="${customer.getFname()}" value="${customer.getFname()}"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupLname">Last Name</label>
                            <form:input id="formGroupLname" class="form-control" path="lname" placeholder="${customer.getLname()}" value="${customer.getLname()}"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupBalance">Balance</label>
                            <form:input id="formGroupBalance" class="form-control" path="balance" placeholder="${customer.getBalance()}" value="${customer.getBalance()}"/>
                        </div>
                        <div class="d-flex justify-content-center">
                            <input type="submit" class="btn btn-danger" name="add" value="Accept"/>
                        </div>
                    </form:form>
                </div>
            </c:when>

            <c:otherwise>
                <p class="d-flex justify-content-center" style=" font-size: 21px; font-family: 'Oswald', sans-serif">Add Customer</p>
                <div class="d-flex justify-content-center">
                    <form:form cssStyle="margin-bottom: 20px; margin-top: 5px" method="post" action="addCustomer" modelAttribute="customer">
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupFname">First Name</label>
                            <form:input required="true" id="formGroupFname" class="form-control" path="fname"/>
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupLname">Last Name</label>
                            <form:input required="true" id="formGroupLname" class="form-control" path="lname" />
                        </div>
                        <div class="form-group" style="width: 300px">
                            <label style="font-family: Bahnschrift, sans-serif; font-weight: 300" for="formGroupBalance">Balance</label>
                            <form:input required="true" id="formGroupBalance" class="form-control" path="balance" />
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
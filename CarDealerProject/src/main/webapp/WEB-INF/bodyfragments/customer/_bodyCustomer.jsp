<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div style="margin-top: 30px" class="row justify-content-center">
    <div class="col-lg-8 col-md-8 col-sm-9">

        <table class="table table-striped table-dark table-hover table-striped" style="border-radius: 10px !important;">
            <tbody>
        <c:forEach var="customer" items="${customers}">

                <tr style="border-radius: 5px;">

                    <th style="padding-top: 11px;" scope="row" width="50px">${customer.getId()}</th>
                    <td style="text-align: center; padding-top: 11px;" width="150px"> <c:out value="${customer.getFname()}"/> </td>
                    <td style="text-align: center; padding-top: 11px;" width="150px"> <c:out value="${customer.getLname()}"/> </td>
                    <td style="text-align: end" width="60px">
                        <form:form width="60px" method="post" action="customerPage/editCustomerView" modelAttribute="customer">
                            <form:input type="hidden" path="id" value="${customer.getId()}"/>
                            <form:input type="hidden" path="fname" value="${customer.getFname()}"/>
                            <form:input type="hidden" path="lname" value="${customer.getLname()}"/>
                            <form:input type="hidden" path="balance" value="${customer.getBalance()}"/>
                            <input width="60px" type="submit" class="btn-sm btn-info" value="Edit"
                                   name="Submit"/>
                        </form:form>
                    </td>
                    <td style="text-align: end" width="60px">
                        <form:form width="60px" method="post" action="customerPage/deleteCustomer" modelAttribute="customer">
                            <form:input type="hidden" path="id" value="${customer.getId()}"/>
                            <input width="60px" type="submit" class="btn-sm btn-danger" name="delete" value="Delete"/>
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
        <form:form width="80px" action="customerPage/addCustomerView" method="post">
            <input type="submit" class="btn btn-warning" value="Add new"
                   name="Submit"/>
        </form:form>
    </div>
</div>






<%--            <table border="0px" width="100%" align="center">--%>
<%--                <tr>--%>
<%--                    <td> <c:out value="${customer.getFname()}"/> </td>--%>
<%--                    <td> <c:out value="${customer.getLname()}"/> </td>--%>
<%--                    <td>--%>
<%--                        &lt;%&ndash;@elvariable id="Customer" type="kz.iitu.itse1908.daniyal.database.Customer"&ndash;%&gt;--%>
<%--                        <form:form method="post" action="customerPage/editCustomerView" modelAttribute="customer">--%>
<%--                            <form:input type="hidden" path="id" value="${customer.getId()}"/>--%>
<%--                            <form:input type="hidden" path="fname" value="${customer.getFname()}"/>--%>
<%--                            <form:input type="hidden" path="lname" value="${customer.getLname()}"/>--%>
<%--                            <form:input type="hidden" path="balance" value="${customer.getBalance()}"/>--%>
<%--                            <input type="submit" class="btn btn-info" value="Edit"--%>
<%--                                   name="Submit"/>--%>
<%--                        </form:form>--%>

<%--                    </td>--%>

<%--                    <td>--%>
<%--                        &lt;%&ndash;@elvariable id="Customer" type="kz.iitu.itse1908.daniyal.database.Customer"&ndash;%&gt;--%>
<%--                        <form:form method="post" action="customerPage/deleteCustomer" modelAttribute="customer">--%>
<%--                            <form:input type="hidden" path="id" value="${customer.getId()}"/>--%>
<%--                            <input type="submit" class="btn btn-danger" name="delete" value="Delete"/>--%>
<%--                        </form:form>--%>

<%--                    </td>--%>
<%--                </tr>--%>
<%--            </table>--%>
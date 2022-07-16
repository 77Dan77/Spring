<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div style="margin-top: 30px" class="row justify-content-center">
    <div class="col-lg-8 col-md-8 col-sm-9">

        <table class="table table-striped table-dark table-hover table-striped">
            <tbody>
            <c:forEach var="carDealer" items="${carDealers}">

                <tr style="border-radius: 5px;">
                    <th style="padding-top: 11px;" scope="row" width="50px">${carDealer.getId()}</th>
                    <td style="text-align: center; padding-top: 11px;" width="150px"> <c:out value="${carDealer.getName()}"/> </td>
                    <td style="text-align: end" width="60px">
                        <form:form width="60px" method="post" action="carDealerPage/editCarDealerView" modelAttribute="carDealer">
                            <form:input type="hidden" path="id" value="${carDealer.getId()}"/>
                            <form:input type="hidden" path="name" value="${carDealer.getName()}"/>
                            <input width="60px" type="submit" class="btn-sm btn-info" value="Edit"
                                   name="Submit"/>
                        </form:form>
                    </td>
                    <td style="text-align: end" width="60px">
                        <form:form width="60px" method="post" action="carDealerPage/deleteCarDealer" modelAttribute="carDealer">
                            <form:input type="hidden" path="id" value="${carDealer.getId()}"/>
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
        <form:form width="80px" action="carDealerPage/addCarDealerView" method="post">
            <input type="submit" class="btn btn-warning" value="Add new"
                   name="Submit"/>
        </form:form>
    </div>
</div>

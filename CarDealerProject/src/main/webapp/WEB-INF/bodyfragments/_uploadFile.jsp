<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="margin-top: 30px; margin-bottom: 30px" class="row d-flex justify-content-center">
    <div class="col-lg-6 col-md-6 col-sm-9 align-items-center">
<form:form method="POST" action="uploadFileMvc" enctype="multipart/form-data" modelAttribute="file">
    <table>
        <tr>
            <td><form:label path="file">Select a file to upload</form:label></td>
             <td>
                <input type="file" name="file" />
             </td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form:form>
    </div>
</div>

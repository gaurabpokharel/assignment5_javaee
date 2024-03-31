<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
		<h1>Edit Product</h1>
       <form:form method="POST" action="/SpringMVCPagination/editsaveproduct">  
      	<table >  
      	<tr>
      	<td></td>  
         <td><form:hidden  path="id" /></td>
         </tr> 
         <tr>  
          <td>Name : </td> 
          <td><form:input path="name"  /></td>
         </tr>  
         <tr>  
          <td>Price :</td>  
          <td><form:input path="price" /></td>
         </tr> 
         <tr> 
          <td>Quantity :</td>  
          <td><form:input path="quantity" /></td>
         </tr> 
         <tr>  
          <td> </td>  
          <td><input type="submit" value="Edit Save" /></td>  
         </tr>  
        </table>  
       </form:form>  

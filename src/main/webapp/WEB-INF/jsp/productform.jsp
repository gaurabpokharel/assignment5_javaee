<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

		<h1>Add New Product</h1>
       <form:form method="post" action="saveproduct">  
      	<table >  
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
          <td><input type="submit" value="Save" /></td>
                      </tr>  
        </table>  
       </form:form>  

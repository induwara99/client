<%@page import="model.Researcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/main.js"></script>
</head>
<body>
<div class="container"> 
		<div class="row">  
						
			<div class="col-8">       
				<h1 class="m-3">Researcher Management</h1>        
				
				<form id="formResearcher" name="formResearcher" method="post" action="researcher.jsp">  
					
					
					Full Name:  
					<input id="name" name="name" type="text" class="form-control form-control-sm">  
					
					<br> 
					Country:  
					<input id="country" name="country" type="text" class="form-control form-control-sm">  
					
					<br> 
					Specialization:  
					<input id="specialization" name="specialization" type="text" class="form-control form-control-sm">  
					
					<br> 
					Phone Number:  
					<input id="pnumber" name="pnumber" type="text" class="form-control form-control-sm">  
					
					<br>
					Age:  
					<input id="age" name="age" type="text" class="form-control form-control-sm">  
					
					 
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidresIDSave" name="hidresIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%    
						Researcher resObj = new Researcher();
						out.print(resObj.readResearcher());   
					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 

</body>

</html>
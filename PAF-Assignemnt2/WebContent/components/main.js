$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateResearcherForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidresIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ResearcherAPI",
		type : t,
		data : $("#formResearcher").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onResearcherSaveComplete(response.responseText, status);
		}
	});
}); 

function onResearcherSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidresIDSave").val("");
	$("#formResearcher")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidresIDSave").val($(this).closest("tr").find('#hidresIDUpdate').val());     
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#country").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#specialization").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#pnumber").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#age").val($(this).closest("tr").find('td:eq(4)').text()); 

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "ResearcherAPI",
		type : "DELETE",
		data : "resID=" + $(this).data("resid"),
		dataType : "text",
		complete : function(response, status)
		{
			onResearcherDeletedComplete(response.responseText, status);
		}
	});
});

function onResearcherDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateResearcherForm() {  
	// NAME  
	if ($("#name").val().trim() == "")  {   
		return "Insert Name.";  
		
	} 
	
	// Country  
	if ($("#country").val().trim() == "")  {   
		return "Insert Country.";  
		
	} 
	 
	//Specialization
	if ($("#specialization").val().trim() == "")  {   
		return "Insert Specialization."; 
	}
	 
	//Number 
	var tmppnumber  = $("#pnumber").val().trim();  
	if (!$.isNumeric(tmppnumber))  {   
		return "Insert a Numerical value for Phone Number.";  
		
	}
	
	//Age  
	var tmpage = $("#age").val().trim();  
	if (!$.isNumeric(tmpage))  {   
		return "Insert a numerical value for age.";  
		
	} 
	  
	 
	 return true; 
	 
}

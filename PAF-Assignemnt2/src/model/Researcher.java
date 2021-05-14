package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Researcher {
	
	
			//Method to Connect the DB 
			private Connection connect() {
				Connection con = null;
				
				try {
					 Class.forName("com.mysql.jdbc.Driver");
					 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

					//For testing          
					 System.out.print("Successfully Connected\n");
					 
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				return con; 
			}
			
			public String readResearcher() {  
				String output = "";  
				
				try {  
					Connection con = connect();  
					if (con == null)  {   
						return "Error While Reading The Database.";  
					} 

					//Prepare the Html table to be displayed   
					output = "<table border='1'><tr><th>name</th>"
							+ "<th>country</th><th>specialization</th>"
							+ "<th>pnumber</th><th>age</th>"
							+ "<th>Update</th><th>Remove</th>";


					  String query = "select * from researcher";   
					  Statement stmt = con.createStatement();   
					  ResultSet rs = stmt.executeQuery(query); 

					  //Iterate through the rows.   
					  while (rs.next())   {  

						  	String resID = Integer.toString(rs.getInt("resID"));
							String name = rs.getString("name");
							String country = rs.getString("country");
							String specialization = rs.getString("specialization");
							String pnumber = Integer.toString(rs.getInt("pnumber"));
							String age = Integer.toString(rs.getInt("age"));
							
						  
							//Add In-to Html Table    

							output += "<tr><td><input id='hidresIDUpdate' name='hidresIDUpdate' type='hidden' value='" + resID + "'>" + name + "</td>"; 
							output += "<td>" + country + "</td>";
						  	output += "<td>" + specialization + "</td>";
						  	output += "<td>" + pnumber + "</td>";
						  	output += "<td>" + age + "</td>";
						  
						  //Buttons     
						  	output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						  		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-resID='"+ resID +"'>"+"</td></tr>";

						} 
					  
					  con.close(); 

					  // Complete the html table   
					  output += "</table>"; 
					}
					catch (Exception e) {  
						output = "Error when reading the Researcher.";  
						System.err.println(e.getMessage()); 
					}

					return output;
				}
			
			//Insert Researcher
			public String insertResearcher(String name, String country, String specialization, String pnumber, String age) {
				String output = "";

				try {
					Connection con = connect();  

					if (con == null) {
						return "Error while connecting to the database";
					}

					// create a prepared statement   
					String query = "insert into researcher (`resID`,`name`,`country`,`specialization`,`pnumber`,`age`)"+" values (?, ?, ?, ?, ?, ?)";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					//Binding values 
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, country);
					preparedStmt.setString(4, specialization);
					preparedStmt.setString(5,pnumber);
					preparedStmt.setString(6, age);
	
					
					//execute the statement   
					preparedStmt.execute();   
					con.close(); 

					//Create JSON Object to show successful msg.
					String newResearcher = readResearcher();
					output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";
				}
				catch (Exception e) {  
					//Create JSON Object to show Error msg.
					output = "{\"status\":\"error\", \"data\": \"Error while Inserting Researcher.\"}";   
					System.err.println(e.getMessage());  
				} 

				 return output; 
			}
			
			//Update Researcher
			public String updateResearcher( String resID,String name, String country, String specialization, String pnumber, String age )  {   
				String output = ""; 
			 
			  try   {   
				  Connection con = connect();
			 
				  if (con == null)    {
					  return "Error while connecting to the database for updating."; 
				  } 
			 
			   // create a prepared statement    
				   String query = "UPDATE researcher SET name=?,country=?,specialization=?,pnumber=?,age=?";
					 
			   PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			   // binding values    
			   	preparedStmt.setString(1, name);
			   	preparedStmt.setString(2,country);
			   	preparedStmt.setString(3,specialization);
				preparedStmt.setInt(4,Integer.parseInt (pnumber));
				preparedStmt.setInt(5,Integer.parseInt(age));
			   
			 
			   // execute the statement    
			   preparedStmt.execute();    
			   con.close(); 
			 
			   //create JSON object to show successful msg
			   String newResearcher = readResearcher();
			   output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";
			   }   catch (Exception e)   {    
				   output = "{\"status\":\"error\", \"data\": \"Error while Updating Researcher Details.\"}";      
				   System.err.println(e.getMessage());   
			   } 
			 
			  return output;  
			  }
			
			public String deleteResearcher(String resID) {  
				String output = ""; 
			 
			 try  {   
				 Connection con = connect();
			 
			  if (con == null)   {    
				  return "Error while connecting to the database for deleting.";   
			  } 
			 
			  // create a prepared statement   
			  String query = "DELETE FROM researcher WHERE resID=?"; 
			 
			  PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			  // binding values   
			  preparedStmt.setInt(1, Integer.parseInt(resID));       
			  // execute the statement   
			  preparedStmt.execute();   
			  con.close(); 
			 
			  //create JSON Object
			  String newResearcher = readResearcher();
			  output = "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";
			  }  catch (Exception e)  {  
				  //Create JSON object 
				  output = "{\"status\":\"error\", \"data\": \"Error while Deleting Researcher.\"}";
				  System.err.println(e.getMessage());  
				  
			 } 
			 
			 return output; 
			 }

}

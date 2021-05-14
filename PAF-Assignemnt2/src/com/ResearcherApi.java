package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Researcher;


@WebServlet("/ResearcherApi")
public class ResearcherApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Researcher resObj = new Researcher();
	
    public ResearcherApi() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String result = resObj.insertResearcher(
				
				request.getParameter("name"), 
				request.getParameter("country"), 
				request.getParameter("specialization"), 
				request.getParameter("pnumber"), 
				request.getParameter("age"));
	
		response.getWriter().write(result);
	}

	
	
	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();  
		try  {   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";   
			scanner.close(); 
		 
		  String[] params = queryString.split("&");   
		  for (String param : params)   {
			  String[] p = param.split("=");    
			  map.put(p[0], p[1]);   
		  }  
		  
		}catch (Exception e)  {  
			
		} 
		return map;
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Map<String, String> param = getParasMap(request);
		
		
		String result = resObj.updateResearcher(
				
				param.get("hidresIDSave").toString(),
				param.get("name").toString().toString().replace("+", " "),   
				param.get("country").toString().toString().replace("+", " "), 
				param.get("specialization").toString().toString().replace("+", " "),
		 		param.get("pnumber").toString(),
		 		param.get("age").toString());
		
		response.getWriter().write(result);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Map<String, String> param = getParasMap(request);
		
		String result = resObj.deleteResearcher(param.get("resID").toString());
		
		response.getWriter().write(result);
	}

}

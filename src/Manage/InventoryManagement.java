package Manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Operation;
import data.Inventory;

public class InventoryManagement extends HttpServlet
{
	private static final long serialVersionUID = 9134037758839635775L;

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException 
	{
		
		PrintWriter out = res.getWriter(); 
		
		Operation<Inventory> operation = new Operation<>();
		List<Inventory> completeList=null;
		try {
			completeList = operation.readAll(new Inventory());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = "<html>\n<body>\n<form><input type=\"button\" value=\"Go back!\" onclick=\"history.back()\">\n</form><form action=\"create\" method=\"get\">\n<input type=\"submit\" value=\"create\">\n</form>\n";
		out.println(s);
		for (int counter = 0; counter < completeList.size(); counter++) { 		      
			out.print(completeList.get(counter)); 
			String id = Integer.toString(completeList.get(counter).getId());
			String result = id+"update";
			out.println("<br>");
			String str = "<form action=\"execute\" method=\"post\">\n" + 
					"  <select name=\"execute\">\n" + 
					"    <option value=\""+result+"\">Update</option>\n" + 
					"    <option value=\""+id+"delete\"\"\">Delete</option>\n" + 
					"  </select>\n" + 
					"  <input type=\"submit\">\n" + 
					"</form>";
			out.println(str);
	    }
		out.println("</body>\n</html>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException 
	{
		String ans=null;
		PrintWriter out = res.getWriter();
		String result = req.getParameter("execute");
		Pattern p=Pattern.compile("\\D+");
		Matcher m=p.matcher(result);
		while(m.find()) {
			ans = result.substring(m.start(), m.end());
		}
		if(ans.equals("update")) {
			RequestDispatcher dis=req.getRequestDispatcher("update");          
	        dis.forward(req, res);  
		}else {
////			res.sendRedirect("deleted");
			RequestDispatcher dis=req.getRequestDispatcher("delete");          
	        dis.forward(req, res);   
		}
		
	}
}

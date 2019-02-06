package Manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Operation;
import data.Inventory;

public class Update extends HttpServlet
{
	private static final long serialVersionUID = 7907344635123357756L;
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		int id = 0;
		PrintWriter out = res.getWriter();
		String result = req.getParameter("execute");
		Pattern p=Pattern.compile("\\d+");
		Matcher m=p.matcher(result);
		while(m.find()) {
			id = Integer.parseInt(m.group());
			//id = Integer.parseInt(result.substring(m.start(), m.end()));
		}
		
		Operation<Inventory> operation = new Operation<>();
		Inventory invent=null;
		try {
			invent = operation.read(id,new Inventory());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<body>\n" + 
				"<form action=\"update\" method=\"get\">\n" + 
				"		Id : <input type = \"text\" name=\"id\" value=\""+invent.getId()+"\" readonly><br>" + 
				"		Product Name: <input type = \"text\" name=\"prod_name\" value=\""+invent.getProd_name()+"\"><br>"
						+ "Product Quantity : <input type = \"text\" name=\"prod_quan\" value=\""+invent.getProd_quan()+"\"><br>" + 
						"Price : <input type = \"text\" name=\"price\" value=\""+invent.getPrice()+"\"><br>"
						+ "created_at : <input type = \"text\" name=\"created_at\" value=\""+invent.getCreated_at()+"\" readonly><br>"
								+ "update_at : <input type = \"text\" name=\"updated_at\" value=\""+invent.getUpdated_at()+"\" readonly><br>" +
				"		<input type=\"submit\">\n" + 
				"	</form>\n</body>\n" + 
				"</html>";
		out.println(s);
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		
		Operation<Inventory> operation = new Operation<>();
		Inventory inventory = new Inventory();
		
		int id = Integer.parseInt(req.getParameter("id"));
		String prod_name = req.getParameter("prod_name");
		int prod_quan = Integer.parseInt(req.getParameter("prod_quan"));
		float price = Float.parseFloat(req.getParameter("price"));
		String created = req.getParameter("created_at");
		long created_at ;
		created_at = Long.parseLong(created);
		Date date = new Date();
        
		
		inventory.setId(id);
		inventory.setProd_name(prod_name);
		inventory.setProd_quan(prod_quan);
		inventory.setPrice(price);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//inventory.setCreated_at(timestamp.getTime());
		inventory.setCreated_at(created_at);
		inventory.setUpdated_at(timestamp.getTime());
		
		Map<String,Object> map = inventory.toMap();
		
		operation.listUpdate(id,map,new Inventory());
		res.sendRedirect("read");
	}
}

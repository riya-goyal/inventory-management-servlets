package Manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Operation;
import data.Inventory;

public class Create extends HttpServlet
{
	private static final long serialVersionUID = -2633379850378583295L;

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException 
	{
		PrintWriter out = res.getWriter();
		String s = "<html>\n<body>\n<form>\n" + 
				"  		<input type=\"button\" value=\"Go back!\" onclick=\"history.back()\"><br>\n" + 
				"	</form><form action=\"create\" method=\"post\">Inventory Id<input type=\"number\" name=\"id\" min=1 required><br><br>Product name<input type=\"text\" name=\"prod_name\" required><br><br>"
				+ "Product quantity<input type=\"number\" name=\"prod_quan\" min=1><br><br>"
				+ "Product price<input type=\"number\" name=\"price\" min=1><br><br>"
				+ "<input type=\"submit\" value=\"submit\"></form>\n</body>\n</html>";
		out.println(s);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		int prod_quan=0,price=0;
		
		Operation<Inventory> inOperate = new Operation<>();
		PrintWriter out = res.getWriter();
		int id = Integer.parseInt(req.getParameter("id"));
		String prod_name = req.getParameter("prod_name");
		if(req.getParameter("prod_quan")!=null && !req.getParameter("prod_quan").isEmpty())
			prod_quan = Integer.parseInt(req.getParameter("prod_quan"));
		if(req.getParameter("price")!=null && !req.getParameter("price").isEmpty())
			price = Integer.parseInt(req.getParameter("price"));
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Inventory inventObj = new Inventory();
		inventObj.setId(id);
		inventObj.setProd_name(prod_name);
		inventObj.setProd_quan(prod_quan);
		inventObj.setPrice(price);
//		Date date = new Date();
//        inventObj.setCreated_at(new Timestamp(date.getTime()));
//        inventObj.setUpdated_at(new Timestamp(date.getTime()));
		inventObj.setCreated_at(timestamp.getTime());
		inventObj.setUpdated_at(timestamp.getTime());
		
		if(inOperate.checkValidId(id, inventObj)) {
			out.println("<!DOCTYPE html>\n" + 
					"<html>\n" + 
					"<body>\n" + 
					"Id already exists.<br><br>"
					+ "<form action=\"inventory\"><input type=\"submit\" value=\"back\"></from>" + 
					"</body>\n" + 
					"</html>");
			
		}else {
			inOperate.create(id,inventObj);
			res.sendRedirect("index1.html");
		}
		
	}
}

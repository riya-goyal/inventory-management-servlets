package Manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Operation;
import data.Inventory;

public class Delete extends HttpServlet 
{
	private static final long serialVersionUID = 6313797179311932513L;

	@SuppressWarnings("unused")
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
		operation.delete(id, new Inventory());
		res.sendRedirect("read");
	}
}

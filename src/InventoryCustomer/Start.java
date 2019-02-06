package InventoryCustomer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Start extends HttpServlet
{
	private static final long serialVersionUID = 4758280965277574916L;

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String system = req.getParameter("system");
		//System.out.println(system);
		PrintWriter out = res.getWriter();
		if(system.equals("Inventory")) {
			res.sendRedirect("inventory");
		}
	}
}

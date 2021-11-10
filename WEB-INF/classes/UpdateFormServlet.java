import java.io.*;
import java.io.IOException;  
import java.io.PrintWriter;   
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/UpdateFormServlet")  
public class UpdateFormServlet extends HttpServlet 
{  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<h1>Update Employee</h1>");  
        String acno=request.getParameter("acno");  
        int id=Integer.parseInt(acno);  
          
        Customers e=CustomersDao.getCustomersById(id);  
        out.print("<table>");  
        out.print("<form action='UpdateServlet' >"); 
        out.print("Name:<input type='text' name='cname' value='"+e.getCname()+"'/></br>");  
        out.print("Account Number:<input type='text' name='acno' value='"+e.getAcno()+"'readonly='readonly'/></br> ");  
        out.print("Country:");  
        out.print("<select name='city' style='width:150px'>");  
        out.print("<option>Hyderabad</option>");  
        out.print("<option>Secunderabad</option>");  
        out.print("<option>Medchal</option>");  
        out.print("</select><br/>");
        out.print("Account Type:");
        out.print("<select name='actype' style='width:150px'>");  
        out.print("<option>Savings</option>");  
        out.print("<option>Current</option>");  
        out.print("<option>FixedDeposit</option>");  
        out.print("</select><br/>");  
		out.print("Balance:<input type='text' name='bal' value='"+e.getBalance()+"'/></br>");  
		out.print("Branchcode:<input type='text' name='brcode' value='"+e.getBranchcode()+"'/></br>");  
        out.print("<input type='submit' value='Update'/>");  
        out.print("</form>");  
        out.print("</table>") ;
        out.close();  
    }  
}  
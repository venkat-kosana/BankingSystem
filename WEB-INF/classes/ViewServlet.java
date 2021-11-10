import java.io.*;
import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.List;   
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/ViewServlet")  
public class ViewServlet extends HttpServlet 
{  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        String accno=request.getParameter("accno");  
        int id=Integer.parseInt(accno);  
        Customers e1=CustomersDao.getCustomersById(id);  
		out.print("Customer Name: "+e1.getCname()+"</br>"+"AccountNumber: "+e1.getAcno()+"</br>"+"City: "+e1.getCity()+"</br>"+"Balance:"+e1.getBalance()+"</br>"+"Branchcode: "+e1.getBranchcode()+"<br/>"+"AccountType: "+e1.getActype());
        out.print("</br></br><a href='index.html'>Back</a>");       
        out.close();  
    }  
}  
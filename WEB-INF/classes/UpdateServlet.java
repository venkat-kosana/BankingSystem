import java.io.*;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/UpdateServlet")  
public class UpdateServlet extends HttpServlet 
{  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String acno=request.getParameter("acno");  
        int id=Integer.parseInt(acno);  
        String cname=request.getParameter("cname");  
        String city=request.getParameter("city");  
        String bal=request.getParameter("bal");  
        String brcode=request.getParameter("brcode");  
        String actype=request.getParameter("actype"); 
        Customers e=new Customers();  
        e.setCname(cname);  
        e.setAcno(id);  
        e.setCity(city);  
        e.setBalance(Integer.parseInt(bal));  
        e.setBranchcode(Integer.parseInt(brcode));  
        e.setActype(actype); 
        int status=CustomersDao.update(e);  
        if(status>0)
		{  
            response.sendRedirect("ViewingServlet");  
        }
        else
		{  
            out.println("Sorry! unable to update record");  
        }  
          
        out.close();  
    }  
  
}  
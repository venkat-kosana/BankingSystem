import java.io.IOException;  
import java.io.PrintWriter;    
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/SaveServlet")  
public class SaveServlet extends HttpServlet 
{  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String name=request.getParameter("name");  
        String acno=request.getParameter("acno");  
        String city=request.getParameter("city");  
        String bal=request.getParameter("bal");  
		String brcode=request.getParameter("brcode");  
        String actype=request.getParameter("actype");
        Customers e=new Customers();  
        e.setCname(name);  
        e.setAcno(Integer.parseInt(acno));  
        e.setCity(city);  
        e.setBalance(Integer.parseInt(bal));  
		e.setBranchcode(Integer.parseInt(brcode));
        e.setActype(actype);
          
        int status=CustomersDao.save(e);  
        if(status>0)
		{  
            out.print("<p>Record saved successfully!</p>");  
            request.getRequestDispatcher("index.html").include(request, response);  
        }
		else
		{  
            out.println("Sorry! unable to save record"); 
            out.print("<br/><a href='index.html'>Back</a>");  
        }  
		//out.print(name+" "+acno+" "+city+" "+bal+" "+brcode);
          
        out.close();  
    }  
  
}  
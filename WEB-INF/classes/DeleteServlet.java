import java.io.*;
import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/DeleteServlet")  
public class DeleteServlet extends HttpServlet 
{  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{  
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        String accno=request.getParameter("accno");  
        int id=Integer.parseInt(accno);  
        int status=CustomersDao.delete(id);   
		if(status>0){  
            out.print("<p>Record deleted successfully!</p>");  
            response.sendRedirect("index.html");  
        }else{  
            out.println("Sorry! unable to delete record");  
            out.print("<br/><a href='index.html'>Back</a>");  
        }  
    }  
}  
import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/LoginServlet")  
public class LoginServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                           throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String name=request.getParameter("name");  
        String password=request.getParameter("pass");  
        if(name.equals(""))
		{
			out.print("sorry, username or password error!");  
            request.getRequestDispatcher("login.html").include(request, response);  
		}	
		else
		{
			if(password.equals("admin123"))
			{  
				response.sendRedirect("homepage.html");
			}
			else
			{  
				out.print("sorry, username or password error!");  
				request.getRequestDispatcher("login.html").include(request, response);  
			}
		}			
        

          
        out.close();  
    }  
  
}  

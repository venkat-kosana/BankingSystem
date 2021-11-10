import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/ViewingServlet")  
public class ViewingServlet extends HttpServlet 
{  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<h1>Customers List</h1>");  
          
        List<Customers> list=CustomersDao.getAllEmployees();  
        
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");  // escape the quote marks
        out.println("<title></title>");
        out.println("<style>");     // start style
        // enclose style attributes withing the <style> </style> elements
        out.println("table {");        // note leading brace

        out.println("color:#333;");
        out.println("border: 1px collapse;");
        out.println("font-family: sans-serif;");
        out.println("font-size:20px;");
        out.println("}");          // note trailing brace for h1 style

        out.println("body {");
        out.println("background: url('../bankingsystem/banner.jpg');");
        out.println("background-repeat: no-repeat;");
        out.println("background-size: cover;");
        
        out.println("}");
        // add styles for other elements here using similar structure
        // note that separate lines are used for clarity -
        // all of the above could be one println
        out.println("</style>");  // terminate style
        out.println("</head>");
        out.println("<body>");
        out.print("<table border='1' width='100%'");  
        out.print("<tr>"+"<th>Name</th>"+"<th>Account Number</th>"+"<th>City</th>"+"<th>Balance</th> "+"<th>BranchCode</th>"+"<th>AccountType</th>"+"</tr>");  
        for(Customers e1:list)
		{  
         out.print("<tr><td>"+e1.getCname()+"</td><td>"+e1.getAcno()+"</td><td>"+e1.getCity()+"</td><td>"+e1.getBalance()+"</td><td>"+e1.getBranchcode()+"</td><td>"+e1.getActype()+"</td>"+"</tr>");  
        }  
        out.print("</table></br>");  
        out.print("<a href='index.html'>Back</a>");  
        out.println("</body>");
        out.println("</html>");
        out.close();  
    }  
}  

    
    /* background-attachment: fixed; */
    
    
    
    
    
import java.util.*;  
import java.sql.*;  
  
public class CustomersDao {  
  
    public static Connection getConnection()
	{  
        Connection con=null;  
        try
		{  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1","hr","hr");  
        }
		catch(Exception e)
		{
			System.out.println(e);
		}  
        return con;  
    }  
    public static int save(Customers e)
	{  
        int status=0;  
        try
		{  
            Connection con=CustomersDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("insert into bankcustomers(cname,acno,city,balance,branchcode,actype) values (?,?,?,?,?,?)");  
            ps.setString(1,e.getCname());  
            ps.setInt(2,e.getAcno());  
            ps.setString(3,e.getCity());  
            ps.setInt(4,e.getBalance());  
			ps.setInt(5,e.getBranchcode()); 
			ps.setString(6,e.getActype());
              
            status=ps.executeUpdate();  
              
            con.close();  
        }
		catch(Exception ex)
		{
			ex.printStackTrace();
		}  
          
        return status;  
    }
	public static int delete(int id)
	{  
        int status=0;  
        try
		{  
            Connection con=CustomersDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from bankcustomers where acno=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();               
            con.close();  
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}            
        return status;  
    }
	 public static Customers getCustomersById(int id)
	 {  
			Customers e=new Customers();  
			  
			try
			{  
				Connection con=CustomersDao.getConnection();  
				PreparedStatement ps=con.prepareStatement("select * from bankcustomers where acno=?");  
				ps.setInt(1,id);  
				ResultSet rs=ps.executeQuery();  
				if(rs.next())
				{  
					e.setCname(rs.getString(1));  
					e.setAcno(rs.getInt(2));  
					e.setCity(rs.getString(3));  
					e.setBalance(rs.getInt(4));  
					e.setBranchcode(rs.getInt(5));  
					e.setActype(rs.getString(6)); 
				}  
				con.close();  
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			} 			  
			return e;  
		}
	public static List<Customers> getAllEmployees()
	{  
			List<Customers> list=new ArrayList<Customers>();  
			  
			try
			{  
				Connection con=CustomersDao.getConnection();  
				PreparedStatement ps=con.prepareStatement("select * from bankcustomers");  
				ResultSet rs=ps.executeQuery();  
				while(rs.next())
				{  
					Customers e=new Customers();  
					e.setCname(rs.getString(1));  
					e.setAcno(rs.getInt(2));  
					e.setCity(rs.getString(3));  
					e.setBalance(rs.getInt(4));  
					e.setBranchcode(rs.getInt(5));  
					e.setActype(rs.getString(6)); 
					list.add(e);  
				}  
				con.close();  
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}  
			  
			return list;  
	}
	public static int update(Customers e)
	{  
			int status=0;  
			try
			{  
				Connection con=CustomersDao.getConnection();  
				PreparedStatement ps=con.prepareStatement("update bankcustomers set cname=?,city=?,balance=?,branchcode=?,actype=? where acno=?");  
				ps.setString(1,e.getCname());  
				ps.setString(2,e.getCity());
				ps.setInt(3,e.getBalance());
				ps.setInt(4,e.getBranchcode());
				ps.setString(5,e.getActype()); 
				ps.setInt(6,e.getAcno()); 
				status=ps.executeUpdate();  
				  
				con.close();  
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}  
			  
			return status;  
		}  	
}	
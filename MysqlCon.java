package CourseManagementSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class MysqlCon {
    Connection con;
    Statement st;
    public MysqlCon(){
        try{

            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework","root","");
            st=con.createStatement();

        }
        catch(Exception e)
        {
        	System.out.println(e);
        }
    }
}


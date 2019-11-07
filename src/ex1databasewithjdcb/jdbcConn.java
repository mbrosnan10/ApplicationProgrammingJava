
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcConn
{

	public static void main(String[] args) throws Exception
	{
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/books", "sduser", "pass");

		Statement stmt = con.createStatement();
		String query = "CREATE TABLE titles(id INTEGER PRIMARY KEY, first_name CHAR(50), last_name CHAR(75))";

		stmt.execute(query);
		System.out.println("Employee table created");
		String query1 = "aLTER TABLE employees ADD  CHAR(100) ";
		String query2 = "ALTER TABLE employees DROP COLUMN last_name";

		stmt.execute(query1);
		stmt.execute(query2);
		System.out.println("Address column added to the table & last_name column removed from the table");

		String query3 = "drop table employees";
		stmt.execute(query3);
		System.out.println("Employees table removed");
	}
}

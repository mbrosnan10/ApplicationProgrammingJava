package ex1databasewithjdcb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class EX1DATABASEWITHJDCB
{

	public static void main(String[] args)
	{
		String results = "";
		Connection connection = null;
		Statement statement = null;
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "sduser", "pass");
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT *  FROM titles  ");
			ResultSetMetaData metaData = resultSet.getMetaData();
			 int numberOfColumns = metaData.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++)
			{
//				results += metaData.getColumnName(i) + "\t\t\t\t";
				results += String.format("%-20s", metaData.getColumnName(i));
			}

			results += "\n";

			while (resultSet.next())
			{
				for (int i = 1; i <= numberOfColumns; i++)
				{
					results += String.format("%-20s", resultSet.getObject(i));
				}
				results += "\n\n";
			}
		} catch (SQLException sqlex)
		{
			System.out.println("Comms error " + sqlex);
		} finally
		{
			try
			{
				statement.close();
				connection.close();
			} catch (SQLException sqlex)
			{
				System.out.println("Error cleaning up " + sqlex);
			}
		}

		System.out.println(results);
	}
}

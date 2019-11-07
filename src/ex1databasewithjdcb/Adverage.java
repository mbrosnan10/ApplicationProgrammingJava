package ex1databasewithjdcb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Adverage
{

	static Connection connection = null;
	static final String sql = "SELECT AVG(Price) FROM titles";

	public static void main(String[] args)
	{
		//open connection 

		try
		{
			openDatabaseConnection();

			doWork();

		} catch (SQLException sqlex)
		{
			System.out.println("Comms error " + sqlex);
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException sqlex)
			{
				System.out.println("Error cleaning up " + sqlex);
			}
		}

	}
	//close connection 

	protected static void doWork() throws SQLException
	{
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();

			String results = createHeadings(metaData, numberOfColumns);

			results += CreateRowsData(resultSet, numberOfColumns);

			System.out.println(results);
		} catch (SQLException ex)
		{
			Logger.getLogger(Adverage.class.getName()).log(Level.SEVERE, null, ex);
		} finally
		{
			if (resultSet != null)
			{
				resultSet.close();
			}
			if (statement != null)
			{
				statement.close();
			}

		}
	}

	protected static String CreateRowsData(ResultSet resultSet, int numberOfColumns) throws SQLException
	{
		String results = "";
		while (resultSet.next())
		{
			for (int i = 1; i <= numberOfColumns; i++)
			{
				results += resultSet.getObject(i) + "\t\t";
			}
			results += System.lineSeparator();
		}
		return results;
	}

	protected static void openDatabaseConnection() throws SQLException
	{
		connection = DriverManager.getConnection(ProjectData.DataBaseServerURI, ProjectData.DataBaseServerUsername, ProjectData.DataBaseServerPassword);
	}

	private static String createHeadings(ResultSetMetaData metaData, int numberOfColumns) throws SQLException
	{
		String results = "";

		for (int i = 1; i <= numberOfColumns; i++)
		{
			results += metaData.getColumnName(i) + "\t";
		}
		results += "\n";

		return results;

	}

}

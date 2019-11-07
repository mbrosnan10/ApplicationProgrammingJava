
package ex1databasewithjdcb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
public class count {
     public static void main(String[] args) {
          String results = "";
        Connection connection = null;
        Statement statement = null;
        try{
             connection = DriverManager.getConnection(ProjectData.DataBaseServerURI, ProjectData.DataBaseServerUsername, ProjectData.DataBaseServerPassword);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(Price) FROM titles WHERE Price >='40'");
        ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                results += metaData.getColumnName(i) + "\t";
            }

            results += "\n";

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    results += resultSet.getObject(i) + "\t\t";
                }
                results += "\n";
            }
        }
        
        catch (SQLException sqlex) {
            System.out.println("Comms error " + sqlex);
        }
        
        finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlex) {
                System.out.println("Error cleaning up " + sqlex);
            }
        }
        
        System.out.println(results);
     }
}

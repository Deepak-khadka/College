package testingDatabaseConnectivity;
import java.sql.*;

public class DisplayAuthors {

    public static void main(String[] args) {
        try{
            String url = "jdbc:mysql://localhost:3306/mysql";
            Connection connection = DriverManager.getConnection(url, "root", "root");
            System.out.println("Connecting database ...");
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            resultSet = statement.executeQuery("SELECT * FROM books.authors where authors.last_name like 'Pandey'");

            while (resultSet.next()){
                System.out.println("Id : " + resultSet.getInt("id"));
                System.out.println("First Name : " + resultSet.getString("first_name"));
                System.out.println("------------------------");
            }
            connection.close();
        }

        catch (Exception exception){
            System.err.println("Got an exception !");
            System.err.println(exception.getMessage());
        }
    }

}

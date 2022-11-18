package jdbctest;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        /*
        connection string
        data base information, kako ces da se povezes sa data base
         */

        String dbUrl = "jdbc:oracle:thin:@18.212.188.149:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from employees");

        //next() --> move pointer to first row
        resultSet.next();

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));

        System.out.println(resultSet.getInt(2));


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }








}

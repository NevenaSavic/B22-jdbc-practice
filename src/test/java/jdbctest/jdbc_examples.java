package jdbctest;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@18.212.188.149:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //prvo sto radimo je move to next row
        resultSet.next();

        System.out.println(resultSet.getString(2));

        //sad trazimo tacno sta je vezano za ovaj columnindex 2
        //Administracija, 200, 1700 format
        //pomocu while loop radimo iteraciju
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2)
            + " - " + resultSet.getInt(3) + " - " + resultSet.getString(4));
        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }


    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //ovim metodama dozvoljavamo da se slobodno krecemo po date base i da only read, no updates the result
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //how to find out how many rows we have in the query
        //move to the last row
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        //to move before first row, after we use last method
        resultSet.beforeFirst();

        //print all second row information
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //get the database related data inside the dbMetadate object
        DatabaseMetaData dbMetaDate = connection.getMetaData();

        System.out.println("dbMetaDate.getUserName() = " + dbMetaDate.getUserName());
        System.out.println("dbMetaDate.getDatabaseProductName() = " + dbMetaDate.getDatabaseProductName());
        System.out.println("dbMetaDate.getDatabaseProductVersion() = " + dbMetaDate.getDatabaseProductVersion());
        System.out.println("dbMetaDate.getDriverName() = " + dbMetaDate.getDriverName());
        System.out.println("dbMetaDate.getDriverVersion() = " + dbMetaDate.getDriverVersion());

        //get the resultSet meta date
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println(columnCount);

        //getting column name
        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(1));
        System.out.println("resultSetMetaData.getColumnName(2) = " + resultSetMetaData.getColumnName(2));

        //get all the column name dinamicly
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(resultSetMetaData.getColumnName(i));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }




}

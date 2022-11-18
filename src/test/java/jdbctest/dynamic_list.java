package jdbctest;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@18.212.188.149:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select first_name, last_name, salary, job_id\n " +
                "from employees\n" +
                "where rownum <6");

        //in order to get column names we need to have resultset metadata
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //move to the first row
        resultSet.next();

        //creating list for keeping all the informations
        List<Map<String, Object>> queryData = new ArrayList<>();

        int countColomn = rsmd.getColumnCount();

        //loop throug each row
        while (resultSet.next()){

            Map<String, Object> row = new HashMap<>();

            //some code to fill in dynamically

            for (int i = 0; i <= countColomn; i++) {
               row.put(rsmd.getColumnName(i), resultSet.getObject(i));
            }




            //add ready map row to the list
            queryData.add(row);
        }
        //print each row inside the list
            for (Map<String, Object> row : queryData){
                System.out.println(row.toString());
            }



        Map<String, Object> row1 = new HashMap<>();
        row1.put(rsmd.getColumnName(1), resultSet.getString(1));
        row1.put(rsmd.getColumnName(2), resultSet.getString(2));
        row1.put(rsmd.getColumnName(3), resultSet.getString(3));
        row1.put(rsmd.getColumnName(4), resultSet.getString(4));

        System.out.println(row1.toString());

        //moves to next row
        resultSet.next();

        Map<String, Object> row2 = new HashMap<>();
        row2.put("first_name", "Nenna");
        row2.put("last_name", "Kochhar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");

        System.out.println(row2.toString());

        //adding rows one by one in the list
        queryData.add(row1);
        queryData.add(row2);










        //close connection
        resultSet.close();
        statement.close();
        connection.close();





    }

}

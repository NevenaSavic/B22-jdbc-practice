package jdbctest;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class DButilPractice {

    @Test
    public void test1(){

        DBUtils.createConnection("jdbc:oracle:thin:@18.212.188.149:1521:XE", "hr", "hr");

        String query = "SELECT first_name, last_name, salary, job_id\n" +
                "from employees\n" +
                "where rownum < 6";

        List<Map<String, Object>> queryData= DBUtils.getQueryResultMap(query);

        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }
        //ovako zatvaramo sad metodu
        DBUtils.destroy(); //metoda je vec napravljena

    }

    @Test
    public void test2(){

        DBUtils.createConnection("jdbc:oracle:thin:@18.212.188.149:1521:XE", "hr", "hr");

        String query = "SELECT first_name, last_name, salary, job_id\n" +
                "from employees\n" +
                "where rownum < 2";

        //kad pravimo samo za jedan red, onda je to mapa a ne list

        Map<String, Object> rowData = DBUtils.getRowMap(query);

        System.out.println(rowData.toString());

        //ovako zatvaramo sad metodu
        DBUtils.destroy(); //metoda je vec napravljena

    }
}


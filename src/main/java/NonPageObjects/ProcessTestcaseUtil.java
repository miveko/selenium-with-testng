package NonPageObjects;

import base.objects.BaseTest;
import base.objects.Testcase;
import org.testng.Assert;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static NonPageObjects.Constants.*;

public class ProcessTestcaseUtil extends BaseTest {
    public void processTestcase(Testcase tc, String msg) {
        if(msg.equals(OK)) {
            tc.setStatus(PASSED);
        } else if(msg.equals(SKIP)) {
            tc.setStatus(SKIPPED);
        } else {
            tc.setStatus(FAILED);
            Assert.assertEquals(msg, OK);
        }

        tc.setMessage(msg);
        //TODO save tc to database
    }


    public List<HashMap<String, Object>> getSqlData(String query) {
        String url = automationDbConnString;
        List<HashMap<String, Object>> rows = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            // create a connection to the database
            System.out.println("Connection to SQLite has been established.");
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();

            while (rs.next()) {
                HashMap<String, Object> columns = new HashMap<>();
                for (int i = 1; i <= colCount; i++) {
                    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
                }

                rows.add(columns);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rows;
    }
}

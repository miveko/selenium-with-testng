package NonPageObjects;

import base.objects.BaseTest;
import base.objects.Testcase;
import org.testng.Assert;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static NonPageObjects.Constants.*;

public class HelpUtils extends BaseTest {
    public void processTestcase(Testcase tc, String msg) {
        if(msg.equals(OK)) {
            tc.setStatus(PASSED);
        } else if(msg.equals(SKIP)) {
            tc.setStatus(SKIPPED);
        } else {
            tc.setStatus(FAILED);
        }

        tc.setMessage(msg);
        saveTestcaseResult(tc);
        Assert.assertEquals(msg, OK);
    }


    public List<HashMap<String, Object>> getSqlData(String query) {
        List<HashMap<String, Object>> rows = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(automationDbConnString)) {
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

    public void saveTestcaseResult(Testcase tc) {
        String timestamp = new Timestamp(System.currentTimeMillis()).toString();
        try (Connection conn = DriverManager.getConnection(automationDbConnString);
             PreparedStatement pstmt = conn.prepareStatement(SAVE_TESTCASE_RESULT_QUERY)) {
            pstmt.setString(1, tc.getExecutionGuid());
            pstmt.setString(2, tc.getTestcaseName());
            pstmt.setString(3, tc.getTestcaseDescription());
            pstmt.setString(4, tc.getStatus());
            pstmt.setString(5, tc.getMessage());
            pstmt.setString(6, timestamp);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

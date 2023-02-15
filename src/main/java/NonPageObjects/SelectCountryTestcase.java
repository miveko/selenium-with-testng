package NonPageObjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectCountryTestcase {
    private String testcaseName;

    public SelectCountryTestcase(String testcaseName, String testcaseDescription, String type, String country, String currency) {
        this.testcaseName = testcaseName;
        this.testcaseDescription = testcaseDescription;
        this.type = type;
        this.country = country;
        this.currency = currency;
    }

    private String testcaseDescription;
    private String type;
    private String country;
    private String currency;

    public SelectCountryTestcase(Object[] singleTc) {
        testcaseName = String.valueOf(singleTc[0]);
        testcaseDescription =  String.valueOf(singleTc[1]);
        type = String.valueOf(singleTc[2]);
        country = String.valueOf(singleTc[3]);
        currency = String.valueOf(singleTc[4]);
    }

    public static Object[][] map (List<HashMap<String, Object>> rows) {
//        SelectCountryTestcase[] tcList = new SelectCountryTestcase[rows.size()];
        Object[][] tcList = new Object[rows.size()][];
        try {
            int i = 0;
            for(HashMap<String, Object> row : rows) {
//                SelectCountryTestcase singleTc = new SelectCountryTestcase();
//                singleTc.setTestcaseName(row.get("testcase_name").toString());
//                singleTc.setTestcaseDescription(row.get("testcase_description").toString());
//                singleTc.setType(row.get("testcase_type").toString());
//                singleTc.setCountry(row.get("country").toString());
//                singleTc.setCurrency(row.get("currency").toString());
//                tcList[i] = singleTc;
                Object[] singleTc = new Object[5];
                singleTc[0] = row.get("testcase_name").toString();
                singleTc[1] = row.get("testcase_description").toString();
                singleTc[2] = row.get("testcase_type").toString();
                singleTc[3] = row.get("country").toString();
                singleTc[4] = row.get("currency").toString();
                tcList[i] = singleTc;
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }

        return tcList;
    }

    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public String getTestcaseDescription() {
        return testcaseDescription;
    }

    public void setTestcaseDescription(String testcaseDescription) {
        this.testcaseDescription = testcaseDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

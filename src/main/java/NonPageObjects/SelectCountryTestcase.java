package NonPageObjects;

import java.util.HashMap;
import java.util.List;

public class SelectCountryTestcase {
    private final String testcaseName;
    private final String testcaseDescription;
    private final String type;
    private final String country;
    private final String currency;

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

    public String getTestcaseDescription() {
        return testcaseDescription;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }
}

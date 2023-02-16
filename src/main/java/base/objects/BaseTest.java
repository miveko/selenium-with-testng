package base.objects;

import java.io.*;
import java.util.Properties;
import java.util.UUID;

public class BaseTest {
    protected static String automationDbConnString;
    protected static Properties prop;
    public static final UUID executionGuid;
    protected static String testType;
    protected static double rate_amount_threshold;

    static {
        executionGuid = java.util.UUID.randomUUID();

        try (InputStream input = new FileInputStream(".\\src\\main\\resources\\config.properties")) {
            prop = new Properties();
            prop.load(input);
            automationDbConnString = prop.getProperty("auto_db_conn_string");
            rate_amount_threshold = Double.parseDouble(prop.getProperty("rate_amount_threshold"));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}

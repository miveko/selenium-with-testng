package base.objects;

import java.io.*;
import java.util.Properties;
import java.util.UUID;

public class BaseTest {
    protected static String automationDbConnString;
    protected static Properties prop;
    public static final UUID executionGuid;

    static {
        executionGuid = java.util.UUID.randomUUID();

        try (InputStream input = new FileInputStream(".\\src\\main\\resources\\config.properties")) {
            prop = new Properties();
            prop.load(input);
            automationDbConnString = prop.getProperty("auto_db_conn_string");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}

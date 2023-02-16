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
            Class.forName("org.sqlite.JDBC");
            prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            automationDbConnString = prop.getProperty("auto_db_conn_string");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

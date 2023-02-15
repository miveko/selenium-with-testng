package Listeners;

import base.objects.BaseTest;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class XmlSuiteListener implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        // When <suite> tag starts
        System.out.println("onStart: before suite starts");
        try (InputStream input = new FileInputStream("./resources/config.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            BaseTest.setAutomationDbConnString(prop.getProperty("auto_db_conn_string"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        // When <suite> tag completes
        System.out.println("onFinish: after suite completes");
    }
}

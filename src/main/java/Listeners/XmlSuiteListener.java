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
    }

    @Override
    public void onFinish(ISuite suite) {
        // When <suite> tag completes
        System.out.println("onFinish: after suite completes");
    }
}

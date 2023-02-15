package base.objects;

public class BaseTest {
    protected static String automationDbConnString;

    public static String getAutomationDbConnString() {
        return automationDbConnString;
    }

    public static void setAutomationDbConnString(String automationDbConnString) {
        BaseTest.automationDbConnString = automationDbConnString;
    }

}

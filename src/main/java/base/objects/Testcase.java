package base.objects;


import static NonPageObjects.Constants.NOT_EXECUTED;

public class Testcase {
    private final String testcaseName;
    private String testcaseDescription;
    private final String executionGuid;
    private String status;
    private String message;

    public Testcase(String testcaseName) {
        this.testcaseName = testcaseName;
        testcaseDescription = "";
        status = NOT_EXECUTED;
        message = "";
        executionGuid = String.valueOf(BaseTest.executionGuid);
    }

    public String getTestcaseName() {
        return testcaseName;
    }

    public String getTestcaseDescription() {
        return testcaseDescription;
    }

    public void setTestcaseDescription(String testcaseDescription) {
        this.testcaseDescription = testcaseDescription;
    }

    public String getExecutionGuid() {
        return executionGuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

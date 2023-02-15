package base.objects;


import static NonPageObjects.Constants.NOT_EXECUTED;

public class Testcase {
    private String testcaseName;
    private String testcaseDescription;
    private String executionGuid;
    private String status;
    private String message;

    public Testcase(String testcaseName) {
        this.testcaseName = testcaseName;
        testcaseDescription = "";
        status = NOT_EXECUTED;
        message = "";
        //TODO initialize the executionGuidField;
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

    public String getExecutionGuid() {
        return executionGuid;
    }

    public void setExecutionGuid(String executionGuid) {
        this.executionGuid = executionGuid;
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
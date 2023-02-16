package NonPageObjects;

import java.time.Duration;
public class Constants {
     public static final Duration DEFAULT_IMPLICIT_WAIT_OF_SECONDS = Duration.ofSeconds(5);
     public static final String PASSED = "Passed";
     public static final String NOT_EXECUTED = "Not executed";
     public static final String FAILED = "Failed";
     public static final String SKIPPED = "Skipped";
     public static final String OK = "OK";
     public static final String SKIP = "skip";
     public static final String SMOKE = "Smoke";
     public static final String REGRESSION = "Regression";
     public static final String GET_TESTCASES_QUERY = "SELECT * FROM @TestcaseGroup@_testcase_dataset  " +
             "WHERE active=1 ORDER BY priority;";
     public static final String SAVE_TESTCASE_RESULT_QUERY = "INSERT INTO testcase_result (execution_guid, " +
             "testcase_name, testcase_description, status, message, timestamp) VALUES(?, ?, ?, ?, ?, ?);";
}

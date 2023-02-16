package Test;
import base.objects.BaseTest;
import NonPageObjects.HelpUtils;
import NonPageObjects.SelectCountryTestcase;
import base.objects.Testcase;
import Pages.CurrencyConversionCalculatorPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import static NonPageObjects.Constants.*;
import static PageElements.CurrencyConversionCalculatorElements.*;

public class CurrencyConversionCalculatorTests extends BaseTest {
    private WebDriver driver;
    private HelpUtils pTcUtil;
    private Testcase testcase;
    private String msg;
    CurrencyConversionCalculatorPage page;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        pTcUtil = new HelpUtils();
        page = new CurrencyConversionCalculatorPage(driver);
        page.loadPage();
    }

    @DataProvider(name = "SelectCountryTestcases")
    public Object[][] dpMethod(){
        String sqlQuery = GET_TESTCASES_QUERY.replace("@TestcaseGroup@", "select_country");
        List<HashMap<String, Object>> resultSet = pTcUtil.getSqlData(sqlQuery);

        return SelectCountryTestcase.map(resultSet);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test(groups= {SMOKE,REGRESSION})
    public void sellAmountClearedWhenBuyAmountFilled() {
        try {
            msg = "OK";
            testcase = new Testcase("sellAmountClearedWhenBuyAmountFilled");
            testcase.setTestcaseDescription("Verify the sell amount input box is cleared " +
                    "when an amount is entered in the buy amount input box");
            if(input_sellAmount().getText().length() == 0)
                page.fillSellAmount();
            page.fillBuyAmount();
            Assert.assertEquals(input_sellAmount().getText().length(), 0);
        } catch (Exception e) {
            msg = e.toString();
        } finally {
            pTcUtil.processTestcase(testcase, msg);
        }
    }
    @Test(groups= {SMOKE,REGRESSION})
    public void buyAmountClearedWhenSellAmountFilled() {
        try {
            testcase = new Testcase("buyAmountClearedWhenSellAmountFilled");
            testcase.setTestcaseDescription("Verify the buy amount input box is cleared " +
                    "when an amount is entered in the sell amount input box");
            if(input_buyAmount().getText().length() == 0)
                page.fillBuyAmount();
            page.fillSellAmount();
            Assert.assertEquals(input_buyAmount().getText().length(), 0);
        } catch (Exception e) {
            testcase.setStatus(FAILED);
            testcase.setMessage(e.toString());
        } finally {
            testcase.setStatus(PASSED);
            testcase.setMessage("OK");
        }
    }

    @Test(groups= {REGRESSION})
    public void sellAmountNegativeInput() {

    }

    @Test(groups= {REGRESSION})
    public void buyAmountNegativeInput() {

    }

    @Test (groups= {SMOKE,REGRESSION}, dataProvider = "SelectCountryTestcases")
    public void selectCountryFromFooter(Object[] singleTc) {
        try {
            SelectCountryTestcase tc = new SelectCountryTestcase(singleTc);
            msg = "OK";
            testcase = new Testcase(tc.getTestcaseName());
            testcase.setTestcaseDescription(tc.getTestcaseDescription());
            page.selectCountry(tc.getCountry());
            String currency = _span_currencySelected().getText();
            Assert.assertEquals(currency, tc.getCurrency());
            //TODO check rates changed
        } catch (Exception e) {
            msg = e.toString();
        } finally {
            pTcUtil.processTestcase(testcase, msg);
        }
    }
}
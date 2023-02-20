package Test;
import NonPageObjects.RatesCompare;
import NonPageObjects.Validation;
import base.objects.BaseTest;
import NonPageObjects.HelpUtils;
import NonPageObjects.SelectCountryTestcase;
import base.objects.Testcase;
import Pages.CurrencyConversionCalculatorPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.*;
import java.util.stream.Collectors;

import static NonPageObjects.Constants.*;
import static PageElements.CurrencyConversionCalculatorElements.*;

public class CurrencyConversionCalculatorTests extends BaseTest {
    private WebDriver driver;
    private HelpUtils pTcUtil;
    private Testcase testcase;
    private String msg;
    CurrencyConversionCalculatorPage page;

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext context) {
        driver = new ChromeDriver();
        pTcUtil = new HelpUtils();
        page = new CurrencyConversionCalculatorPage(driver);
        page.loadPage();
        testType = context.getCurrentXmlTest().getSuite().getName();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        msg = "OK";
    }

    @DataProvider(name = "SelectCountryTestcases")
    public Iterator<Object[]> dpMethod(){
        String sqlQuery = GET_TESTCASES_QUERY.replace("@TestcaseGroup@", "select_country");
        List<HashMap<String, Object>> resultSet = pTcUtil.getSqlData(sqlQuery);
        if(testType.equals(SMOKE))
            resultSet = resultSet.stream()
                    .filter(c -> c.get("testcase_type").equals(SMOKE))
                    .collect(Collectors.toList());

        List<SelectCountryTestcase> list =  SelectCountryTestcase.map(resultSet);
        Collection<Object []> filteredTestcases = new ArrayList<>();
        for(SelectCountryTestcase eachTestInput : list) {
            filteredTestcases.add(new Object[] {eachTestInput});
        }
        return  filteredTestcases.iterator();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test(groups= {SMOKE,REGRESSION}, priority = 1)
    public void sellAmountClearedWhenBuyAmountFilled() {
        try {
            testcase = new Testcase("SellAmountClearedWhenBuyAmountFilled");
            testcase.setTestcaseDescription("Verify the sell amount input box is cleared " +
                    "when an amount is entered in the buy amount input box");
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_OF_SECONDS);
            if(input_sellAmount().getAttribute("value").length() == 0)
                    page.fillSellAmount();
            page.fillBuyAmount();
            Assert.assertEquals(input_sellAmount().getAttribute("value").length(), 0);
        } catch (Throwable thr) {
            msg = thr.toString();
        } finally {
            pTcUtil.processTestcase(testcase, msg);
        }
    }
    @Test(groups= {SMOKE,REGRESSION}, priority = 2)
    public void buyAmountClearedWhenSellAmountFilled() {
        try {
            testcase = new Testcase("BuyAmountClearedWhenSellAmountFilled");
            testcase.setTestcaseDescription("Verify the buy amount input box is cleared " +
                    "when an amount is entered in the sell amount input box");
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_OF_SECONDS);
            if(input_buyAmount().getAttribute("value").length() == 0)
                page.fillBuyAmount();
            page.fillSellAmount();
            Assert.assertEquals(input_buyAmount().getAttribute("value").length(), 0);
        } catch (Throwable thr) {
           msg = thr.toString();
        } finally {
            pTcUtil.processTestcase(testcase, msg);
        }
    }

    @Test(groups= {REGRESSION}, priority = 3)
    public void sellAmountInvalidInput() {
        try {
            testcase = new Testcase("SellAmountInvalidInput");
            testcase.setTestcaseDescription("Validate sell amount input field doesn't accept negative value");
            page.fillSellAmount("fjdslkj_;a-z");
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_OF_SECONDS);
            Assert.assertEquals(input_sellAmount().getAttribute("value").length(), 0);
        } catch (Throwable thr) {
            msg = thr.toString();
        } finally {
            pTcUtil.processTestcase(testcase, msg);
        }
    }

    @Test(groups= {REGRESSION}, priority = 4)
    public void buyAmountNegativeInput() {
        try {
            testcase = new Testcase("BuyAmountInvalidInput");
            testcase.setTestcaseDescription("Validate buy amount input field doesn't accept negative value");
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_OF_SECONDS);
            page.fillBuyAmount("fjdslkj_;a-z");
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_OF_SECONDS);
            Assert.assertEquals(input_buyAmount().getAttribute("value").length(), 0);
        } catch (Throwable thr) {
            msg = thr.toString();
        } finally {
            pTcUtil.processTestcase(testcase, msg);
        }
    }

    @Test (groups= {SMOKE,REGRESSION}, dataProvider = "SelectCountryTestcases", priority = 5)
    public void selectCountryFromFooter(SelectCountryTestcase[] testc) {
        try {
            SelectCountryTestcase tc = testc[0];
            testcase = new Testcase(tc.getTestcaseName());
            testcase.setTestcaseDescription(tc.getTestcaseDescription());
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_OF_SECONDS);
            page.selectCountry(tc.getCountry());
            String currency = _span_currencySelected().getText();
            while (currency == null || currency.length() == 0) {
                driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_OF_SECONDS);
                currency = _span_currencySelected().getText();
            }

            Assert.assertEquals(currency, tc.getCurrency());

            boolean persistsIn_ConvertTo_ListOfCurrencies = false;
            for(WebElement webElement : _currenciesInRatesToList()) {
                if(webElement.getText().trim().startsWith(tc.getCurrency())) {
                    persistsIn_ConvertTo_ListOfCurrencies = true;
                    break;
                }
            }

            Assert.assertFalse(persistsIn_ConvertTo_ListOfCurrencies);
        } catch (Throwable thr) {
            msg = thr.toString();
        } finally {
            pTcUtil.processTestcase(testcase, msg);
        }
    }

    @Test(groups = {SMOKE, REGRESSION}, priority = 6)
    public void checkNegativeBankVsPayseraAmounts() {
        try {
            testcase = new Testcase("CheckNegativeBankVsPayseraAmounts");
            testcase.setTestcaseDescription("Check wheather text in red is displayed " +
                    "when bank provider's exchange amount for selling is lower than the amount, provided by Paysera");
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_OF_SECONDS);
            page.selectCountry("Lithuania");
            List<RatesCompare> ratesCompares = new ArrayList<>();
            List<WebElement> rows = _tr_curenciesRates();
            for(WebElement row : rows) {
               RatesCompare ratesCompare = page.extractRatesComparisons(row);
               ratesCompares.add(ratesCompare);
            }

            msg = Validation.validateNegativeRateAmounts(ratesCompares, BaseTest.rate_amount_threshold);
        } catch (Throwable thr) {
            msg = thr.toString();
        } finally {
            pTcUtil.processTestcase(testcase, msg);
        }
    }

}
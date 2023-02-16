package Pages;
import PageElements.CurrencyConversionCalculatorElements;
import org.openqa.selenium.WebDriver;
import static NonPageObjects.Constants.PAGE_LOAD_WAIT_TIME;
import static PageElements.CurrencyConversionCalculatorElements.*;

public class CurrencyConversionCalculatorPage {

    private final WebDriver driver;

    public CurrencyConversionCalculatorPage(WebDriver driver) {
        this.driver = driver;
        new CurrencyConversionCalculatorElements(driver);
    }

    public void loadPage() {
        driver.manage().timeouts().implicitlyWait(PAGE_LOAD_WAIT_TIME);
        driver.get("https://www.paysera.lt/v2/en-LT/fees/currency-conversion-calculator");
    }

    public String searchText() {
        return search_button().getAttribute("value");
    }

    public void clearSellAmount() {
        input_sellAmount().clear();
    }

    public void clearBuyAmount() {
        input_buyAmount().clear();
    }

    public void fillBuyAmount(String amount) {
        if(amount.length() > 0) {
            input_buyAmount().clear();
            input_buyAmount().sendKeys(amount);
        } else {
            fillBuyAmount();
        }
    }

    public void fillBuyAmount() {
        input_buyAmount().clear();
        input_buyAmount().sendKeys("5");
    }

    public void fillSellAmount(String amount) {
        if(amount.length() > 0) {
            input_sellAmount().clear();
            input_sellAmount().sendKeys(amount);
        } else {
            fillSellAmount();
        }
    }

    public void fillSellAmount() {
        input_sellAmount().clear();
        input_sellAmount().sendKeys("5");
    }

    public void selectCountry(String country) {
        _span_carret().click();
        _dropdown_countries().click();
        _link_country(country);
    }
}

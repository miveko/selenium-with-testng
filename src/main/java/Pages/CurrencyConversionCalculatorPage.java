package Pages;
import NonPageObjects.RatesCompare;
import PageElements.CurrencyConversionCalculatorElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static NonPageObjects.Constants.DEFAULT_IMPLICIT_WAIT_OF_SECONDS;
import static PageElements.CurrencyConversionCalculatorElements.*;

public class CurrencyConversionCalculatorPage {

    private final WebDriver driver;

    public CurrencyConversionCalculatorPage(WebDriver driver) {
        this.driver = driver;
        new CurrencyConversionCalculatorElements(driver);
    }

    public void loadPage() {
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_OF_SECONDS);
        driver.get("https://www.paysera.lt/v2/en-LT/fees/currency-conversion-calculator");
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
        _link_country(country).click();
    }

    public RatesCompare extractRatesComparisons(WebElement row) {
        RatesCompare ratesCompare = new RatesCompare();
        List<WebElement> tds = row.findElements(By.tagName("td"));
        for(int i = 0; i < tds.size(); i++) {
            switch (i) {
                case 0:
                    ratesCompare.setCurrency(tds.get(i).getText());
                    break;
                case 3:
                    try {
                        if(tds.get(i).getText().contains("-")) {
                            ratesCompare.setPayseraAmount("");
                        } else {
                            WebElement spanPayseraAmount = tds.get(i).findElement(By.cssSelector("span[class='ng-binding']"));
                            ratesCompare.setPayseraAmount(spanPayseraAmount.getText());
                        }
                    } catch (Exception e) {
                        ratesCompare.setPayseraAmount("");
                        System.err.println("Unable to extract Paysera rate/amount");
                    }
                    break;
                default:
                    if(i > 3 && ratesCompare.getPayseraAmount().length() > 1) {
                        try {
                            if(tds.get(i).getText().contains("-")) {
                                ratesCompare.getBankAmount().add("");
                            } else {
                                WebElement spanOtherBankAmount = tds.get(i).findElement(By.cssSelector("span[class='ng-binding']"));
                                ratesCompare.getBankAmount().add(spanOtherBankAmount.getText());
                            }
                        } catch (Exception e) {
                            ratesCompare.getBankAmount().add("");
                            System.err.println("Unable to extract Bank amount");
                        }
                        try {
                            if(tds.get(i).getText().contains("-")) {
                                ratesCompare.getNegativeDiff().add("");
                            } else {
                                WebElement spanOtherBankNeg = tds.get(i).findElement(By.cssSelector("span[class='other-bank-loss ng-binding ng-scope']"));
                                ratesCompare.getNegativeDiff().add(spanOtherBankNeg.getText());
                            }
                        } catch (Exception e) {
                            ratesCompare.getNegativeDiff().add("");
                            System.err.println("Unable to extract Bank negative difference");
                        }
                    }
                    break;
            }
        }
        return ratesCompare;
    }
}

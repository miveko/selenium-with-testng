package PageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class CurrencyConversionCalculatorElements {

    private static WebDriver driver;
    public CurrencyConversionCalculatorElements(WebDriver driver) {
        CurrencyConversionCalculatorElements.driver = driver;
    }
    public static WebElement search_button() {
        return driver.findElement(By.name("btnK"));
    }

    public static WebElement input_sellAmount() {
        return driver.findElement(By.cssSelector("input[data-ng-model='currencyExchangeVM.filter.from_amount']"));
    }

    public static WebElement input_buyAmount() {
        return driver.findElement(By.cssSelector("input[data-ng-model='currencyExchangeVM.filter.to_amount']"));
    }

    public static WebElement _span_carret() {
        return driver.findElement(By.cssSelector(".dropup"));
    }

    public static WebElement _dropdown_countries() {
        return driver.findElement(By.xpath("//button[@id='countries-dropdown']/span[2]"));
    }

    public static WebElement _link_country(String country) {
        return driver.findElement(By.linkText(country));
    }

    public static WebElement _span_currencySelected() {
        return driver.findElement(By.cssSelector("span[data-ng-bind='$select.selected'][class='ng-binding ng-scope']"));
    }
    
    public static List<WebElement> _currenciesInRatesToList() {
        return driver.findElements(By.cssSelector("td[data-ng-if='currencyExchangeVM.rates[currencyExchangeVM.PROVIDERS.OFFICIAL]']"));
    }

    public static List<WebElement> _tr_curenciesRates() {
        return driver.findElements(By.xpath("//table/tbody/tr"));
    }
}

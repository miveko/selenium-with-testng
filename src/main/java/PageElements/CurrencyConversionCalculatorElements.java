package PageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import java.io.Console;

public class CurrencyConversionCalculatorElements {

    private static WebDriver driver;
    public CurrencyConversionCalculatorElements(WebDriver driver) {
        this.driver = driver;
    }
    public static WebElement search_button() {
        return driver.findElement(By.name("btnK"));
    }

    public static WebElement input_sellAmount() {
        WebElement webElement =
                driver.findElement(By.cssSelector("input[data-ng-model='currencyExchangeVM.filter.from_amount']"));
        System.out.println("Text: " + webElement.getText());
        System.out.println("Text: " + webElement.getAttribute("innerHTML"));
        System.out.println("Text: " + webElement.getAttribute("value"));
        return webElement;
    }

    public static WebElement input_buyAmount() {
        return driver.findElement(By.cssSelector("input[data-ng-model='currencyExchangeVM.filter.to_amount']"));
//        return driver.findElement(By.cssSelector(".ng-pristine:nth-child(2)']"));
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
}

package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import tests.BaseTest;

public class Actions extends BasePage {
    public static JavascriptExecutor js = (JavascriptExecutor) driver;
    public static void scrollToElement(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static void scrollToElementAndClick(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }
}

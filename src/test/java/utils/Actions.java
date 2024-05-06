package utils;

import org.openqa.selenium.*;
import pages.BasePage;

public class Actions extends BasePage {
    public static JavascriptExecutor js = (JavascriptExecutor) driver;
    public static void scrollToElement(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static void scrollToElementAndClick(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }
    public static void clickOnElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    public static void waitForElementLoaded() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
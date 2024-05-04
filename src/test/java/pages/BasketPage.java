package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage extends BasePage{
    private WebElement deleteItemFromBasketButton;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    public void deleteItemFromBasket(){
        deleteItemFromBasketButton =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-tag=\"deleteButton\"]")));
        deleteItemFromBasketButton.click();
    }
}

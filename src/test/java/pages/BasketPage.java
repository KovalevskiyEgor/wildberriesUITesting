package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import static utils.Actions.waitForElementLoaded;

public class BasketPage extends BasePage{
    @FindBy(xpath = "(//button[@data-tag=\"deleteButton\"])[1]")
    private WebElement deleteItemFromBasketButton;
    @FindBy(xpath = "//div[@class=\"b-item__picture\"]")
    private WebElement item;
    public BasketPage(){
        PageFactory.initElements(driver,this);
    }

    @Step("deleting item from basket")
    public void deleteItemFromBasket(){
        waitForElementLoaded();
        deleteItemFromBasketButton.click();
    }
    @Step("goint to items page")
    public void goToItemsPage(){
        waitForElementLoaded();
        item.click();
    }
}
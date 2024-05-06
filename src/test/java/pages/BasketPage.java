package pages;

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

    public void deleteItemFromBasket(){
        waitForElementLoaded();
        deleteItemFromBasketButton.click();
    }
    public void goToItemsPage(){
        waitForElementLoaded();
        item.click();
    }
}
package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import utils.Actions;

import static utils.Actions.waitForElementLoaded;

public class ItemPage extends BasePage{
    @FindBy(xpath = "//button[@data-tag=\"favouritesButton\"]")
    private WebElement likeButton;
    @FindBy(xpath = "//button[@data-tag=\"basketButtonMain\"]")
    private WebElement basketButton;
    @FindBy(xpath = "//span[@data-tag=\"copyId\"]")
    private WebElement itemId;
    public ItemPage(){
        PageFactory.initElements(driver, this);
    }
    public void addItemToFavourites(){
        waitForElementLoaded();
        Actions.clickOnElement(likeButton);
        //likeButton.click();
        propertyReader.setValue("item.id",itemId.getText());
    }
    public void addToBasket(){
        basketButton.click();
    }

    public boolean isIdCorrect() {
        return itemId.getText().equals(propertyReader.getProperty("item.id"));
    }
}
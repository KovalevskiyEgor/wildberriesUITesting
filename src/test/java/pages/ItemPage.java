package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
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
    @Step("adding item to favourites")
    public void addItemToFavourites(){
        waitForElementLoaded();
        Actions.clickOnElement(likeButton);
        try {
            driver.findElement(By.xpath("//button[@class=\"product__favourites is-favourites\"]"));
        }catch (Exception e){
            Actions.clickOnElement(likeButton);
        }
        propertyReader.setValue("item.id",itemId.getText());
    }
    @Step("adding item to basket")
    public void addToBasket(){
        basketButton.click();
    }

    @Step("checking if item id is correct")
    public boolean isIdCorrect() {
        return itemId.getText().equals(propertyReader.getProperty("item.id"));
    }
}
package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BasePage{
    @FindBy(xpath = "//button[@data-tag=\"favouritesButton\"]")
    private WebElement likeButton;

    public ItemPage(){
        PageFactory.initElements(driver, this);
    }
    public void addItemToFavourites(){
        likeButton.click();
    }
}

package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
public class FavoritesPage extends BasePage{
    @FindBy(xpath = "//div[@class=\"favourites__container\"]//div[@class=\"card-img swiper-slide\"]")
    private WebElement item;
    public FavoritesPage(){
        PageFactory.initElements(driver, this);
    }
    public void goToItemPage(){
        item.click();
    }
}
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import static utils.Actions.waitForElementLoaded;

public class MainPage extends BasePage{
    @FindBy(xpath = "//button[@data-tag=\"catalogBtn\"]")
    private WebElement catalogButton;
    @FindBy(xpath = "//input[@class=\"search-component-input\"]")
    private WebElement searchInput;
    @FindBy(xpath = "//button[@data-tag=\"curBtn\"]")
    private WebElement currencyButton;
    @FindBy(xpath = "(//div[@data-tag=\"address\"])[1]")
    private WebElement addressButton;
    private WebElement mainCategoryButton;
    private WebElement categoryButton;
    private WebElement subCategoryButton;
    private WebElement currency;
    private static String CATEGORY_XPATH = "//span[text()=\"%s\"]/..";

    public MainPage(){
        PageFactory.initElements(driver, this);
    }
    public void clickOnCatalogButton(){
        catalogButton.click();
    }
    public void selectCategories(String mainCategory, String category, String subCategory){
        mainCategoryButton = driver.findElement(By.xpath(String.format(CATEGORY_XPATH,mainCategory)));
        mainCategoryButton.click();
        categoryButton = driver.findElement(By.xpath(String.format(CATEGORY_XPATH, category)));
        categoryButton.click();
        subCategoryButton = driver.findElement(By.xpath(String.format(CATEGORY_XPATH, subCategory)));
        subCategoryButton.click();
    }
    public void findProduct(String productName){
        searchInput.sendKeys(productName);
        searchInput.sendKeys(Keys.ENTER);
    }
    public void changeCurrency(String currencyName){
        currencyButton.click();
        currency = driver.findElement(By.xpath(String.format("//span[@class=\"currency-item__name\" and contains(text(),\"%s\")]/..",currencyName)));
        currency.click();
    }
    public void changeAddress(){
        waitForElementLoaded();
        addressButton.click();
    }
}
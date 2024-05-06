package pages;

import io.qameta.allure.Step;
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
    @Step("open catalog")
    public void clickOnCatalogButton(){
        catalogButton.click();
    }
    @Step("selecting categories")
    public void selectCategories(String mainCategory, String category, String subCategory){
        mainCategoryButton = driver.findElement(By.xpath(String.format(CATEGORY_XPATH,mainCategory)));
        mainCategoryButton.click();
        categoryButton = driver.findElement(By.xpath(String.format(CATEGORY_XPATH, category)));
        categoryButton.click();
        subCategoryButton = driver.findElement(By.xpath(String.format(CATEGORY_XPATH, subCategory)));
        subCategoryButton.click();
    }
    @Step("find product by name")
    public void findProduct(String productName){
        searchInput.sendKeys(productName);
        searchInput.sendKeys(Keys.ENTER);
    }
    @Step("changing currency")
    public void changeCurrency(String currencyName){
        currencyButton.click();
        currency = driver.findElement(By.xpath(String.format("//span[@class=\"currency-item__name\" and contains(text(),\"%s\")]/..",currencyName)));
        currency.click();
    }
    @Step("changing address")
    public void changeAddress(){
        waitForElementLoaded();
        addressButton.click();
    }
    @Step("checking if currency correctly selected")
    public boolean isCurrencyCorrectlySelected(String currencyName) {
        try {
            driver.findElement(By.xpath(String.format("//span[@class=\"currency-item__name\" and contains(text(),\"%s\")]/ancestor::li[@class=\"currency-item is-active\"]",currencyName)));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage{
    @FindBy(xpath = "//button[@data-tag=\"catalogBtn\"]")
    private WebElement catalogButton;
    @FindBy(xpath = "//input[@class=\"search-component-input\"]")
    private WebElement searchInput;
    private WebElement mainCategoryButton;
    private WebElement categoryButton;
    private WebElement subCategoryButton;

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
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.Actions.scrollToElement;

public class ItemsPage extends BasePage{
    @FindBy(xpath = "//div[@class=\"chip chip__sort\"]")
    private WebElement sorting;
    @FindBy(xpath = "//div[contains(@class,\"chip\") and @data-title=\"Бренд\"]")
    private WebElement brand;
    @FindBy(xpath = "//a[@href=\"/basket\"]")
    private WebElement basketButton;
    @FindBy(xpath = "//div[@class=\"product-card\"]")
    private WebElement itemCard;

    private WebElement sortingMethod;
    private WebElement header;
    private WebElement allBrandsButton;
    private WebElement searchInput;
    private List<WebElement> basketButtons;

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private JavascriptExecutor js = (JavascriptExecutor) driver;
    String currentUrl = driver.getCurrentUrl();
    public ItemsPage(){
        PageFactory.initElements(driver, this);
    }
    public void filter(String sortBy,String minPrice, String maxPrice){
        sort(sortBy);
        setBrand();
    }

    public void addItemsToBasket(){
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        basketButtons = driver.findElements(By.xpath("//button[@data-tag=\"basketBtn\"]"));
        scrollToElement(basketButtons.get(0));
        basketButtons.get(0).click();
        basketButtons.get(1).click();
        basketButtons.get(2).click();
    }
    public void goToBasket(){
        basketButton.click();
    }
    public void goToItem(){
        itemCard.click();
    }
    private void sort(String sortBy){
        sorting.click();
        sortingMethod = driver.findElement(By.xpath(String.format("//span[@class=\"filter__item-in\" and contains(text(),\"%s\")]",sortBy)));
        sortingMethod.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        currentUrl = driver.getCurrentUrl();
    }

    private void setBrand(){
        wait.until(ExpectedConditions.attributeContains(By.xpath("//div[@class=\"chip chip__sort\"]"),"data-status","closed"));
        brand.click();
        allBrandsButton = driver.findElement(By.xpath("//div[@class=\"chip\" and @data-title=\"Бренд\"]//button[@class=\"filter__fold\"]"));
        allBrandsButton.click();

        searchInput = driver.findElement(By.xpath("//div[@class=\"chip-filter-container\"]//input[@data-text=\"strSearch\"]"));
        ArrayList<String> brands = readXmlWithBrands();
        for(String brand:brands){
            searchInput.sendKeys(brand);
            WebElement brandToChoose = driver.findElement(By.xpath("//div[@class=\"chip-filter-box\"]//div[@class=\"filter__items-in\"]//div"));
            brandToChoose.click();
            searchInput.clear();
        }
        header = driver.findElement(By.xpath("//h1"));
        header.click();
    }

    private ArrayList<String> readXmlWithBrands() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse("src/test/resources/brands.xml");

            Element filtersElement = document.getDocumentElement();

            NodeList manufacturerNodes = filtersElement.getElementsByTagName("brand");

            ArrayList<String> brands = new ArrayList<>();

            for (int i = 0; i < manufacturerNodes.getLength(); i++) {
                Element manufacturerElement = (Element) manufacturerNodes.item(i);
                String brand = manufacturerElement.getTextContent();
                brands.add(brand);
            }
            return brands;
        } catch (Exception e) {
            return (new ArrayList<>(Collections.singleton("")));
        }
    }
}

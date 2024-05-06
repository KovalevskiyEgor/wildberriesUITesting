package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.time.Duration;
import java.util.*;
import static utils.Actions.*;

public class ItemsPage extends BasePage{
    @FindBy(xpath = "//div[@class=\"chip chip__sort\"]")
    private WebElement sorting;
    @FindBy(xpath = "//div[contains(@class,\"chip\") and @data-title=\"Бренд\"]")
    private WebElement brand;
    @FindBy(xpath = "//a[@href=\"/basket\"]")
    private WebElement basketButton;
    @FindBy(xpath = "//div[@class=\"product-card\"]")
    private WebElement itemCard;
    @FindBy(xpath = "//li[@class=\"user-menu__item user-menu__item--heart-thin\"]")
    private WebElement favouritesButton;
    private WebElement sortingMethod;
    private WebElement allBrandsButton;
    private WebElement searchInput;
    private List<WebElement> basketButtons;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public ItemsPage(){
        PageFactory.initElements(driver, this);
    }
    public void filter(String sortBy){
        sort(sortBy);
        waitForElementLoaded();
        setBrand();
    }
    public void goToFavourites(){
        favouritesButton.click();
    }

    public void addItemsToBasket(){
        waitForElementLoaded();
        basketButtons = driver.findElements(By.xpath("//button[@data-tag=\"basketBtn\"]"));
        scrollToElement(basketButtons.get(0));
        basketButtons.get(0).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class=\"quantity\"]"),1));
        basketButtons.get(1).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class=\"quantity\"]"),2));
    }
    public void goToBasket(){
        basketButton.click();
    }
    public void goToItem(){
        itemCard.click();
    }
    public boolean foundProductIsCorrect(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        return driver.findElement(By.xpath("//h1")).getText().equals(productName);
    }
    private void sort(String sortBy){
        sorting.click();
        sortingMethod = driver.findElement(By.xpath(String.format("//span[@class=\"filter__item-in\" and contains(text(),\"%s\")]",sortBy)));
        sortingMethod.click();
        //sorting = driver.findElement(By.xpath("//div[@class=\"chip chip__sort\"]"));
        //wait.until(ExpectedConditions.attributeToBe(sorting,"data-status","closed"));
    }

    private void setBrand(){
        wait.until(ExpectedConditions.visibilityOf(brand));
        wait.until(ExpectedConditions.elementToBeClickable(brand));
        brand = driver.findElement(By.xpath("//div[@data-title=\"Бренд\"]"));
        brand.click();

        allBrandsButton = driver.findElement(By.xpath("//div[@class=\"chip\" and @data-title=\"Бренд\"]//button[@class=\"filter__fold\"]"));
        allBrandsButton.click();

        searchInput = driver.findElement(By.xpath("//div[@class=\"chip-filter-container\"]//input[@data-text=\"strSearch\"]"));
        ArrayList<String> brands = readXmlWithBrands();
        for(String brand:brands){
            searchInput.sendKeys(brand);
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath("//div[@class=\"chip-filter-box\"]//div[@class=\"filter__items-in\"]//div"),3));
            wait.until(ExpectedConditions.attributeContains(searchInput,"value",brand));
            waitForElementLoaded();
            WebElement brandToChoose = driver.findElement(By.xpath("//div[@class=\"chip-filter-box\"]//div[@class=\"filter__items-in\"]//div"));
            brandToChoose.click();
            searchInput.clear();
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(searchInput,"value")));
        }
        driver.findElement(By.xpath("//button[@data-tag=\"fold\" and contains(text(),\"Свернуть\")]")).click();
        //wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[@data-title=\"Бренд\"]"),"data-status","closed"));
    }
    public boolean isSelectedCategoriesCorrect(String mainCategory, String category, String subCategory) {
        boolean isMainCategoryCorrect = mainCategory.equals(driver.findElement(By.xpath("(//span[@class=\"breadcrumb-item__link\"])[1]")).getText());
        boolean isCategoryCorrect = category.equals(driver.findElement(By.xpath("(//span[@class=\"breadcrumb-item__link\"])[2]")).getText());
        boolean isSubCategoryCorrect = subCategory.equals(driver.findElement(By.xpath("//h1")).getText());
        return isSubCategoryCorrect&&isCategoryCorrect&&isMainCategoryCorrect;
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

    public boolean isFilterCorrect(String sortBy) {
        waitForElementLoaded();
        sorting = driver.findElement(By.xpath("//div[@class=\"chip chip__sort\"]"));
        sorting.click();
        try{
            driver.findElement(By.xpath(String.format("//span[@class=\"filter__item-in\" and contains(text(),\"%s\")]/ancestor::div[@class=\"filter__item is-active\"]",sortBy)));
            brand = driver.findElement(By.xpath("//div[contains(@class,\"chip\") and @data-title=\"Бренд\"]"));
            brand.click();
            ArrayList<String> brands = readXmlWithBrands();
            for(String brand: brands){
                driver.findElement(By.xpath(String.format("//span[@class=\"filter__item-in\" and contains(text(),\"%s\")]/ancestor::div[@class=\"filter__item is-active\"]",brand)));
            }
        }catch (NoSuchElementException e){
            return false;
        }
        return true;
    }

    public boolean areAllItemsAddedToBasket() {
        System.out.println(driver.findElement(By.xpath("//span[@class=\"user-menu__badge\"]")).getText());
        return driver.findElement(By.xpath("//span[@class=\"user-menu__badge\"]")).getText().equals("2");
    }

    public boolean isItemRemovedFromBasket() {
        return driver.findElement(By.xpath("//span[@class=\"user-menu__badge\"]")).getText().equals("1");
    }
}
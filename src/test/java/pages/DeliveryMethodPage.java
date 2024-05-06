package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import static utils.Actions.waitForElementLoaded;

public class DeliveryMethodPage extends BasePage{
    private WebElement addAddressButton;
    @FindBy(xpath = "//button[@data-tag=\"courierMode\"]")
    private WebElement deliveryByCourierButton;
    private WebElement pickUpPointAddressSearchField;
    private WebElement pickUpPoint;
    private WebElement confirmPickUpPoint;
    private WebElement confirmDeliveryByCourierAddress;
    private WebElement pickUpPointStreet;
    private WebElement deliveryByCourierAddressSearchField;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private WebElement houseField;
    private WebElement flatField;
    private WebElement entranceField;
    private WebElement floorField;
    public DeliveryMethodPage(){
        PageFactory.initElements(driver, this);
    }

    @Step("adding pick up point")
    public void addPickUpPoint(String pickUpPointAddress){
        pickUpPointAddressSearchField = driver.findElement(By.xpath("//input[@data-tag=\"address\"]"));
        for(char c:pickUpPointAddress.toCharArray()){
            pickUpPointAddressSearchField.sendKeys(String.valueOf(c));
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//span[@class=\"input-autocomplete-item__address\" and contains(text(),\"%s\")]",pickUpPointAddress))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[@class=\"input-autocomplete-item__address\" and contains(text(),\"%s\")]",pickUpPointAddress))));
        waitForElementLoaded();
        pickUpPointStreet = driver.findElement((By.xpath("(//li[@class=\"input-autocomplete-item\"])[1]")));
        pickUpPointStreet.click();
        pickUpPoint = driver.findElement(By.xpath("(//div[@class=\"pvz-item-delivery__content\"])[1]"));
        pickUpPoint.click();

        confirmPickUpPoint = driver.findElement(By.xpath("//button[@class=\"btn delivery-footer__btn--confirm \"]"));
        confirmPickUpPoint.click();
    }
    @Step("adding courier delivery address")
    public void addCourierDeliveryAddress(String deliveryByCourierAddress, String house, String flat, String entrance, String floor){
        deliveryByCourierButton.click();
        addAddressButton = driver.findElement(By.xpath("//button[@data-tag=\"pick\"]"));
        addAddressButton.click();
        deliveryByCourierAddressSearchField = driver.findElement(By.xpath("//textarea[@data-tag=\"address\"]"));
        for(char c:deliveryByCourierAddress.toCharArray()){
            deliveryByCourierAddressSearchField.sendKeys(String.valueOf(c));
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//span[@class=\"input-autocomplete-item__address\" and contains(text(),\"%s\")]",deliveryByCourierAddress))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[@class=\"input-autocomplete-item__address\" and contains(text(),\"%s\")]",deliveryByCourierAddress))));
        waitForElementLoaded();
        driver.findElement(By.xpath(String.format("//span[@class=\"input-autocomplete-item__address\" and contains(text(),\"%s\")]",deliveryByCourierAddress))).click();

        houseField = driver.findElement(By.xpath("//input[@data-tag=\"house\"]"));
        flatField = driver.findElement(By.xpath("//input[@data-tag=\"flat\"]"));
        entranceField = driver.findElement(By.xpath("//input[@data-tag=\"entrance\"]"));
        floorField = driver.findElement(By.xpath("//input[@data-tag=\"floor\"]"));
        houseField.sendKeys(house);
        flatField.sendKeys(flat);
        entranceField.sendKeys(entrance);
        floorField.sendKeys(floor);

        confirmDeliveryByCourierAddress = driver.findElement(By.xpath("//button[@class=\"btn delivery-footer__btn--confirm\"]"));
        confirmDeliveryByCourierAddress.click();
    }

    @Step("checking if courier delivery addresses correct")
    public boolean areCourierDeliveryAddressesCorrect(String oneMoreDeliveryByCourierAddress, String deliveryByCourierAddress, String house, String flat, String floor, String entrance) {
        String address = "%s, дом %s, квартира %s, подъезд %s, этаж %s";
        String firstAddress = String.format(address, oneMoreDeliveryByCourierAddress, house, flat, floor, entrance);
        String secondAddress = String.format(address, deliveryByCourierAddress, house, flat, floor, entrance);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\"user-addresses__list\"]//div[@data-tag=\"address\"])[1]")));
        boolean isFirstAddressCorrect = driver.findElement(By.xpath("(//div[@class=\"user-addresses__list\"]//div[@data-tag=\"address\"])[1]")).getText().equals(firstAddress);
        boolean isSecondAddressCorrect = driver.findElement(By.xpath("(//div[@class=\"user-addresses__list\"]//div[@data-tag=\"address\"])[2]")).getText().equals(secondAddress);
        return isFirstAddressCorrect&&isSecondAddressCorrect;
    }
}
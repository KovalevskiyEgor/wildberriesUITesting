package tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;

public class ChangeAddressTest extends BaseTest{
    private SoftAssert softAssert = new SoftAssert();
    @Test
    @Owner("Ковалевский Егор")
    @Severity(SeverityLevel.NORMAL)
    @Description("changing pick up and courier delivery address")
    @Parameters({"currencyName","pickUpPointAddress","deliveryByCourierAddress","house","flat","floor","entrance","oneMoreDeliveryByCourierAddress"})
    public void changeAddressTest(String currencyName,String pickUpPointAddress,String deliveryByCourierAddress,
                     String house,String flat, String floor, String entrance,
                     String oneMoreDeliveryByCourierAddress){
        MainPage mainPage = new MainPage();
        mainPage.changeCurrency(currencyName);
        softAssert.assertTrue(mainPage.isCurrencyCorrectlySelected(currencyName),"currency is not correct");
        mainPage.changeAddress();

        DeliveryMethodPage deliveryMethodPage = new DeliveryMethodPage();
        deliveryMethodPage.addPickUpPoint(pickUpPointAddress);
        mainPage.changeAddress();
        deliveryMethodPage.addCourierDeliveryAddress(deliveryByCourierAddress, house, flat, floor, entrance);
        mainPage.changeAddress();
        deliveryMethodPage.addCourierDeliveryAddress(oneMoreDeliveryByCourierAddress, house, flat, floor, entrance);

        mainPage.changeAddress();
        softAssert.assertTrue(deliveryMethodPage.areCourierDeliveryAddressesCorrect(oneMoreDeliveryByCourierAddress, deliveryByCourierAddress, house, flat, floor, entrance));

        softAssert.assertAll();
    }
}
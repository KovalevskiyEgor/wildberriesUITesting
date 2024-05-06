package tests;

import io.qameta.allure.*;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;

public class SecondTest extends BaseTest{
    private SoftAssert softAssert = new SoftAssert();
    @Test
    @Owner("Ковалевский Егор")
    @Severity(SeverityLevel.NORMAL)
    @Description("")
    @Parameters({"productName"})
    public void second(String productName){
        MainPage mainPage = new MainPage();
        mainPage.findProduct(productName);

        ItemsPage itemsPage = new ItemsPage();
        softAssert.assertTrue(itemsPage.foundProductIsCorrect(productName),"found product is not correct");
        itemsPage.goToItem();

        ItemPage itemPage = new ItemPage();
        itemPage.addItemToFavourites();
        itemPage.addToBasket();
        itemsPage.goToFavourites();

        FavoritesPage favoritesPage = new FavoritesPage();
        favoritesPage.goToItemPage();
        ItemPage itemPage2 = new ItemPage();
        softAssert.assertTrue(itemPage2.isIdCorrect(),"item is not added to favorites");
        itemsPage.goToBasket();
        BasketPage basketPage = new BasketPage();
        basketPage.goToItemsPage();
        ItemPage itemPage3 = new ItemPage();
        softAssert.assertTrue(itemPage3.isIdCorrect(),"item not added to basket");

        softAssert.assertAll();
    }
}
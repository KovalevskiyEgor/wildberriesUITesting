package tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;

public class WildberriesTest extends BaseTest{
    private SoftAssert softAssert = new SoftAssert();
    @Test
    @Owner("Ковалевский Егор")
    @Severity(SeverityLevel.NORMAL)
    @Description("")
    @Parameters({"mainCategory","category","subCategory","sortBy","minPrice","maxPrice"})
    public void first(String mainCategory, String category, String subCategory,String sortBy,String minPrice,String maxPrice){
        MainPage mainPage = new MainPage();
        mainPage.clickOnCatalogButton();
        mainPage.selectCategories(mainCategory, category, subCategory);

        ItemsPage itemsPage = new ItemsPage();
        softAssert.assertTrue(itemsPage.isSelectedCategoriesCorrect(mainCategory, category, subCategory),"chosen categories are not correct");
        itemsPage.filter(sortBy);
        softAssert.assertTrue(itemsPage.isFilterCorrect(sortBy),"filter is not correct");
        itemsPage.addItemsToBasket();

        itemsPage.goToBasket();
        softAssert.assertTrue(itemsPage.areAllItemsAddedToBasket(),"not all items are added to basket");

        BasketPage basketPage = new BasketPage();
        basketPage.deleteItemFromBasket();
        softAssert.assertTrue(itemsPage.isItemRemovedFromBasket(),"item wasn't removed");

        softAssert.assertAll();
        System.out.println();
    }
}
package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasketPage;
import pages.ItemPage;
import pages.ItemsPage;
import pages.MainPage;

public class WildberriesTest extends BaseTest{
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
        itemsPage.filter(sortBy, minPrice, maxPrice);
        itemsPage.addItemsToBasket();
        itemsPage.goToBasket();

        BasketPage basketPage = new BasketPage();
        basketPage.deleteItemFromBasket();
        System.out.println();
    }

    @Test
    @Owner("Ковалевский Егор")
    @Severity(SeverityLevel.NORMAL)
    @Description("")
    @Parameters({"productName"})
    public void second(String productName){
        MainPage mainPage = new MainPage();
        mainPage.findProduct(productName);

        ItemsPage itemsPage = new ItemsPage();
        itemsPage.goToItem();

        ItemPage itemPage = new ItemPage();
        itemPage.addItemToFavourites();
        System.out.println();
    }
}

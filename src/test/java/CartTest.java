import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest {

    public WebDriver login() {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("user-name")
        )).sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("inventory_list")
        ));

        return driver;
    }

    @Test
    public void addProductToCartTest() {

        WebDriver driver = login();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-sauce-labs-backpack")
            )).click();

            WebElement removeButton = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("remove-sauce-labs-backpack")
                    )
            );

            Assert.assertEquals(removeButton.getText(), "Remove");

            System.out.println("Add Product To Cart Test Passed!");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void verifyCartBadgeTest() {

        WebDriver driver = login();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-sauce-labs-backpack")
            )).click();

            WebElement badge = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("shopping_cart_badge")
                    )
            );

            Assert.assertEquals(badge.getText(), "1");

            System.out.println("Cart Badge Test Passed!");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void openShoppingCartTest() {

        WebDriver driver = login();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.className("shopping_cart_link")
            )).click();

            WebElement heading = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("title")
                    )
            );

            Assert.assertEquals(heading.getText(), "Your Cart");

            System.out.println("Open Shopping Cart Test Passed!");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void verifyProductInCartTest() {

        WebDriver driver = login();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-sauce-labs-backpack")
            )).click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.className("shopping_cart_link")
            )).click();

            WebElement productName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("inventory_item_name")
                    )
            );

            Assert.assertEquals(
                    productName.getText(),
                    "Sauce Labs Backpack"
            );

            System.out.println("Verify Product In Cart Test Passed!");

        } finally {
            driver.quit();
        }
    }

    @Test
    public void removeProductFromCartTest() {

        WebDriver driver = login();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20)
        );

        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-sauce-labs-backpack")
            )).click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("remove-sauce-labs-backpack")
            )).click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.className("shopping_cart_badge")
            ));

            int badgeCount = driver.findElements(
                    By.className("shopping_cart_badge")
            ).size();

            Assert.assertEquals(badgeCount, 0);

            System.out.println("Remove Product From Cart Test Passed!");

        } finally {
            driver.quit();
        }
    }
}
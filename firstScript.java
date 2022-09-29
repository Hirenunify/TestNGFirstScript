import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class firstScript {
    WebDriver driver;
     @BeforeClass
    void url() {
        System.setProperty("webdriver.chrome.driver", "C:\\Software\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    void selectItem() {
        WebElement Computers = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a"));
        Actions action = new Actions(driver);
        action.moveToElement(Computers).perform();
        WebElement Desktops = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/ul/li[1]/a"));
        Actions action1 = new Actions(driver);
        action1.moveToElement(Desktops).click().perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[3]/div/div[2]/h2/a")).click();
    }

    @Test(priority = 2)
    void addToCart() {
        driver.findElement(By.id("product_enteredQuantity_3")).clear();
        driver.findElement(By.id("product_enteredQuantity_3")).sendKeys("1");
        driver.findElement(By.id("add-to-cart-button-3")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("dialog-notifications-success"));
        String expectedMessage = "The product has been added to your shopping cart";
       String confirmation= driver.findElement(By.xpath("//*[@id=\"bar-notification\"]/div")).getText();
        if(expectedMessage.equalsIgnoreCase(confirmation))
            System.out.println("Lenovo IdeaCentre 600 All-in-One PC - successfully added to cart ");
        else
            System.out.println("Lenovo IdeaCentre 600 All-in-One PC - unable to add to cart ");
        driver.findElement(By.className("close")).click();
    }
    @Test(priority = 3)
    void cartUpdate(){
        WebElement ShoppingCart = driver.findElement(By.className("cart-label"));
        Actions action2 = new Actions(driver);
        action2.moveToElement(ShoppingCart).perform();
        driver.findElement(By.id("flyout-cart")).click();
        WebElement button =driver.findElement(By.xpath("//button[@class='button-1 cart-button']"));
        Actions action3 = new Actions(driver);
        action3.moveToElement(button).click().perform();
        driver.findElement(By.className("qty-input")).clear();
       driver.findElement(By.className("qty-input")).sendKeys("3");
        driver.findElement(By.id("updatecart")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)");
        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.id("checkout")).click();
        }
        @Test(priority = 4)
        void checkout(){

        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[1]/div[3]/button[1]")).click();
        driver.findElement(By.id("ShipToSameAddress")).click();
        driver.findElement(By.id("ShipToSameAddress")).click();
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("Hiren");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Bhatt");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("info@iblogistics.co.uk");
        driver.findElement(By.id("BillingNewAddress_Company")).sendKeys("IB LOGISTICS LTD");
        WebElement Country = driver.findElement(By.id("BillingNewAddress_CountryId"));
            Select select = new Select(Country);
            select.selectByValue("233");
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Essex");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("6 Maidstone Road");
        driver.findElement(By.id("BillingNewAddress_Address2")).sendKeys("Grays");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("RM17 6NF");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("07356963274");
        driver.findElement(By.id("BillingNewAddress_FaxNumber")).sendKeys("02034645773");
        driver.findElement(By.cssSelector("button[class^=\"button-1 ne\"]")).click();
        driver.findElement(By.id("shippingoption_1")).click();
        driver.findElement(By.cssSelector("button[onclick^=\"ShippingM\"]")).click();
        }

        @Test(priority = 5)
        void paymentProcess(){
        driver.findElement(By.id("paymentmethod_1")).click();
        driver.findElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']")).click();
        WebElement CreditCard = driver.findElement(By.id("CreditCardType"));
        Select select = new Select(CreditCard);
        select.selectByValue("MasterCard");
        driver.findElement(By.id("CardholderName")).sendKeys("Mr.Bhatt");
        driver.findElement(By.id("CardNumber")).sendKeys("2143 1245 5324 3255");
            WebElement Month = driver.findElement(By.id("ExpireMonth"));
            WebElement Year = driver.findElement(By.id("ExpireYear"));
            Select select1 = new Select(Month);
            Select select2 = new Select(Year);
            select1.selectByValue("9");
            select2.selectByValue("2026");
            driver.findElement(By.cssSelector("input[style$=\"60px;\"]")).sendKeys("999");
            driver.findElement(By.cssSelector("button[onclick=\"PaymentInfo.save()\"]")).click();
            driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/ol/li[5]/div[2]/form/div/div/div/div[2]"));

        }


 @AfterClass
    void close() {
        driver.close();
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest {
  WebDriver driver = null;

  @BeforeMethod
  public void setUp() {
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
  }

  @Test
  public void successfulLoginTest() {
    driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
    driver.findElement(By.id("login-form-username")).sendKeys("IrinaChub");
    driver.findElement(By.id("login-form-password")).sendKeys("IrinaChub");
    driver.findElement(By.id("login")).click();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    assertTrue(driver.findElement(By.id("header-details-user-fullname")).isDisplayed());

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    driver.findElement(By.id("quickSearchInput")).sendKeys("WEBINAR-9060");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    driver.findElement(By.xpath("//*[@id='quicksearch-menu']//child::span[contains(text(),'WEBINAR-9060')]")).click();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertTrue(driver.findElement(By.xpath("//*[contains(text(),'Type:')]//following::*[@id='type-val']")).isDisplayed());

    try {
      Thread.sleep(3000);//5
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertTrue(driver.findElement(By.xpath("//*[@id='stalker']//child::a[@data-issue-key='WEBINAR-9060']")).isDisplayed());
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}
package com.ch.tests;
import org.junit.jupiter.api.*; import org.openqa.selenium.*; import org.openqa.selenium.chrome.*; import io.github.bonigarcia.wdm.WebDriverManager;
public class SmokeTests {
  WebDriver driver; String base="https://automationintesting.online";
  @BeforeEach void setup(){ WebDriverManager.chromedriver().setup(); ChromeOptions o=new ChromeOptions(); o.addArguments("--headless=new","--window-size=1366,768"); driver=new ChromeDriver(o); }
  @AfterEach void teardown(){ if(driver!=null) driver.quit(); }
  @Test void homeToRooms(){ driver.get(base+"/"); driver.findElement(By.cssSelector("a[href*='#/rooms']")).click(); Assertions.assertTrue(driver.findElements(By.cssSelector("[data-testid='room-card'], .room")).size()>0); }
}
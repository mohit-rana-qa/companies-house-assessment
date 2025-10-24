package com.ch.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class SmokeTests {
  private WebDriver driver;
  private WebDriverWait wait;
  private final String base = System.getProperty("baseUrl", "https://automationintesting.online");

  @BeforeEach
  void setup() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions o = new ChromeOptions();
    // Headless in CI + extra flags for GitHub Actions Linux runners
    o.addArguments("--headless=new", "--window-size=1366,768", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
    driver = new ChromeDriver(o);
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
  }

  @AfterEach
  void teardown() {
    if (driver != null) driver.quit();
  }

  @Test
  @DisplayName("Home → Rooms shows cards")
  void homeToRooms() {
    driver.get(base + "/");

    // Make sure page is ready
    wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

    // Robust locator for the Rooms link (href or visible text)
    By roomsLink = By.xpath("//a[contains(@href,'rooms') or normalize-space(.)='Rooms' or contains(.,'Rooms')]");

    // Some UIs render late; wait + click
    WebElement link = wait.until(ExpectedConditions.elementToBeClickable(roomsLink));
    link.click();

    // Assert at least one room card is present
    By roomCard = By.cssSelector("[data-testid='room-card'], .room");
    wait.until(ExpectedConditions.presenceOfElementLocated(roomCard));
    Assertions.assertTrue(driver.findElements(roomCard).size() > 0, "Expected room cards to be visible");
  }
}

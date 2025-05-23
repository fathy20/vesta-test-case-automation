package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.time.Duration;

public class VezeetaAutomationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Store all locators as constants
    private final By SEARCH_BAR = By.xpath("//input[@placeholder='Search']");
    private final By LOGIN_BUTTON = By.linkText("Login");
    private final By EMAIL_FIELD = By.id("email");
    private final By PASSWORD_FIELD = By.id("password");
    private final By SEARCH_RESULTS = By.className("search-results");
    private final By DOCTOR_CARDS = By.className("doctor-card");
    private final By SPECIALTY_DROPDOWN = By.id("specialty-dropdown");
    private final By LOCATION_DROPDOWN = By.id("location-dropdown");
    private final By BOOK_APPOINTMENT_BUTTON = By.xpath("//button[contains(text(),'Book Appointment')]");

    @BeforeTest
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testHomePage() {
        try {
            // Navigate to Vezeeta website
            driver.get("https://www.vezeeta.com/en");

            // Verify that the search bar is visible
            WebElement searchBar = driver.findElement(SEARCH_BAR);
            Assert.assertTrue(searchBar.isDisplayed(), "Search bar is not visible");

            System.out.println("Homepage test passed successfully");
        } catch (Exception e) {
            System.out.println("Homepage test failed: " + e.getMessage());
            Assert.fail("Homepage test failed");
        }
    }

    @Test(priority = 2)
    public void testSearch() {
        try {
            // Find search bar and enter search term
            WebElement searchBar = driver.findElement(SEARCH_BAR);
            searchBar.clear();
            searchBar.sendKeys("Dentist");

            // Wait for search results
            wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_RESULTS));

            // Verify search results are displayed
            Assert.assertTrue(driver.findElement(SEARCH_RESULTS).isDisplayed(),
                    "Search results are not displayed");

            System.out.println("Search test passed successfully");
        } catch (Exception e) {
            System.out.println("Search test failed: " + e.getMessage());
            Assert.fail("Search test failed");
        }
    }

    @Test(priority = 3)
    public void testLogin() {
        try {
            // Click login button
            driver.findElement(LOGIN_BUTTON).click();

            // Enter login credentials
            driver.findElement(EMAIL_FIELD).sendKeys("test@example.com");
            driver.findElement(PASSWORD_FIELD).sendKeys("testPassword123");
            driver.findElement(LOGIN_BUTTON).click();

            // Wait for login to complete (you might need to adjust this based on actual page behavior)
            Thread.sleep(2000);

            System.out.println("Login test passed successfully");
        } catch (Exception e) {
            System.out.println("Login test failed: " + e.getMessage());
            Assert.fail("Login test failed");
        }
    }

    @Test(priority = 4)
    public void testFilterDoctors() {
        try {
            // Select specialty
            WebElement specialtyDropdown = driver.findElement(SPECIALTY_DROPDOWN);
            specialtyDropdown.click();
            driver.findElement(By.xpath("//option[contains(text(),'Dermatology')]")).click();

            // Select location
            WebElement locationDropdown = driver.findElement(LOCATION_DROPDOWN);
            locationDropdown.click();
            driver.findElement(By.xpath("//option[contains(text(),'Cairo')]")).click();

            // Wait for filtered results
            wait.until(ExpectedConditions.visibilityOfElementLocated(DOCTOR_CARDS));

            System.out.println("Filter doctors test passed successfully");
        } catch (Exception e) {
            System.out.println("Filter doctors test failed: " + e.getMessage());
            Assert.fail("Filter doctors test failed");
        }
    }

    @Test(priority = 5)
    public void testBookAppointment() {
        try {
            // Find and click on first doctor card
            WebElement firstDoctor = driver.findElement(DOCTOR_CARDS);
            firstDoctor.click();

            // Wait for booking button and click it
            wait.until(ExpectedConditions.elementToBeClickable(BOOK_APPOINTMENT_BUTTON));
            driver.findElement(BOOK_APPOINTMENT_BUTTON).click();

            // Verify booking form appears (adjust locator as needed)
            Assert.assertTrue(driver.findElement(By.id("booking-form")).isDisplayed(),
                    "Booking form is not displayed");

            System.out.println("Book appointment test passed successfully");
        } catch (Exception e) {
            System.out.println("Book appointment test failed: " + e.getMessage());
            Assert.fail("Book appointment test failed");
        }
    }

    // Utility method to take screenshot (you can call this when a test fails)
    private void takeScreenshot(String testName) {
        // Add screenshot implementation here if needed
    }

    // Utility method to wait for element
    private void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Utility method to check if element exists
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @AfterMethod
    public void handleTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
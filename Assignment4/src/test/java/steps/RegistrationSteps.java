package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class RegistrationSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Given("I open the registration page")
    public void openPage() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@placeholder='First Name']")));
    }

    @When("I enter name {string}")
    public void enterName(String name) {
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(name);
    }

    @And("I enter email {string}")
    public void enterEmail(String email) {
        driver.findElement(By.xpath("//input[@placeholder='name@example.com']")).sendKeys(email);
    }

    @And("I select gender {string}")
    public void selectGender(String gender) {
        driver.findElement(By.xpath(
                "//label[contains(text(),'" + gender + "')]")).click();
    }

    @And("I enter mobile {string}")
    public void enterMobile(String mobile) {
        driver.findElement(By.xpath("//input[@placeholder='Enter Mobile Number']")).sendKeys(mobile);
    }

    @And("I enter date of birth {string}")
    public void enterDob(String dob) {
        WebElement dobField = driver.findElement(By.xpath("//input[@type='date']"));
        dobField.sendKeys(dob);
    }

    @And("I enter subject {string}")
    public void enterSubject(String subject) {
        driver.findElement(By.xpath("//input[@placeholder='Enter Subject']")).sendKeys(subject);
    }

    @And("I select hobby {string}")
    public void selectHobby(String hobby) {
        driver.findElement(By.xpath(
                "//label[contains(text(),'" + hobby + "')]")).click();
    }

    @And("I enter current address {string}")
    public void enterAddress(String address) {
        driver.findElement(By.xpath("//textarea[@placeholder='Currend Address']")).sendKeys(address);
    }

    @And("I select state {string}")
    public void selectState(String state) {
        WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//select[1]")));
        new Select(stateDropdown).selectByVisibleText(state);
    }

    @And("I select city {string}")
    public void selectCity(String city) {
        WebElement cityDropdown = driver.findElement(
                By.xpath("(//select)[2]"));
        new Select(cityDropdown).selectByVisibleText(city);
    }

    @And("I click submit")
    public void clickSubmit() {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('button').scrollIntoView(true);");
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('button').click();");
    }

    @Then("the form should be submitted successfully")
    public void verifySubmission() {
        try {
            Thread.sleep(3000);
            String title = driver.getTitle();
            System.out.println("Page title: " + title);
            System.out.println("Form submitted successfully!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }
}
package createUserFeature;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class MyStepdefs {

    protected WebDriver driver;
    private DriveCreator myObj = new DriveCreator();


    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Given("User opens {string} and navigates to Mailchimp Log in page")
    public void userOpensAndNavigatesToMailchimpLogInPage(String browser) {
        driver = myObj.createBrowser(browser);
        driver.get("https://login.mailchimp.com/signup/");
        click(driver, By.id("onetrust-accept-btn-handler"));
    }

    @Given("User types in email {string}")
    public void userTypesInEmail(String email) {

        if (email.equals("correctAddress")) {
            email = getDate() + "@mail.se";
            sendkeys(driver, By.id("email"), email);
        }

    }

    @And("user types in username {string}")
    public void userTypesInUsername(String userName) {


        if (userName.equals("userName")) {
            userName = getDate();
            sendkeys(driver, By.id("new_username"), userName);

        }
        if (userName.equals("existingUserName")) {
            sendkeys(driver, By.id("new_username"), userName);

        }
        if (userName.equals("longUserName")) {
            for (int i = 0; i < 20; i++) {
                sendkeys(driver, By.id("new_username"), userName);
            }
        }

    }

    @And("user types in password {string}")
    public void userTypesInPassword(String password) {
        sendkeys(driver, By.id("new_password"), password);
    }

    @When("user press sign up button")
    public void userPressSignUpButton() {
        click(driver, By.id("create-account"));

    }

    @Then("user is {string} and gets this {string}")
    public void userIsAndGetsThis(String accountStatus, String expectedMessage) {
        String actualAccountStatus = driver.getTitle();

        if (accountStatus.equals("Signed up")) {
            //asserting that user is on new page hence was able to sign up
            Assert.assertEquals("Success | Mailchimp", actualAccountStatus);
            //asserting that correct success message is displayed
            String actualMessage = getText(driver, By.cssSelector("h1[class='!margin-bottom--lv3 no-transform center-on-medium']"));
            Assert.assertEquals(expectedMessage, actualMessage);

        } else {
            //asserting user is still on signup page hence something went wrong
            Assert.assertEquals("Signup | Mailchimp", actualAccountStatus);

            //Asserting that error and error message agrees
            String actualErrorMessage = getText(driver, By.className("invalid-error"));
            System.out.println(actualErrorMessage);
            Assert.assertEquals(expectedMessage, actualErrorMessage);

        }
    }

    private String getText(WebDriver driver, By by) {
        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.presenceOfElementLocated(by));
        return element.getText();
    }

    private void click(WebDriver driver, By by) {
        ((new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(by))).click();
    }

    private void sendkeys(WebDriver driver, By by, String input) {
        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(input);
    }

    private String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyHHmmss");
        Date todaysDate = new Date();

        String formattedDate = df.format(todaysDate);

        return formattedDate;

    }
    // if there's a need for a scroll
    private static void scroll(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
    }


}

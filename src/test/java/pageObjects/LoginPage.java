package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//input[@id='input-username']")
    @CacheLookup
    WebElement textUsername;

    @FindBy(xpath = "//input[@id='input-password']")
    @CacheLookup
    WebElement textPassword;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    @CacheLookup
    WebElement buttonLogin;

    @FindBy(xpath = "//div[@class='modal-content']")
    @CacheLookup
    WebElement windowModal;

    @FindBy(xpath = "//button[@class='btn-close']")
    @CacheLookup
    WebElement buttonClose;

    @FindBy(xpath = "//span[@class='d-none d-md-inline']")
    @CacheLookup
    WebElement buttonLogout;

    public void setUsername(String username) {
        textUsername.clear();
        textUsername.sendKeys(username);
    }

    public void setPassword(String password) {
        textPassword.clear();
        textPassword.sendKeys(password);
    }

    public void clickButtonLogin() {
        buttonLogin.click();
    }

    public void clickWindowModal() {
        wait.until(ExpectedConditions.visibilityOf(windowModal));
        if(windowModal.isDisplayed()) {
            buttonClose.click();
            // System.out.println("Modal window closed.");
        }
    }

    public void clickButtonLogout() throws InterruptedException {
        Thread.sleep(3000);
        buttonLogout.click();
    }
}

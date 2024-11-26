package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

    public WebDriver driver;
    public JavascriptExecutor js;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    @FindBy(xpath = "//li[@id='menu-customer']")
    WebElement customerMenu;

    @FindBy(xpath = "//li[@id='menu-customer']//ul[@id='collapse-5']//a[contains(text(),'Customers')]")
    WebElement customerMenuItem;

    @FindBy(xpath = "//i[@class='fa-solid fa-plus']")
    WebElement buttonAddNew;

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement textFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement textLastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement textEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement textTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement textPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement textConfirmPassword;

    @FindBy(xpath = "//input[@id='input-newsletter']")
    WebElement toggleNewsletter;

    @FindBy(xpath = "//input[@id='input-safe']")
    WebElement toggleSafe;

    @FindBy(xpath = "//i[@class='fa-solid fa-floppy-disk']")
    WebElement buttonSave;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement alertMessage;

    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    public void clickCustomerMenu() {
        customerMenu.click();
    }

    public void clickCustomerMenuItem() {
        customerMenuItem.click();
    }

    public void clickButtonAddNew() {
        // js.executeScript("arguments[0].click()", buttonAddNew);
        buttonAddNew.click();
    }

    public void setFirstName(String fName) {
        textFirstName.clear();
        textFirstName.sendKeys(fName);
    }

    public void setLastName(String lName) {
        textLastName.clear();
        textLastName.sendKeys(lName);
    }

    public void setEmail(String email) {
        textEmail.clear();
        textEmail.sendKeys(email);
    }

    public void setTelephone(String phone) {
        textTelephone.clear();
        textTelephone.sendKeys(phone);
    }

    public void setPassword(String password) {
        textPassword.clear();
        textPassword.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        textConfirmPassword.clear();
        textConfirmPassword.sendKeys(password);
    }

    public void clickToggleNewsletter() {
        toggleNewsletter.click();
    }

    public void clickToggleSafe() {
        toggleSafe.click();
    }

    public void clickButtonSave() {
        buttonSave.click();
    }

    public String getAlertMessage() {
        try {
            return alertMessage.getText();
        } catch(Exception e) {
            return e.getMessage();
        }
    }
}

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddCustomerAddressPage {

    public WebDriver driver;
    public JavascriptExecutor js;
    private boolean isAddAddressClicked = false;
    WebDriverWait wait;

    public AddCustomerAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//a[normalize-space()='Addresses']")
    WebElement tabAddress;

    @FindBy(xpath = "//button[@id='button-address']")
    WebElement buttonAddAddress;

    @FindBy(xpath = "//div[@id='tab-address']//label")
    List<WebElement> elements;


    public void clickTabAddress() {
        tabAddress.click();
    }

    public void clickButtonAddAddress() {
        buttonAddAddress.click();
        isAddAddressClicked = true;
    }

    public void setMandatoryFields(String fName, String lName, String add1, String ct) {
        if (!isAddAddressClicked) {
            System.out.println("Button 'Add Address' must be clicked first!");
            return;
        }

        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

        for (int i = 0; i < elements.size(); i++) {
            try {
                WebElement element = elements.get(i);
                String titleField = element.getText();
                String script = "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content')";
                String content = js.executeScript(script, element).toString();

                if (content.contains("*")) {
                    // System.out.println("Mandatory field: " + titleField);

                    WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='input-address-" + i + "-firstname']")));
                    firstName.sendKeys(fName);

                    WebElement lastName = driver.findElement(By.xpath("//input[@id='input-address-" + i + "-lastname']"));
                    lastName.sendKeys(lName);

                    WebElement address1 = driver.findElement(By.xpath("//input[@id='input-address-" + i + "-address-1']"));
                    address1.sendKeys(add1);

                    WebElement city = driver.findElement(By.xpath("//input[@id='input-address-" + i + "-city']"));
                    city.sendKeys(ct);

                    WebElement country = driver.findElement(By.xpath("//select[@id='input-address-" + i + "-country']"));
                    Select selectCountry = new Select(country);
                    selectCountry.selectByVisibleText("Malaysia");

                    WebElement zone = driver.findElement(By.xpath("//select[@id='input-address-" + i + "-zone']"));
                    Select selectZone = new Select(zone);
                    wait.until(ExpectedConditions.visibilityOfAllElements(selectZone.getOptions()));
                    selectZone.selectByVisibleText("Kuala Lumpur");

                    break;
                }

            } catch (Exception e) {
                System.out.println("Error locating or interacting with mandatory field at index: " + i);
                e.printStackTrace();
                break;
            }
        }
    }
}

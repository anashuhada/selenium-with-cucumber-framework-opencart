package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.time.Duration;
import java.util.List;

public class SearchCustomerPage {

    public WebDriver driver;
    WaitHelper waitHelper;

    public SearchCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='input-name']")
    WebElement textName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement textEmail;

    @FindBy(xpath = "//button[@id='button-filter']")
    WebElement buttonFilter;

    @FindBy(xpath = "//div[@class='table-responsive']")
    WebElement table;

    @FindBy(xpath = "//div[@class='table-responsive']//tbody//tr")
    List<WebElement> totalRow;

    @FindBy(xpath = "//div[@class='table-responsive']//tbody//tr//td")
    List<WebElement> totalColumn;

    public void setEmail(String email) {
        textEmail.clear();
        textEmail.sendKeys(email);
    }

    public void setName(String name) {
        textName.clear();
        textName.sendKeys(name);
    }

    public void clickButtonFilter() {
        // waitHelper.waitForElement(buttonFilter, Duration.ofSeconds(10));
        buttonFilter.click();
    }

    public int getNoOfRows() {
        return totalRow.size();
    }

    public int getNoOfColumns() {
        return totalColumn.size();
    }

    public boolean searchCustomerByEmail(String email) {
        boolean flag = false;

        for(int i = 1; i <= getNoOfRows(); i++) {
            try {
                String customerEmail = table.findElement(By.xpath("//div[@class='table-responsive']//tbody//tr[" + i + "]//td[3]")).getText();
//                System.out.println(customerEmail);
                if (customerEmail.equals(email)) {
//                    System.out.println(email);
                    flag = true; // return true;
                    break;
                }
            } catch(StaleElementReferenceException e) {
                i--;
            }
        }
        return flag;
    }

    public boolean searchCustomerByName(String name) {
        boolean flag = false;

        for (int i = 1; i <= getNoOfRows(); i++) {
            try {
                String fullText = table.findElement(By.xpath("//div[@class='table-responsive']//tbody//tr[" + i + "]//td[2]")).getText();
//                System.out.println("Full text before splitting and trimming: " + fullText); // this will include Enabled

                String[] textAfterSplitting = fullText.split("\n");
                String onlyName = textAfterSplitting[0].trim();
//                System.out.println("Customer name after splitting and trimming: " + onlyName);

                if (onlyName.equals(name)) {
                    flag = true; // found
                    break; // exit loop once found the name
                }

            } catch(StaleElementReferenceException e) {
                i--; // retry the same row
            }
        }
        return flag; // not found
    }
}

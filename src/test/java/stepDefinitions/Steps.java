package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AddCustomerAddressPage;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Steps extends BaseClass {

    @Before
    public void setup() throws IOException {
        logger = Logger.getLogger("OpenCart"); // logger setup
        PropertyConfigurator.configure("log4j.properties"); // load log4j properties file from classpath

        prop = new Properties(); // load configuration properties
        FileInputStream file = new FileInputStream("config.properties");
        prop.load(file);

        String br = prop.getProperty("browser"); // select browser
        if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", prop.getProperty("chromepath"));
            driver = new ChromeDriver();
        } else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        }
        logger.info("*** Launching browser ***");
    }

    @Given("user launches Chrome browser")
    public void user_launches_chrome_browser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + ".//Drivers/chromedriver.exe");

        lp = new LoginPage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
    }

    @When("user opens URL {string}")
    public void user_opens_url(String url) {
        logger.info("*** Opening URL ***");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("user enters Username as {string} and Password as {string}")
    public void user_enters_username_as_and_password_as(String username, String password) {
        logger.info("*** Providing login details ***");
        lp.setUsername(username);
        lp.setPassword(password);
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String title) {
        logger.info("*** Login passed ***");
        Assert.assertEquals(title, driver.getTitle());
    }

    @Then("user clicks on Login button")
    public void user_clicks_on_login_button() {
        logger.info("*** Login started ***");
        lp.clickButtonLogin();
        lp.clickWindowModal();
    }

    @When("user clicks on Logout button")
    public void user_clicks_on_logout_button() throws InterruptedException {
        logger.info("*** Click on Logout button ***");
        lp.clickButtonLogout();
    }

    @When("close browser")
//    @After
    public void close_browser() {
        logger.info("*** Browser closed ***");
        driver.quit();
    }

    // customers step definitions starts here
    @Then("user can view Dashboard")
    public void user_can_view_dashboard() {
        acp = new AddCustomerPage(driver);
        String pageTitle = acp.getPageTitle();
        Assert.assertEquals("Dashboard", pageTitle);
    }

    @When("user clicks on Customers menu")
    public void user_clicks_on_customers_menu() throws InterruptedException {
        acp.clickCustomerMenu();
        Thread.sleep(2000);
    }

    @When("clicks on Customers menu item")
    public void clicks_on_customers_menu_item() {
        acp.clickCustomerMenuItem();
    }

    @When("clicks on Add New button")
    public void clicks_on_add_new_button() {
        acp.clickButtonAddNew();
    }

    @Then("user can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        String pageTitle = acp.getPageTitle();
        Assert.assertEquals("Customers", pageTitle);
    }

    @When("user enters customer details")
    public void user_enters_customer_details() {
        logger.info("*** Adding new customer ***");
        logger.info("*** Providing customer details ***");
        String firstName = randomString();
        String lastName = randomString();
        String email = firstName + "." + lastName + "@example.com";
        String phone = randomNumeric();
        String password = randomAlphaNumeric();

        acp.setFirstName(firstName);
        acp.setLastName(lastName);
        acp.setEmail(email);
        acp.setTelephone(phone);
        acp.setPassword(password);
        acp.setConfirmPassword(password);

        acp.clickToggleNewsletter();
        acp.clickToggleSafe();

        acap = new AddCustomerAddressPage(driver);
        acap.clickTabAddress();
        acap.clickButtonAddAddress();
        String address1 = randomString();
        String city = randomString();
        acap.setMandatoryFields(firstName, lastName, address1, city);
    }

    @When("clicks on Save button")
    public void clicks_on_save_button() {
        logger.info("*** Saving customer data ***");
        acp.clickButtonSave();
    }

    @Then("user can view confirmation message {string}")
    public void user_can_view_confirmation_message(String message) {
        String msg = acp.getAlertMessage();
        if(msg.contains(message)) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    // step definitions for searching customer using email
    @When("enters customer email")
    public void enters_customer_email() {
        logger.info("*** Searching customer by email ***");
        scp = new SearchCustomerPage(driver);
        scp.setEmail("cavZx.cCVPT@example.com");
    }

    @When("clicks on filter button")
    public void clicks_on_filter_button() {
        scp.clickButtonFilter();
    }

    @Then("user should find the email in the search table")
    public void user_should_find_the_email_in_the_search_table() {
        boolean status = scp.searchCustomerByEmail("cavZx.cCVPT@example.com");
        Assert.assertEquals(true, status);

        String phone = randomNumeric();
        acp.setTelephone(phone);
        System.out.println(phone);

        acp.clickButtonSave();
    }

    // step definitions for searching customer using name
    @When("enters customer name")
    public void enters_customer_name() {
        logger.info("*** Searching customer by name ***");
        scp = new SearchCustomerPage(driver);
        scp.setName("BqvKL RcqqI");
    }

    @Then("user should find the name in the search table")
    public void user_should_find_the_name_in_the_search_table() {
        boolean status = scp.searchCustomerByName("BqvKL RcqqI");
        Assert.assertEquals(true, status);
    }

}

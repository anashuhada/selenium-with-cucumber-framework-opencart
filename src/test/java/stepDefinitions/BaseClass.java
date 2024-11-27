package stepDefinitions;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerAddressPage;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage acp;
    public SearchCustomerPage scp;
    public AddCustomerAddressPage acap;
    public static Logger logger;
    public Properties prop;

    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomNumeric() {
        String generatedNumeric = RandomStringUtils.randomNumeric(5);
        return generatedNumeric;
    }

    public String randomAlphaNumeric() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        String generatedNumeric = RandomStringUtils.randomNumeric(5);
        return generatedString + generatedNumeric;
    }

}


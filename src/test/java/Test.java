import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Test {

    public static void main(String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.opencart.com/en-gb?route=account/register");
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> listLabel = driver.findElements(By.xpath("//form[@id='form-register']//label"));
        for(WebElement label : listLabel ) {
            String text = label.getText();
            String script = "return window.getComputedStyle(arguments[0], '::Before').getPropertyValue('content')";
            String content = js.executeScript(script, label).toString();

            if(content.contains("*")) {
                System.out.println("Mandatory field: " + text);
            }
        }

    }

}

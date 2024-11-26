package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = ".//Features",
        glue = "stepDefinitions",
        dryRun = false,
        plugin = {"pretty", "html:test-output/cucumber-report.html"},
        tags = "@sanity or @regression"
    )

public class TestRun {
}

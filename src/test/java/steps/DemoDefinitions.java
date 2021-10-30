package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class DemoDefinitions extends resources.driver {

    @Given("I have opened the browser")
    public void iHaveOpenedTheBrowser() {
        driver = initlilizeDriver();
    }

    @When("I navigate too google")
    public void iNavigateTooGoogle() {
        driver.get("https://www.google.com");
    }

    @Then("the page is loaded successfully")
    public void thePageIsLoadedSuccessfully() {
        Assert.assertEquals("https://www.google.com/", driver.getCurrentUrl());
        driver.quit();
    }

}

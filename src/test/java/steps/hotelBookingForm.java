package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.homePage;
import resources.getPropertyValues;

import java.io.IOException;

public class hotelBookingForm extends resources.driver {

    @Given("I navigate to the hotel booking form page")
    public void iNavigateToTheHotelBookingFormPage() {
        driver = initlilizeDriver();
        driver.get("http://hotel-test.equalexperts.io/");
    }

    @When("enter all valid information")
    public void enterAllValidInformation() {
        homePage hp = new homePage(driver);
        hp.enterFirstName("first");
        hp.enterLastName("lastname");
        hp.enterPrice("12");
        hp.depositStatus(1);
        hp.selectDate();
        hp.selectDate();
        hp.saveHotelBooking();
    }
}

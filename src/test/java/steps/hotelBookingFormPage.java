package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.hotelBookingForm;
import resources.driver;

public class hotelBookingFormPage extends driver {

    @Given("I navigate to the hotel booking form page")
    public void iNavigateToTheHotelBookingFormPage() {
        driver = initlilizeDriver();
        driver.get("http://hotel-test.equalexperts.io/");
    }

    @When("enter all valid information")
    public void enterAllValidInformation() {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.enterBookingInformation();
    }

    @And("save the hotel booking")
    public void saveTheHotelBooking() {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.saveHotelBooking();
    }

    @Then("the hotel booking will be saved")
    public void theHotelBookingWillBeSaved() {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.savedBooking();
    }
}
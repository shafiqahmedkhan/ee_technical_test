package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.hotelBookingForm;
import resources.getPropertyValues;

import java.io.IOException;

public class hotelBookingFormPage {

    public WebDriver driver;
    public static int bookingNo;

    @Before
    public void startBrowser() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        getPropertyValues gpv = new getPropertyValues();
        gpv.fileInputStream("HotelBookingForm.properties");
        driver.get(gpv.getUrl());
    }

    @After
    public void closeBrowser() {
        driver.close();
        driver.quit();
    }

    @Given("I have a hotel booking")
    public void iHaveAHotelBooking() throws IOException {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.enterBookingInformation();
        hbf.saveHotelBooking();
    }

    @When("I enter all valid information")
    public void iEnterAllValidInformation() throws IOException {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.enterBookingInformation();
    }

    @Then("the hotel booking will be {string}")
    public void theHotelBookingWillBeSaved(String bookingStatus) {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        if (bookingStatus.equals("saved")) {
            hbf.assertNewSavedBooking();
        } else {
            hbf.assertDeletedBooking();
        }
    }

    @And("I {string} the hotel booking")
    public void iSaveTheHotelBooking(String status) {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        if (status.equals("save")) {
            hbf.saveHotelBooking();
        } else {
            hbf.deleteLastSavedHotelBooking();
        }
    }

}

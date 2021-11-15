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

public class hotelBookingFormPage {

    public WebDriver driver;
    public static int bookingNo;

    @Before
    public void startBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://hotel-test.equalexperts.io/");
//        hotelBookingForm hbf = new hotelBookingForm(driver);
//        hbf.waitForSaveButton();
//        hbf.removeLegacyBookings();
    }

    @After
    public void closeBrowser() {
        driver.close();
        driver.quit();
    }

//    @Given("I navigate to the hotel booking form page")
//    public void iNavigateToTheHotelBookingFormPage() {
//        driver.get("http://hotel-test.equalexperts.io/");
//    }

    @When("enter all valid information")
    public void enterAllValidInformation() {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.enterBookingInformation();
    }

    @And("save the hotel booking")
    public void saveTheHotelBooking() throws InterruptedException {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.saveHotelBooking();
    }

    @Then("the hotel booking will be saved")
    public void theHotelBookingWillBeSaved() {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.assertNewSavedBooking();
    }

    @Given("I have a hotel booking")
    public void iHaveAHotelBooking() {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.enterBookingInformation();
    }

    @When("I delete the hotel booking")
    public void iDeleteTheHotelBooking() {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.deleteLastSavedHotelBooking();
    }

    @Then("then booking cannot be seen")
    public void thenBookingCannotBeSeen() {
        hotelBookingForm hbf = new hotelBookingForm(driver);
        hbf.assertDeletedBooking();
    }
}

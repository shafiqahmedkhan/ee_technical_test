package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By firstName = By.id("firstname");
    By lastName = By.id("lastname");
    By price = By.id("totalprice");
    By deposit = By.id("depositpaid");
    By checkIn = By.id("checkin");
    By checkOut = By.id("checkout");
    By uiDatePicker = By.id("ui-datepicker-div");
    By datePickerMonthYear = By.className("ui-datepicker-title");
    By datePickerNext = By.xpath("//a[@title='Next']");
    By save = By.cssSelector("input[value=' Save ']");
    By delete = By.cssSelector("input[value='Delete']");

    public void enterFirstName(String fname) {
        driver.findElement(firstName).sendKeys(fname);
    }

    public void enterLastName(String lname) {
        driver.findElement(lastName).sendKeys(lname);
    }

    public void enterPrice(String cost) {
        driver.findElement(price).sendKeys(cost);
    }

    public void depositStatus (String status) {
        Select se = new Select(driver.findElement(deposit));
        se.selectByVisibleText(status);
    }

    public void selectDate() {
        //open date picker
        driver.findElement(uiDatePicker).click();
        //get month year
        String monthYear = driver.findElement(datePickerMonthYear).getText();
        //split month and year text
        String month = monthYear.split(" ")[0].trim();
        String year = monthYear.split(" ")[1].trim();
        //select month year
        while (!(month=="November" && year=="2021")) {
            driver.findElement(datePickerNext).click();
        }
        //select day
        driver.findElement(By.xpath("//a[text()='1']"));
    }

    public void saveHotelBooking() {
        driver.findElement(save).click();
    }

    public int noOfBookings() {
        List<WebElement> bookings = driver.findElements(By.cssSelector("div[class='row']"));
        int totalNoOfBookings = bookings.size() - 2;
        return totalNoOfBookings;
    }

    public void deleteHotelBooking(int bookingNo) {
        String locator = "input[value='Delete']:nth-child(%s)";
        locator = String.format(locator, bookingNo);
        driver.findElement(By.cssSelector(locator)).click();
    }

}

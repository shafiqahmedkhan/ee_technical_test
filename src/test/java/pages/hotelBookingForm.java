package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import resources.getPropertyValues;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class hotelBookingForm {

    public WebDriver driver;
    public static String bookingId;

    public hotelBookingForm(WebDriver driver) {
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
    By row = By.cssSelector("div[id='bookings']>div[class='row']");
    By lastSavedBooking = By.cssSelector("div[id='bookings']>div[class='row']:last-child");

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

    public void selectCheckInDatePicker() {
        driver.findElement(checkIn).click();
    }

    public void selectCheckOutDatePicker() {
        driver.findElement(checkOut).click();
    }

    public void waitForDatePicker() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(uiDatePicker));
    }

    public void selectDate(String exDay, String exMonth, String exYear) {
        waitForDatePicker();
        String monthYear = driver.findElement(datePickerMonthYear).getText();
        String month = monthYear.split(" ")[0].trim();
        String year = monthYear.split(" ")[1].trim();
        while (!(month.equals(exMonth) && year.equals(exYear))) {
            driver.findElement(datePickerNext).click();
            monthYear = driver.findElement(datePickerMonthYear).getText();
            month = monthYear.split(" ")[0].trim();
            year = monthYear.split(" ")[1].trim();
        }
        driver.findElement(By.xpath("//a[text()='"+exDay+"']")).click();
    }

    public void saveHotelBooking() {
        //Getting the number of rows
        List<WebElement> rows = driver.findElements(row);
        int noOfRows = rows.size();
        //Selecting save button
        driver.findElement(save).click();
        //Waiting for the delete button to appear
        if (noOfRows==1) {
            //Only one delete button appearing when the first booking is saved
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(delete));
        } else {
            //More than one previous booking saved
            int locatorNo = noOfRows - 1;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@value='Delete'])["+locatorNo+"]")));
        }
    }

    public void enterBookingInformation() throws IOException {
        getPropertyValues gpv = new getPropertyValues();
        gpv.fileInputStream("HotelBookingForm.properties");
        enterFirstName(gpv.getFirstName());
        enterLastName(gpv.getLastName());
        enterPrice(gpv.getPrice());
        depositStatus(gpv.getDepositStatus());
        selectCheckInDatePicker();
        selectDate(gpv.getCheckInDate(), gpv.getCheckInMonth(), gpv.getCheckInYear());
        selectCheckOutDatePicker();
        selectDate(gpv.getCheckOutDate(), gpv.getCheckOutMonth(), gpv.getCheckOutYear());
    }

    public void assertNewSavedBooking() {
        WebElement booking = driver.findElement(lastSavedBooking);
        System.out.println(booking);
        String outerHtml = booking.getAttribute("outerHTML");
        System.out.println(outerHtml);
        //Assertion to check if the last saved booking has an id present thus the booking has been saved
        Assert.assertTrue(outerHtml.contains("div class=\"row\" id=\""));
    }

    public void deleteLastSavedHotelBooking() {
        WebElement booking = driver.findElement(lastSavedBooking);
        bookingId = booking.getAttribute("id");
        System.out.println(bookingId);
        String deleteBtn = String.format("input[onclick='deleteBooking(%s)']", bookingId);
        driver.findElement(By.cssSelector(deleteBtn)).click();
        //Wait to check if the delete button does not appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(
                ExpectedConditions.elementToBeClickable(By.cssSelector(deleteBtn))));
    }

    public void assertDeletedBooking() {
        String bookingId = hotelBookingForm.bookingId;
        List<WebElement> deleteBtn = driver.findElements(By.cssSelector("input[onclick='deleteBooking(" + bookingId + ")']"));
        //Assertion to check if the delete button is not displayed
        Assert.assertEquals(0, deleteBtn.size());
    }

}

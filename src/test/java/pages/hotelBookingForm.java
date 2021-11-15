package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class hotelBookingForm {

    public WebDriver driver;

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
    By bookingRow = By.cssSelector("div[id='form']>div[class='row']");
    By newBookingRow = By.cssSelector("div[id='form']>div[class='row']");
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

    public void depositStatus (int index) {
        Select se = new Select(driver.findElement(deposit));
        se.selectByIndex(index);
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
        //All the rows
        List<WebElement> rows = driver.findElements(row);
        int noOfRows = rows.size();
        System.out.println(noOfRows);
        //select save
        driver.findElement(save).click();
        //wait for the delete button
        if (noOfRows==1) {
            //only one delete button when the first booking is saved
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(delete));

        } else {
            //
            int locatorNo = noOfRows - 1;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@value='Delete'])["+locatorNo+"]")));
        }
    }

    public void enterBookingInformation() {
        enterFirstName("first");
        enterLastName("lastname");
        enterPrice("12");
        depositStatus(1);
        selectCheckInDatePicker();
        selectDate("1", "June", "2022");
        selectCheckOutDatePicker();
        selectDate("15", "June", "2022");
    }

    public int totalNoOfRows() {
        List<WebElement> rows = driver.findElements(bookingRow);
        int totalNoOfRows = rows.size();
        return totalNoOfRows;
    }

    public int noOfSavedBookings() {
        List<WebElement> rows = driver.findElements(bookingRow);
        int noOfSavedBookings = (rows.size()) - 2;
        return noOfSavedBookings;
    }

    public void assertDeletedBooking() {
//        Assert.assertTrue(driver.findElement(By.cssSelector(this.deleteBtn)).isDisplayed());
    }

    public void assertNewSavedBooking() {
        WebElement booking = driver.findElement(lastSavedBooking);
        System.out.println(booking);
        String outerHtml = booking.getAttribute("outerHTML");
        System.out.println(outerHtml);
        //assertion to check if the last saved booking has an id present thus the booking has been saved
        Assert.assertEquals(outerHtml.contains("div class=\"row\" id=\""), true);
    }

    public void deleteLastSavedHotelBooking() {
        WebElement booking = driver.findElement(lastSavedBooking);
        String bookingId = booking.getAttribute("id");
        String deleteBtn = String.format("input[onclick='deleteBooking(%s)']", bookingId);
        driver.findElement(By.cssSelector(deleteBtn)).click();
    }

}

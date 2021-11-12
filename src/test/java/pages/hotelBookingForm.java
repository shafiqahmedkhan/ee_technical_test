package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class hotelBookingForm {

    public WebDriver driver;
    int bookingNo;
    String savedRow;
    String bookingId;
    String deleteBtn;
    String clickingDelete;

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
    By bookingRow = By.cssSelector("div[class='row']");

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
        driver.findElement(save).click();
    }

//    public void savedBooking() {
//        bookingNo();
//        savedRow();
//        waitForSavedRow();
//        bookingId();
//        deleteBtn();
//        Assert.assertTrue(driver.findElement(By.cssSelector(this.deleteBtn)).isDisplayed());
//    }

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

    public void deleteLastBooking() {
        noOfSavedBookings();
        String rowLocator = String.format("div[class='row']:nth-child(%s)", noOfSavedBookings());
        String bookingId = driver.findElement(By.cssSelector(rowLocator)).getAttribute("id");
        String deleteBtn = String.format("input[onclick='deleteBooking(%s)']", bookingId);
        driver.findElement(By.cssSelector(deleteBtn)).click();
    }





//    public String rowLocator(int bookingNo) {
//        String rowLocator = String.format("div[class='row']:nth-child(%s)", bookingNo);
//        return rowLocator;
//    }

//    public void waitForSavedRow() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(r)));
//    }

//    public String bookingId() {
//        bookingId = driver.findElement(By.cssSelector(savedRow)).getAttribute("id");
//        return bookingId;
//    }

//    public String deleteBtn() {
//        deleteBtn = String.format("input[onclick='deleteBooking(%s)']", bookingId);
//        return  deleteBtn;
//    }

//    public void clickingDelete() {
//        driver.findElement(By.cssSelector(deleteBtn)).click();
//    }

//        public void deleteABooking() {
//        bookingNo();
//        savedRow();
//        waitForSavedRow();
//        bookingId();
//        deleteBtn();
//        clickingDelete();
//    }

//    public void verifyADeletedBooking() {
//
////        Assert.assertTrue(hooks.findElement(By.cssSelector(this.deleteBtn)).isDisplayed());
//    }

    public void removeLegacyBookings() {
        int noOfSavedBookings = noOfSavedBookings();
        while (noOfSavedBookings!=2) {
            deleteLastBooking();
            noOfSavedBookings--;
            System.out.println(noOfSavedBookings);
        }
    }

}

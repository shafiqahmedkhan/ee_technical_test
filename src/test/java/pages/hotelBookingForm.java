package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
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

    public void savedBooking() {
        bookingNo();
        savedRow();
        waitForSavedRow();
        bookingId();
        deleteBtn();
        Assert.assertTrue(driver.findElement(By.cssSelector(this.deleteBtn)).isDisplayed());
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

    public void bookingNo() {
        List<WebElement> bookings = driver.findElements(bookingRow);
        this.bookingNo = bookings.size();
    }

    public void savedRow() {
        this.savedRow = String.format("div[class='row']:nth-child(%s)", this.bookingNo);
    }

    public void waitForSavedRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(this.savedRow)));
    }

    public void bookingId() {
        this.bookingId = driver.findElement(By.cssSelector(this.savedRow)).getAttribute("id");
    }

    public void deleteBtn() {
        this.deleteBtn = String.format("input[onclick='deleteBooking(%s)']", this.bookingId);
    }

    public void clickingDelete() {
        driver.findElement(By.cssSelector(this.deleteBtn)).click();
    }

    public void deleteABooking() {
        bookingNo();
        System.out.println("1");
        savedRow();
        System.out.println("2");
        waitForSavedRow();
        System.out.println("3");
        bookingId();
        System.out.println("4");
        deleteBtn();
        System.out.println("5");
        clickingDelete();
        System.out.println("6");
        System.out.println(this.deleteBtn);
        System.out.println(deleteBtn);
    }

    public void verifyADeletedBooking() {
        System.out.println(this.deleteBtn);
        System.out.println(deleteBtn);
//        Assert.assertTrue(driver.findElement(By.cssSelector(this.deleteBtn)).isDisplayed());
    }

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class homePage {

    WebDriver driver;

    public homePage(WebDriver driver) {
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

    public void depositStatus (int index) {
        Select se = new Select(driver.findElement(deposit));
        se.selectByIndex(index);
    }

    public void selectDate() {
        //open date picker
        driver.findElement(checkIn).click();
        //wait for the date picker to be displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(uiDatePicker));
        //get month year
        String monthYear = driver.findElement(datePickerMonthYear).getText();
        //split month and year text
        String month = monthYear.split(" ")[0].trim();
        String year = monthYear.split(" ")[1].trim();
        //select month year
        while (!(month.equals("June") && year.equals("2022"))) {
            driver.findElement(datePickerNext).click();
            monthYear = driver.findElement(datePickerMonthYear).getText();
            month = monthYear.split(" ")[0].trim();
            year = monthYear.split(" ")[1].trim();
        }
        //select day
        driver.findElement(By.xpath("//a[text()='1']")).click();
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

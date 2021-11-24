package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class getPropertyValues  {

    Properties prop = new Properties();

    public void fileInputStream(String formPage) throws IOException {
        String currentWorkingDir = System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(currentWorkingDir+"/src/test/java/resources/"+formPage);
        prop.load(fis);
    }

    public String getUrl() {
        String url = prop.getProperty("url");
        return url;
    }

    public String getFirstName() {
        String firstName = prop.getProperty("firstName");
        return firstName;
    }

    public String getLastName() {
        String lastName = prop.getProperty("lastName");
        return lastName;
    }

    public String getPrice() {
        String price = prop.getProperty("price");
        return price;
    }

    public String getDepositStatus() {
        String depositStatus = prop.getProperty("depositStatus");
        return depositStatus;
    }

    public String getCheckInDate() {
        String checkInDate = prop.getProperty("checkInDate");
        return checkInDate;
    }

    public String getCheckInMonth() {
        String checkInMonth = prop.getProperty("checkInMonth");
        return checkInMonth;
    }

    public String getCheckInYear() {
        String checkInYear = prop.getProperty("checkInYear");
        return checkInYear;
    }

    public String getCheckOutDate() {
        String checkOutDate = prop.getProperty("checkOutDate");
        return checkOutDate;
    }

    public String getCheckOutMonth() {
        String checkOutMonth = prop.getProperty("checkOutMonth");
        return checkOutMonth;
    }

    public String getCheckOutYear() {
        String checkOutYear = prop.getProperty("checkOutYear");
        return checkOutYear;
    }

}


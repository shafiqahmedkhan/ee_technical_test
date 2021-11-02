package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class getPropertyValues  {

    Properties prop = new Properties();

    public void fileInputStream() throws IOException {
        FileInputStream fis = new FileInputStream("HotelBookingForm.properties");
        prop.load(fis);
    }

    public void getUrl() {
        prop.getProperty("url");
    }

}


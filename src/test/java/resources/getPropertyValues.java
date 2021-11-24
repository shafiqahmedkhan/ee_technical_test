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



}


package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    private FileInputStream fis;

    public ConfigReader() {
        try {
            readProps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties readProps() throws IOException {
        try {
            fis = new FileInputStream("src/main/resources/configuration.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return properties;
    }

    public String browser() {
        return properties.getProperty("testBrowser");
    }

    public String baseURL() {
        return properties.getProperty("baseURL");
    }

    public String gitURL() {
        return properties.getProperty("gitURL");
    }

    public String resumePath() {
        return System.getProperty("user.dir") + properties.getProperty("resumeFolder");
    }

    public String coverLetterPath() {
        return System.getProperty("user.dir") + properties.getProperty("coverLetter");
    }


}

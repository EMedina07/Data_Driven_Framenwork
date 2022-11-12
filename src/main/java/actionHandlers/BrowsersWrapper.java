package actionHandlers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import frameworkUtilities.GoogleChrome;

public class BrowsersWrapper {
    private Logger log = LogManager.getLogger(BrowsersWrapper.class.getName());

    public GoogleChrome googleChrome = null;

    public BrowsersWrapper(){
        googleChrome = new GoogleChrome(this.buildChromeDriver());
    }

    private ChromeDriver buildChromeDriver(){
        ChromeOptions options = null;

        try {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

            options = new ChromeOptions();

            log.info("Flow 1: driver configured successfully");
        }
        catch (Exception e){
            log.fatal("Error in the Flow 1: the system can\'t get the ChromeOptions");
            log.fatal("Exception detail " + e.getMessage());
        }


        return new ChromeDriver(options);
    }
}

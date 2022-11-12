package frameworkActions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class DriverActions {
    private Logger log = LogManager.getLogger(DriverActions.class.getName());

    protected WebDriver driver = null;

    public DriverActions(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void openBrowser(String url){
        try{
            this.driver.get(url);
            log.info("Flow 1: Access to page completed");
        }catch (Exception e){
            log.fatal("Error in the Flow 1: the system can\'t access to page");
            log.fatal("Exception detail " + e.getMessage());
        }
    }

    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public void CloseWindow(){
        this.driver.close();
    }

    public void closeBrowser(){
        this.driver.quit();
    }
}

package frameworkActions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PageActions {
    private Logger log = LogManager.getLogger(PageActions.class.getName());
    private WebDriver driver = null;

    public PageActions(WebDriver driver){
        this.driver = driver;
    }

    public void toDoClick(String locatorMethod, String location){

        this.finderValueEmpty(locatorMethod, location);

        WebElement element = this.getElement(locatorMethod, location);

        if (element.isDisplayed())
        {
            element.click();
            log.info("Flow 2: Event click is done from element on the location " + location);
        }
        else {
            log.error("Element on location " + location + " not found");
        }
    }

    public void typeText(String locatorMethod, String location, String value){

        this.finderValueEmpty(locatorMethod, location, value);

        WebElement element = this.getElement(locatorMethod, location);

        if (element.isDisplayed())
        {
            element.sendKeys(value);
            log.info("Flow 2: It's typed the value " + value + " from the element on location " + location);
        }
        else {
            log.error("Element on location " + location + " not found");
        }
    }

    public void findItem(String locatorMethod, String location, String value){

        this.finderValueEmpty(locatorMethod, location, value);

        WebElement element = this.getElement(locatorMethod, location);

        if (element.isDisplayed())
        {
            element.sendKeys(value);
            element.sendKeys(Keys.ENTER);
            log.info("Flow 2: It's typed the value " +value+" from the element on location " + location);
        }
        else {
            log.error("Element on location " + location + " not found");
        }
    }

    public void selectItem(String locatorMethod, String location, String value){

        this.finderValueEmpty(locatorMethod, location, value);

        WebElement element = this.getElement(locatorMethod, location);

        if (element.isDisplayed())
        {
            Select items =  new Select(element);
            items.selectByVisibleText(value);

            log.info("Flow 2: It's selected the value " +value+ "from the element on location " + location);
        }
        else {
            log.error("Element on location " + location + " not found");
        }
    }

    public void mouseOver(String locatorMethod, String location, String value){

        this.finderValueEmpty(locatorMethod, location, value);

        Actions action = new Actions(this.driver);
        WebElement element = this.getElement(locatorMethod, location);

        if (element.isDisplayed())
        {
            action.moveToElement(element).moveToElement(this.driver.findElement(By.xpath(value))).click().build().perform();
            log.info("Flow 2: The event mouseOver is done from element on location " + location);
        }
        else {
            log.error("Element on location " + location + " not found");
        }
    }

    public WebElement getElement(String locatorMethod, String location){
        WebElement element = null;
        this.finderValueEmpty(locatorMethod, location);

        try {
            element = this.driver.findElement(builLocation(locatorMethod, location));
        }
        catch (Exception e){
            log.fatal("Error in the Flow 2: the system can\'t work with object in " + location);
            log.fatal("Exception detail " + e.getMessage());
        }

        return element;
    }

    private By builLocation(String locatorMethod, String location){
        if ("css".equals(locatorMethod)) {
            return By.cssSelector(location);
        }
        else {
            if ("id".equals(locatorMethod)) {
                return By.id(location);
            }
        }

        return By.xpath(location);
    }

    private void finderValueEmpty(String locatorMethod, String location){
        if(locatorMethod.isEmpty()){
            log.error("Error - the parameter locatorMethod is empty");
        }

        if (location.isEmpty()){
            log.error("Error - the parameter location is empty");
        }

        return;
    }

    private void finderValueEmpty(String locatorMethod, String location, String value){
        this.finderValueEmpty(locatorMethod, location);

        if(value.isEmpty()){
            log.error("Error - the parameter value is empty");
        }

        return;
    }
}

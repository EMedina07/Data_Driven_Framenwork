package actionHandlers;

import frameworkActions.PageActions;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class FlowExecutor {
    private Logger log = LogManager.getLogger(FlowExecutor.class.getName());

    private WebDriver driver = null;
    private PageActions pageActions = null;

    public FlowExecutor(WebDriver driver){
        this.driver = driver;
        this.pageActions = new PageActions(driver);

        log.info("Flow 2: staring iteration with the page objects");
    }

    public void ExecuteEventOnElementsOfPage(String locatorMethod, String location, String event, String value) throws InterruptedException{
        switch (event) {
            case "click":
                this.pageActions.toDoClick(locatorMethod, location);
                break;

            case "setText":
                this.pageActions.typeText(locatorMethod, location, value);
                break;

            case "selectItem":
                this.pageActions.selectItem(locatorMethod, location, value);
                break;

            case "mouseOver":
                this.pageActions.mouseOver(locatorMethod, location, value);
                break;

            case "findItem":
                this.pageActions.findItem(locatorMethod, location, value);
                break;

            default:
                System.out.println("El elemento no es valido");
                break;
        }
    }
}

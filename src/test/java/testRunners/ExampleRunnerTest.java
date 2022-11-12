package testRunners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import frameworkActions.ReportActions;
import org.junit.Before;
import org.junit.Test;
import actionHandlers.BrowsersWrapper;
import frameworkActions.FileActions;
import frameworkUtilities.FileColumnsModel;
import actionHandlers.FlowExecutor;
import java.io.IOException;

public class ExampleRunnerTest{
    private FileActions excelFile = null;
    private BrowsersWrapper browsers = new BrowsersWrapper();
    private ExtentReports extent = null;
    ReportActions reportActions = new ReportActions();


    @Before
    public void configuration() throws IOException, InterruptedException  {
        extent = reportActions.Report;


        browsers.googleChrome.openBrowser("url page");
        browsers.googleChrome.maximizeWindow();

        Thread.sleep(3000);

        excelFile = new FileActions("src\\main\\resources\\dataFile.xlsx");
    }

    @Test
    public void testUsingSeveralSheet() throws  IOException, InterruptedException{
        FlowExecutor executorTest = new FlowExecutor(browsers.googleChrome.getDriver());

        String testCaseName = "";
        ExtentTest test = null;

        for (int i = 0; i < excelFile.getNumberOfSheets(); i++){

            excelFile.SheetNumber = i;
            testCaseName = excelFile.getSheeName();

            try{
                test = extent.createTest(testCaseName);

                for (int j = 0; j <= excelFile.getNumberOfRows(); j++){

                    if(j == 0){
                        continue;
                    }

                    executorTest.ExecuteEventOnElementsOfPage(excelFile.getValueOfCell(j, FileColumnsModel.LocatorMethod), excelFile.getValueOfCell(j, FileColumnsModel.LOCATION), excelFile.getValueOfCell(j, FileColumnsModel.EVENT), excelFile.getValueOfCell(j, FileColumnsModel.VALUE));
                }

                test.pass("The test step for the test case " + testCaseName + " are passed successfully!")
                        .addScreenCaptureFromPath(reportActions.capture(browsers.googleChrome.getDriver()).toString(), "Evidences test successfull");

            }catch (Exception e)
            {
                test.fail("Oh oh!!, has came about a problem in the test case " + testCaseName + " please to see the file applicationLogs")
                        .addScreenCaptureFromPath(reportActions.capture(browsers.googleChrome.getDriver()).toString(), "Evidences error");
            }
        }

        excelFile.closeFile();
        browsers.googleChrome.closeBrowser();
        extent.flush();
    }
}

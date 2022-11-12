package frameworkActions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ReportActions {
    public final ExtentReports Report = new ExtentReports();

    public ReportActions(){
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReportResults.html");
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        spark.config().setTheme(Theme.DARK);
        Report.attachReporter(spark);
    }

    public String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("src/../ErrImages/" + System.currentTimeMillis()
                + ".png");
        String flpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return flpath;
    }

    public String captureFromBase64(WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        String sourceFile  = screenshot.getScreenshotAs(OutputType.BASE64);

        return screenshot.toString();
    }
}

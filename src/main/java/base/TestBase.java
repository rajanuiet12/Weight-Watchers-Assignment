
package base;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestBase {
	public WebDriver driver;
	public Properties config;
	public FileInputStream propertiesFile;

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public ExtentTest test;

	/*Method to setup configuration for Reporting using Extent Reports.*/
	@BeforeTest(alwaysRun = true)
	public void iniExtentReport() {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

		String filename = "src/ExtentReport/TestReport_" + timeStamp + ".html";
		System.out.println(filename);
		htmlReporter = new ExtentHtmlReporter(filename);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Host Name", "WW");
		extent.setSystemInfo("Broswer Name", "Chrome");
		extent.setSystemInfo("Environment", "QA");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Automation Test Report :: Weight Watchers");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);

	}

	@AfterTest(alwaysRun = true)
	public void closeSuite() throws Exception {
		extent.flush();
	}

	/*Method which will run after each test case i.e. after each @Test method. This method will mark test case
	 as pass or fail depending upon the execution result. It will take a screenshot in case test is failed 
	 or skipped.*/
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable().getMessage());
			takeScreenShot(result, "_fail");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Test case SKIPED due to below issue:",
					ExtentColor.YELLOW));
			test.skip(result.getThrowable().getMessage());
			takeScreenShot(result, "_skip");
		}
		result.getTestContext().getSkippedTests().removeResult(result.getMethod());
	}

	/*This method will take the screenshot of the web page where a test will fail or skip. Screenshot will be attached
	 with the report generated at the end of the suite execution.*/
	private void takeScreenShot(ITestResult result, String status) {
		try {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String image_name = result.getName() + "_" + timeStamp + status + ".png";
			String filename = "src/ExtentReport/" + image_name;
			FileUtils.copyFile(source, new File(filename));
			System.out.println("Screenshot taken");
			test.addScreenCaptureFromPath(image_name);
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
	}

	/*This method will setup the browser on which tests will be executed. Setup method will load all the required
	 web pages used in a test case.*/
	@BeforeClass
	public void setUp() throws Exception {
		config = new Properties();
		propertiesFile = new FileInputStream("src/test/resources/Properties/Environment.properties");
		config.load(propertiesFile);

		executionBrowser();
		setup();
	}

	public void executionBrowser() throws Exception {
		if (config.getProperty("browser.name").equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver", "src/test/resources/Executable/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
		}
	}

	public void setup() throws Exception {

	}

	/*This method will kill the webdriver session by calling the quit method.*/
	@AfterClass
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
}

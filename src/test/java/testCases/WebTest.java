package testCases;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.Status;

import base.TestBase;
import pages.FindAStudioPage;
import pages.HomePage;
import pages.StudioPage;
import utility.XmlReader;

public class WebTest extends TestBase {
	HomePage home_Page;
	FindAStudioPage find_A_Studio_Page;
	StudioPage studio_Page;

	String studio_Name_Find_A_Studio_Page;
	String studio_Distance;

	public Logger log;

	public void setup() throws Exception {
		log = LogManager.getLogger(WebTest.class.getName());
		home_Page = new HomePage(driver, log);
		find_A_Studio_Page = new FindAStudioPage(driver, log);
		studio_Page = new StudioPage(driver, log);
	}

	@Test(dataProvider = "studio_TestData")
	public void studioDetails(String zip, String day) throws Exception {
		test = extent.createTest("Weight Watchers selenium webdriver assignment.");

		log.info("******** Starting Automation Task 1: Web UI test case. ********");

		test.log(Status.INFO, "###### Opening Weight-Watchers home page. #######");
		log.info("###### Opening Weight-Watchers home page. #######");
		driver.get("https://www.weightwatchers.com/us/");
		Assert.assertEquals(home_Page.verifyPage(), true, "Could not open Weight-Watchers home page.");
		Assert.assertEquals(home_Page.getPageTitle(), "WW (Weight Watchers): Weight Loss & Wellness Help");
		log.info("###### Successfully landed on Weight-Watchers home page. #######");
		test.log(Status.PASS, "###### Successfully landed on Weight-Watchers home page. #######");
		test.log(Status.INFO, "Clicking on Find a Studio link to navigate to find studio page.");
		home_Page.findAStudio();
		Assert.assertEquals(find_A_Studio_Page.verifyPage(), true, "Could not open Find a Studio page.");
		Assert.assertEquals(find_A_Studio_Page.getPageTitle(), "Find WW Studios & Meetings Near You | WW USA");
		log.info("###### Successfully landed on Weight-Watchers Find a Studio page. #######");
		test.log(Status.PASS, "###### Successfully landed on Weight-Watchers Find a Studio page. #######");
		test.log(Status.INFO, "Searching studios on Find a Studio page for zip: " + zip);
		find_A_Studio_Page.enterZipLocation(zip);
		find_A_Studio_Page.searchStudio();
		test.log(Status.PASS, "Successfully searched studios on Find a Studio page for zip: " + zip);
		studio_Name_Find_A_Studio_Page = find_A_Studio_Page.getStudioName("1");
		studio_Distance = find_A_Studio_Page.getStudioDistance("1");
		log.info("Name of first studio present for zip 10011 is: " + studio_Name_Find_A_Studio_Page);
		test.log(Status.INFO, "Name of first studio present for zip 10011 is: " + studio_Name_Find_A_Studio_Page);
		log.info("Distance of first studio present for zip 10011 is: " + studio_Distance);
		test.log(Status.INFO, "Distance of first studio present for zip 10011 is: " + studio_Distance);
		test.log(Status.INFO, "Clicking on first studio on Find a Studio page.");
		find_A_Studio_Page.clickStudio("1");
		test.log(Status.PASS, "Successfully clicked on first studio on Find a Studio page.");
		test.log(Status.INFO, "Checking if name of studio on Find a Studio page matches with the name on Studio page.");
		log.info("Checking if name of studio on Find a Studio page matches with the name on Studio page.");
		String studio_Name_Studio_Page = studio_Page.getStudioName();
		Assert.assertEquals(studio_Name_Find_A_Studio_Page, studio_Name_Studio_Page,
				"Studio name on Find a Studio page does not match" + " with studio name on Studio page.");
		test.log(Status.PASS, "Name of studio on Find a Studio page matches with the name on Studio page.");
		log.info("Name of studio on Find a Studio page matches with the name on Studio page.");
		String hoursOfOperation = studio_Page.getStudioCurrentDayOperationHours();
		log.info("Studio's hours of operation for current day are " + hoursOfOperation);
		test.log(Status.INFO, "Studio's hours of operation for current day are " + hoursOfOperation);
		String agent_Meetings_Count = studio_Page.printNumberOfMeetingsOnADay(day);
		log.info("Number of meetings on: " + day + ": " + agent_Meetings_Count);
		test.log(Status.INFO, "Number of meetings on: " + day + ": " + agent_Meetings_Count);
	}

	@DataProvider(name = "studio_TestData")
	public Object[][] rideDetails() throws IOException, ParserConfigurationException, SAXException {
		XmlReader reader = new XmlReader();
		return reader.testData(new File("src/test/resources/TestData/TestData.xml").getAbsolutePath(), "studioDetails");
	}
}

package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.ElementActions;

public class FindAStudioPage {

	private Logger log;
	private ElementActions element_Actions;

	public FindAStudioPage(WebDriver driver, Logger log) throws Exception {

		this.log = log;
		element_Actions = new ElementActions(driver);
	}

	public boolean verifyPage() {
		By find_A_Studio = By.xpath(
				"//*[@class='meeting-finder__header-headline-title spice-translated'][contains(text(),'Find a Studio')]");
		return element_Actions.checkElementPresence(find_A_Studio);
	}

	public String getPageTitle() {
		log.info("Fetching title of Find a Studio page.");
		return element_Actions.getTitle();
	}

	public void enterZipLocation(String zip_Code) {
		log.info("Entering zip code on Find a Studio page.");
		By enter_Location = By.id("meetingSearch");
		element_Actions.findElement(enter_Location);
		element_Actions.waitUntilVisibilityLocated(enter_Location);
		element_Actions.sendKeys(zip_Code);
		log.info("Successfully entered zip code on Find a Studio page.");
	}

	public void searchStudio() {
		log.info("Clicking on search icon after entering zip on Find a Studio page.");
		By search = By.cssSelector("#meetingSearch + button");
		element_Actions.findElement(search);
		element_Actions.waitUntilVisibilityLocated(search);
		element_Actions.click();
		log.info("Successfully clicked on search icon after entering zip on Find a Studio page.");
	}

	public String getStudioName(String studio_Number_In_List) {
		log.info("Fetching name of " + studio_Number_In_List + " studio on Find a Studio page.");
		By location_Name = By.xpath("(//*[@class='location__name'])[" + studio_Number_In_List + "]");
		element_Actions.findElement(location_Name);
		element_Actions.waitUntilVisibilityLocated(location_Name);
		return element_Actions.getElementText();
	}

	public String getStudioDistance(String studio_Number_In_List) {
		log.info("Fetching distance of " + studio_Number_In_List + " studio on Find a Studio page.");
		By location_Distance = By.xpath("(//*[@class='location__distance'])[" + studio_Number_In_List + "]");
		element_Actions.findElement(location_Distance);
		element_Actions.waitUntilVisibilityLocated(location_Distance);
		return element_Actions.getElementText();
	}

	public void clickStudio(String studio_Number_In_List) {
		log.info("Clicking on " + studio_Number_In_List + " studio on Find a Studio page.");
		By location_Name = By.xpath("(//*[@class='location__name'])[" + studio_Number_In_List + "]");
		element_Actions.findElement(location_Name);
		element_Actions.waitUntilVisibilityLocated(location_Name);
		element_Actions.click();
		log.info("Successfully clicked on " + studio_Number_In_List + " studio on Find a Studio page.");
	}

}

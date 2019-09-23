package pages;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.ElementActions;
import utility.Functions;

public class StudioPage {

	private Logger log;
	private ElementActions element_Actions;
	private Functions functions;

	public StudioPage(WebDriver driver, Logger log) throws Exception {

		this.log = log;
		element_Actions = new ElementActions(driver);
		functions = new Functions();
	}

	public String getStudioName() {
		log.info("Fetching Studio name from Studio page.");
		By location_Name = By.xpath("//*[@class='location__name']");
		element_Actions.findElement(location_Name);
		element_Actions.waitUntilVisibilityLocated(location_Name);
		log.info("Studio name successfully fetched from Studio page.");
		return element_Actions.getElementText();
	}

	public String getStudioCurrentDayOperationHours() {
		log.info("Fetching current day operation hours of the studio.");
		By operation_Hours = By.xpath(
				"//*[@class='hours-list-item-wrapper hours-list--currentday']/*[@class='hours-list-item-hours']");
		element_Actions.findElement(operation_Hours);
		element_Actions.waitUntilVisibilityLocated(operation_Hours);
		log.info("Current day operation hours successfully fetched.");
		return element_Actions.getElementText();
	}

	public String printNumberOfMeetingsOnADay(String day) {
		log.info("Fetching number of meetings agents have on "+ day);
		By meetings = By.xpath(
				"//*[@class='schedule-detailed-day-label'][contains(text(),'"+day+"')]/following-sibling::div//*[@class='schedule-detailed-day-meetings-item-leader']");
		List meeting_Leaders=element_Actions.findElements(meetings);
		element_Actions.waitUntilVisibilityLocated(meetings);
		
		return functions.countFrequenciesFromList(element_Actions.getElementsText(meeting_Leaders));
	}

}

package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.ElementActions;

public class HomePage {

	private Logger log;
	private ElementActions element_Actions;
	private By welcome_Back_Text;
	private By username;
	private By password;
	private By sign_In;

	public HomePage(WebDriver driver, Logger log) throws Exception {

		this.log = log;
		element_Actions = new ElementActions(driver);
	}

	public boolean verifyPage() {
		By sign_Up = By.xpath("(//*[@class='menu-link__inner-wrapper'][contains(text(),'Sign up')])[1]");
		return element_Actions.checkElementPresence(sign_Up);
	}

	public String getPageTitle() {
		log.info("Fetching title of Home page.");
		return element_Actions.getTitle();
	}

	public void findAStudio() {
		log.info("Clicking on Find a Studio link on Home page.");
		By findAStudio = By.xpath("(//*[@class='menu-link__inner-wrapper'][contains(text(),'Find a Studio')])[1]");
		element_Actions.findElement(findAStudio);
		element_Actions.waitUntilVisibilityLocated(findAStudio);
		element_Actions.click();
		log.info("Successfully clicked on Find a Studio link on Home page.");
	}
}

package pageObjectModels;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Screenshots;

public class GymServicePage extends BasePage{
	
	//Constructor
	public GymServicePage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(linkText="Gym")
	public WebElement gymIcon;
	
	@FindBy(xpath="*//ul[@id=\"filter_ul\"]/li")
	public List<WebElement> gymSubMenu;
	
	//By Variables
	By submenu=By.xpath("*//ul[@id=\"filter_ul\"]/li");
	
	//Actions
	public void openApp() {
		driver.navigate().to("https://www.justdial.com/");
	}
	
	public String clickGymMenu() throws IOException {
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", gymIcon);
		String path=Screenshots.screenShot("gymIconClick", driver);
		jse.executeScript("arguments[0].click();",gymIcon);
		return path;
	}
	public void getSubmenu() throws InterruptedException {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submenu));
	}

}

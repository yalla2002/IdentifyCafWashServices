package pageObjectModels;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;
import utils.Screenshots;

public class HomePage extends BasePage{
	
	//Constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//*[@id='__next']/div/section/div[1]/section[1]/div/div[2]/div/div[3]/span")
	WebElement popUp;
	
	@FindBy(xpath="//*[@id='city-auto-sug']")
	WebElement locationInput;
	
	@FindBy(xpath="//*[@id='react-autowhatever-city-auto-suggest--item-1']/a")
	public WebElement locationOpton;
	
	@FindBy(xpath="//*[@id='main-auto']")
	WebElement serviceInput;
	
	@FindBy(xpath="//*[@id='__next']/div/section/header/div/div[2]/div/div[2]/label/div[3]/div/div")
	WebElement searchBtn;
	
	@FindBy(xpath="//*[@id='header_freelisting']/a/div[2]")
	WebElement freeListing;
	
	//By variables
	By popup=By.xpath("//*[@id='__next']/div/section/div[1]/section[1]/div/div[2]/div/div[3]/span");
	
	By location=By.xpath("//*[@id='react-autowhatever-city-auto-suggest--item-1']/a");
	
	By sortBy=By.xpath("//*[text()='Sort By']");
	
	
	//Actions
	public String closePopUp() throws IOException {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
		String path=Screenshots.screenShot("popup", driver);
		popUp.click();
		return path;
	}
	
	public String locationSearch() throws IOException {
		List<String> excelInputs=ExcelUtils.readExcel();
		locationInput.sendKeys(excelInputs.get(0));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(location));
		String path=Screenshots.screenShot("locationSearch", driver);
		locationOpton.click();
		return path;
	}
	
	public String serviceSearch() throws IOException, InterruptedException {
		List<String> excelInputs=ExcelUtils.readExcel();
		serviceInput.sendKeys(excelInputs.get(1));
		searchBtn.click();
		String path=Screenshots.screenShot("serviceSearch", driver);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(sortBy));
		return path;
	}
	
	public void openFreeListing() {
		freeListing.click();
	}
}

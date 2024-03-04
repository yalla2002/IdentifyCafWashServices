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

public class FreeListingPage extends BasePage{
	//Declaring Variables
	public static String errorMessageText;
	
	//Constructor
	public FreeListingPage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="(//input[@class='entermobilenumber_input__eCrdc input fw500'])[1]")
	WebElement phoneInput;
	
	@FindBy(xpath="//*[@id='listyourbusiness']/div[1]/form/button")
	WebElement phnSubmit;
	
	@FindBy(xpath="//*[contains(text(),'This Number has been Blocked for Signing Up')]")
	public WebElement errorMessage;
	
	//By Variables
	By errorMsg=By.xpath("//*[contains(text(),'This Number has been Blocked for Signing Up')]");
	
			
	//Actions
	
	public void enterWrongPhn() throws IOException {
		List<String> excelInputs=ExcelUtils.readExcel();
		phoneInput.sendKeys(excelInputs.get(2));
		phnSubmit.click();
	}
	
	public String captureErrorMessage() throws InterruptedException, IOException {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
		String path=Screenshots.screenShot("errorMessage", driver);
		errorMessageText=errorMessage.getText();
		System.out.println("\n"+errorMessageText+"\n");
		return path;
		
	}
	
}

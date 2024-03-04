package pageObjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	//Declaring Web Driver
	public WebDriver driver;
	 public BasePage(WebDriver driver) {
		 this.driver=driver;
		 
		 //PageFactory --> Used to initialize web elements
		 PageFactory.initElements(driver, this);
	 }
}

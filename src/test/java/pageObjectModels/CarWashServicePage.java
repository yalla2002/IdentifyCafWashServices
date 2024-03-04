package pageObjectModels;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Screenshots;

public class CarWashServicePage extends BasePage{
	//Declaring the variables
	public static List<String> carWashsingServices=new ArrayList<String>();
	public static List<String> customersRating=new ArrayList<String>();
	public static List<String> customersVotes=new ArrayList<String>();
	public static List<String> phoneNumbers=new ArrayList<String>();
	
	//Constructor
	public CarWashServicePage(WebDriver driver) {
		super(driver);
	}
	
	
	//Locators
	@FindBy(xpath="//div[@class='jsx-3349e7cd87e12d75 resultbox_title_anchor  line_clamp_1']")
	List<WebElement> serviceNames;
	
	@FindBy(xpath="*//div[@class='jsx-3349e7cd87e12d75 button_flare']")
	List<WebElement> phnBtn;
	
	@FindBy(xpath="//div[@class='jsx-3349e7cd87e12d75 resultbox_totalrate mr-6 font14 fw700 colorFFF']")
	List<WebElement> rating;
	
	@FindBy(xpath="//div[@class='jsx-3349e7cd87e12d75 resultbox_countrate ml-12 mr-12 font14 fw400 color777']")
	List<WebElement> votes;
	
	@FindBy(xpath ="*//span[@class='jsx-3349e7cd87e12d75 callcontent']")
	List<WebElement> phnText;
	
	@FindBy(xpath="//*[text()='Sort By']")
	WebElement sortByBtn;
	
	@FindBy(xpath="//*[text()='Rating']")
	WebElement ratingBtn;
	
	//By Variables
	By serviceName=By.xpath("//*[@id=\"044PXX44.XX44.220704153516.B5J5\"]/div/div[2]/h2/a/div[1]");
	
	//Actions
	public String getCarWashingServiceandPhone() throws InterruptedException, IOException {
		sortByBtn.click();
		ratingBtn.click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(serviceName));
		for(int i=0;i<5;i++) {
			carWashsingServices.add(serviceNames.get(i).getText());
			customersVotes.add(votes.get(i).getText());
			customersRating.add(rating.get(i).getText());
			phnBtn.get(i).click();
		}
		String path=Screenshots.screenShot("carwashServices", driver);
		for(int j=0;j<5;j++) {
			phoneNumbers.add(phnText.get(j).getText());
			System.out.println("\n"+carWashsingServices.get(j)+"----"+phnText.get(j).getText()+"----"+customersRating.get(j)+"----"+customersVotes.get(j));
		}
		return path;
	}
	
}

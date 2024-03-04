package stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectModels.CarWashServicePage;
import pageObjectModels.FreeListingPage;
import pageObjectModels.GymServicePage;
import pageObjectModels.HomePage;

public class carWashServices{
	public static WebDriver driver;
	
	//Feature-1
	@Given("user is on the Just Dial Application")
	public void user_is_on_the_just_dial_application() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-blink-features=AutomationControlled");
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
	    driver.get("https://www.justdial.com/");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@When("the user clicking close popup")
	public void the_user_clicking_close_popup() throws IOException {
		HomePage homePage=new HomePage(driver);
		homePage.closePopUp();
	}

	@When("the user need to enter location and services")
	public void the_user_need_to_enter_location_and_services() throws IOException, InterruptedException {
		HomePage homePage=new HomePage(driver);
		homePage.locationSearch();
		homePage.serviceSearch();
	}

	@Then("the user need to capture top five car wash services")
	public void the_user_need_to_capture_top_five_car_wash_services() throws InterruptedException, IOException {
		CarWashServicePage carWashServicePage=new CarWashServicePage(driver);
		carWashServicePage.getCarWashingServiceandPhone();
	}
	
	//Feature-2
	@Given("user is on the freeListing Page")
	public void user_is_on_the_free_listing_page() {
		HomePage homePage=new HomePage(driver);
		homePage.openFreeListing();
	}

	@When("user need to enter wrong phone number")
	public void user_need_to_enter_wrong_phone_number() throws IOException {
		FreeListingPage freeListPage=new FreeListingPage(driver);
		freeListPage.enterWrongPhn();
	}

	@Then("user need to capture error message")
	public void user_need_to_capture_error_message() throws InterruptedException, IOException {
		FreeListingPage freeListPage=new FreeListingPage(driver);
		freeListPage.captureErrorMessage();
	}
	
	//Feature-3
	@Given("user is on the justdial aplication")
	public void user_is_on_the_justdial_aplication() {
		GymServicePage gymServicePage=new GymServicePage(driver);
		gymServicePage.openApp();
	}

	@When("user click on gym icon")
	public void user_click_on_gym_icon() throws IOException {
		GymServicePage gymServicePage=new GymServicePage(driver);
		gymServicePage.clickGymMenu();
	}

	@Then("user need to get gym submenu")
	public void user_need_to_get_gym_submenu() throws InterruptedException {
		GymServicePage gymServicePage=new GymServicePage(driver);
		gymServicePage.getSubmenu();
		List<WebElement> submenu =gymServicePage.gymSubMenu;
		for(int i=0;i<9;i++) {
			System.out.println(submenu.get(i).getText());
		}
	}
	
	@Then("user need close browser")
	public void user_need_close_browser() {
	   driver.quit();
	}

	
}

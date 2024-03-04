package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjectModels.CarWashServicePage;
import pageObjectModels.FreeListingPage;
import pageObjectModels.GymServicePage;
import pageObjectModels.HomePage;
import utils.ExcelUtils;

public class TestCases extends BaseTest{
	//Declaring Variables
	public List<String> gymSubMenu=new ArrayList<String>();
	public static String path;
	
	//TestCase-1
	@Test(priority=0)
	void closeWelcomePopUp() throws IOException, InterruptedException {
		logger.info("Starting Running Test Cases");
		HomePage homePage=new HomePage(driver);
		path=homePage.closePopUp(); //Closing the Pop Up
		logger.info("Closing popup");
	}
	
	//TestCase-2
	@Test(priority=1)
	void locationInput() throws IOException, InterruptedException{
		HomePage homePage=new HomePage(driver);
		path=homePage.locationSearch(); //Giving Location Name in Search Input
		logger.info("Giving location input");
	}
	
	//TestCase-3
	@Test(priority=2)
	void searchCarWashService() throws IOException, InterruptedException {
		HomePage homePage=new HomePage(driver);
		path=homePage.serviceSearch(); //Giving Service Name in Search Input
		logger.info("Giving service input");
	}
	
	//TestCase-4
	@Test(priority=3)
	void getCarWashingServiceandPhone() throws InterruptedException, IOException {
		CarWashServicePage carWashServicePage=new CarWashServicePage(driver);
		path=carWashServicePage.getCarWashingServiceandPhone();//Storing and Printing Car wash Services List
		logger.info("Getting top 5 car wash services");	
	}
	
	//TestCase-5
	@Test(priority=4)
	void freeListingValidation() throws IOException, InterruptedException {
		FreeListingPage freeListPage=new FreeListingPage(driver);
		HomePage homePage=new HomePage(driver);
		homePage.openFreeListing();//Opening FreeListing Page
		logger.info("Freelisting page is opened");
		freeListPage.enterWrongPhn(); //Entering Wrong Phone Number
		logger.info("Enter the wrong Number");
		path=freeListPage.captureErrorMessage(); //Printing Error Message
		logger.info("Capturing error message");
		Assert.assertEquals(freeListPage.errorMessage.getText(), "This Number has been Blocked for Signing Up");
	}
	
	//TestCase-6
	@Test(priority=5)
	void getGymSubMenuList() throws IOException, InterruptedException {
		GymServicePage gymServicePage=new GymServicePage(driver);
		gymServicePage.openApp(); //Navigating to Home Page
		logger.info("Opening home page");
		Assert.assertEquals(gymServicePage.gymIcon.getText(), "Gym");
		path=gymServicePage.clickGymMenu(); //Clicking Gym Icon
		logger.info("Clicking Gym Menu Icon");
		gymServicePage.getSubmenu();
		List<WebElement> submenu =gymServicePage.gymSubMenu;
		
		//Iterating and Printing Gym Sub menu
		for(int i=0;i<9;i++) {
			System.out.println(submenu.get(i).getText());
			gymSubMenu.add(submenu.get(i).getText());
		}
		logger.info("Getting the submenu of gym");
		
		//Storing Data in Excel Sheet
		ExcelUtils.writeExcel(CarWashServicePage.carWashsingServices,CarWashServicePage.phoneNumbers,CarWashServicePage.customersRating,CarWashServicePage.customersVotes,FreeListingPage.errorMessageText,gymSubMenu);
		
	}
	
}

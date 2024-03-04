package baseTest;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	public static WebDriver driver;
	public Logger logger;
	Properties p;
	
	//BeforeClass -->It will execute before test cases class
	@BeforeClass
	@Parameters({"os","browser"})
	public void openUrl(String os , String browser) throws IOException{
		//Initializing Logger
		logger=LogManager.getLogger(this.getClass());
		
		//Properties file
		FileReader file = new FileReader(".//src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabalities = new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabalities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac")) {
				capabalities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("no matching os .....");
				return;
			}
			//browser
			if(browser.equalsIgnoreCase("chrome")) {
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--disable-blink-features=AutomationControlled");
				option.addArguments("--disable-notifications");
				capabalities.setBrowserName("chrome");
				capabalities.setCapability(ChromeOptions.CAPABILITY, option);
			}
			else if(browser.equalsIgnoreCase("edge")) {
				EdgeOptions option = new EdgeOptions();
				option.addArguments("--disable-blink-features=AutomationControlled");
				option.addArguments("--disable-notifications");
				capabalities.setBrowserName("MicrosoftEdge");
				capabalities.setCapability(EdgeOptions.CAPABILITY, option);
			}
			else {
				System.out.println("no matching browser .....");
				return;
			}
			 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub") , capabalities);
		}
		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			
			//driver=new ChromeDriver(option);
			if(browser.equalsIgnoreCase("chrome")) {
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--disable-blink-features=AutomationControlled");
				option.addArguments("--disable-notifications");
				driver = new ChromeDriver(option);
				logger.info("Chrome browser opened successfully");
			}
			else if(browser.equalsIgnoreCase("edge")){
				EdgeOptions option = new EdgeOptions();
				option.addArguments("--disable-blink-features=AutomationControlled");
				option.addArguments("--disable-notifications");
				driver = new EdgeDriver(option);
				logger.info("Edge browser opened successfully");
			}
			else {
				System.out.println("no matching browser......");
				logger.info("no matching browser......");
				return;
			}
		}

		driver.get(p.getProperty("appUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35));
 
	}
	
	//AfterClass -->It will execute after the test cases classes
	@AfterClass
	public void closeBowser() {
		 driver.quit();
		 logger.info("Closing the browser");
	 }
}







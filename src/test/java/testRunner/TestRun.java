package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

//Cucumber Options
@CucumberOptions(features= {".//featureFiles/carwashServices.feature",
							".//featureFiles/freeListing.feature",
							".//featureFiles/gymSubmenu.feature"},
				glue = "stepDefinitions",

				plugin= {"pretty","html:reports//cucumberReport.html"})
public class TestRun {
	
}

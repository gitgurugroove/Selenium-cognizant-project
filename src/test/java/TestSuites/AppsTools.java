package TestSuites;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Base;
import Pages.StocksToolsApps;

public class AppsTools extends Base{
	
	StocksToolsApps ha= new StocksToolsApps();
	@BeforeTest
	public void invokeBrowser() {
		logger = report.createTest("Executing Test Cases");

		ha.invokeBrowser();
		reportPass("Browser is Invoked");
	}

//	@Test(priority = 1)
//	public void testCases() throws Exception {
//
////		ha.getGoogleData();
//	}
	@Test(priority = 1)
	public void opurl() {
		ha.openURL();
	}
	@Test(priority = 2)
	public void lgn() {
		ha.login();
	}
//	@Test(priority = 4)
//	public void stock() {
////		ha.getCogStock();
//	}
	@Test(priority = 3)
	public void tools() throws IOException, InterruptedException {
		ha.getAppsTools();
		reportPass("All steps passed successfuly");
	}
	@AfterTest
	public void closeBrowser() throws IOException {
		reportPass("Browser is closed successfuly");
		ha.endReport();
		ha.CloseBrowser();
	}
}

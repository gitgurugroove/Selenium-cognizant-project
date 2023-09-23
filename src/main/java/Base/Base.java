package Base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentReportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	JavascriptExecutor js;
	public static WebDriverWait wait;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	public File file;
	public XSSFWorkbook workbook;
	public XSSFSheet sh;
	public FileOutputStream fos;

	// To call different browsers
	public void invokeBrowser() {
		prop = new Properties();

		try {
			prop.load(new FileInputStream("src/main/java/Config/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// To Open Chrome Browser
		if (prop.getProperty("browserName").matches("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		// To Open Firefox Browser
		if (prop.getProperty("browserName").matches("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		// To maximize the Browser Window
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	// To open the Main Page URL
	public void openURL() {
		driver.get(prop.getProperty("websiteURLKey"));
	}

	// Function to show the failed test cases in the report
	public void reportFail(String report) {
		logger.log(Status.FAIL, report);
	}

	// Function to show the passed test cases in the report
	public void reportPass(String report) {
		logger.log(Status.PASS, report);
	}

	// Function to take Screenshot of screen
	public void Screenshot() throws IOException, InterruptedException {
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		String fileName=null;
		File destFile = new File(System.getProperty("user.dir")+ "/Screenshot/" + fileName + ".png");
		Files.copy(srcFile, destFile);
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		FileUtils.copyFile(file, new File("./screenshots/screenshot"+".jpg"));
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		Thread.sleep(1000);
	}
	
	public static void Screenshot( WebDriver driver) {

	      // TODO Auto-generated method stub
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

	     //save the screenshot to the "screenshot" folder
			try {
				FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\screenshot\\"+System.currentTimeMillis()+".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void wait(int sec, By locator) {
		wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// To input all data to the report
	public void endReport() {
		report.flush();
	}

	public void CloseBrowser() throws IOException {
		driver.quit();
	}

}

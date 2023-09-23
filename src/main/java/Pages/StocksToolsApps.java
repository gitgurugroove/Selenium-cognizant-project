package Pages;

import java.io.File;
//import java.io.FileInputStream;
//import org.apache.commons.io.FileUtils;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Base;

public class StocksToolsApps extends Base{
	String gstock,cstock,Value;
	By email=By.xpath("//input[@type='email']");
	By next=By.xpath("//input[@type='submit']");
	By pass=By.name("passwd");
	By acc=By.id("user-name");
	By yes=By.xpath("//input[@value='Yes']");
	By search=By.xpath("//input[@title='Search']");
	By s1=By.xpath("//span[text()='Cognizant']");
	By gs=By.xpath("//*[@id=\"kp-wp-tab-overview\"]/div[1]/div/div/div/div/div/div[2]/div/div/span[2]/span[3]");
	By cs=By.xpath("//div[@class=\"stock-ticker-header__price\"]/span");
	By apps=By.xpath("//span[@class=\"icomoon-windows8\"]");
	By app1=By.xpath("//*[@id=\"mCSB_2_container\"]/div[1]");
	By app2=By.xpath("//*[@id=\"header-menu-item-AppsAndTools\"]/apps-and-tools/div/div/div/div/tabset/div/div/button[2]");
	By app3=By.xpath("//*[@id=\"header-menu-item-AppsAndTools\"]/apps-and-tools/div/div/div/div/tabset/div/div/button[3]");
	By name=By.xpath("//div/a/span[2]");
	
	File fos=new File("C:\\Users\\DELL\\Desktop\\Cognizant_Documents\\After onBoarding\\Main Project\\2129971_AllAppsAndTools _main project\\AllAppsAndTools\\MainProject\\Excel\\new.xls");
//	File fos=new File("C:\\Users\\ABC\\OneDrive\\Desktop\\Web D\\eclipse\\Downloads\\AllAppsAndTools\\MainProject\\Excel\\new.xls");
	XSSFWorkbook wb=new XSSFWorkbook();
	XSSFSheet sh=wb.createSheet();
	

	public void login() {
		logger = report.createTest("Login into Becognizant.");
		try {
			/*driver.get("https://be.cognizant.com");
		Thread.sleep(5000);
		wait(20,next);
//		driver.findElement(email).sendKeys(prop.getProperty("email"));
		driver.findElement(email).sendKeys("");
		driver.findElement(next).click();
		wait(40,pass);
//		driver.findElement(pass).sendKeys(prop.getProperty("password"));
		driver.findElement(pass).sendKeys("");
		driver.findElement(next).click();
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//div[contains(text(),'Text +XX')]")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Call +XX')]")).click();
        Thread.sleep(3000);
		reportPass("Email and Password Verified sucessfully");
		wait(120,yes);
		driver.findElement(yes).click();*/
			wait(20,email);
			driver.findElement(email).sendKeys(prop.getProperty("email"));
			driver.findElement(next).click();
			wait(20,pass);
			driver.findElement(pass).sendKeys(prop.getProperty("password"));
			driver.findElement(next).click();
			Thread.sleep(1000);
			reportPass("Email and Password Verified sucessfully");
			wait(120,yes);
			driver.findElement(yes).click();
		//Verify Title
		if (driver.getTitle().contains("Be.Cognizant"))
			// Pass
			System.out.println("Page title contains Be.Cognizant");
		else
			// Fail
			System.out.println("Page title doesn't contains Be.Cognizant");
		String name=driver.findElement(acc).getText();
		System.out.println("The name for the Acoount is: "+name);
		Screenshot(driver);
		reportPass("Be.Cognizant Page is reached sucessfully");
		
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	

	public void getAppsTools() throws IOException, InterruptedException {
		
		int k=0;
		int v=0;
		logger = report.createTest("Get Apps under Different App search");
		try {
			String username = driver.findElement(By.xpath("//p[@id='user-name']")).getText();
			System.out.println(username);

			String usertest1= username.split(",")[1].trim().split("\\s")[0];
			System.out.println(usertest1);
			char firstlet=usertest1.charAt(0);
			int n=usertest1.length();
			char lastlet=usertest1.charAt(n-1);
			char lastletcaps=Character.toUpperCase(lastlet);
			System.out.println(firstlet);
			System.out.println(lastletcaps);
			
			driver.findElement(By.linkText("All Apps & Tools")).click();
			
			driver.findElement(By.xpath("//button[normalize-space()='"+firstlet+"']")).click();
			
			java.util.List<WebElement>element=driver.findElements(By.xpath("//generic-search-results/item-template"));
			System.out.println(element.size());
			
			//Excel
			String x="APPS with First letter and Last letter of First name :-";
			sh.createRow(k++).createCell(0).setCellValue(x);
			sh.autoSizeColumn(0);
			
			for( int z=1;z<=element.size();z++)
			{
			
			
			String a = driver.findElement(By.xpath("//item-template[@data-data='item']["+z+"]/div/div/a/span[2]")).getText();
			sh.createRow(k++).createCell(0).setCellValue(a);
			sh.autoSizeColumn(0);
			
			try {
				FileOutputStream fo=new FileOutputStream(fos);
				wb.write(fo);
				//wb.close();
				}
				catch(Exception e){
				e.printStackTrace();
				}
			}
			
			for( int i=1;i<=element.size();i++) {
				
				String data=driver.findElement(By.xpath("//item-template[@data-data='item']["+i+"]/div/div/a/span[2]")).getText();
				System.out.println(data);
				
			}
			Screenshot(driver);
			
			
			
			
			driver.findElement(By.xpath("//button[normalize-space()='"+lastletcaps+"']")).click();
			
			java.util.List<WebElement>element2=driver.findElements(By.xpath("//generic-search-results/item-template"));
			System.out.println(element2.size());
			
			//Excel I
			
			String a="APPS starting with Last letter of First name :-";
			sh.createRow(1+k++).createCell(0).setCellValue(a);
			sh.autoSizeColumn(0);
			
			for( int z=1;z<=element2.size();z++)
			{
			String b=driver.findElement(By.xpath("//item-template[@data-data='item']["+z+"]/div/div/a/span[2]")).getText();
			sh.createRow(k++).createCell(0).setCellValue(b);
			sh.autoSizeColumn(0);
			
			try {
				FileOutputStream fo=new FileOutputStream(fos);
				wb.write(fo);
				//wb.close();
				}
				catch(Exception e){
				e.printStackTrace();
				}
			}
			
			
			for( int j=1;j<=element2.size();j++) {
				
				String data2=driver.findElement(By.xpath("//item-template[@data-data='item']["+j+"]/div/div/a/span[2]")).getText();
				System.out.println(data2);
				
			}
			Screenshot(driver);
			
			driver.quit();
		
	  } catch (Exception e) {
		reportFail(e.getMessage());
	  }
	}
	}


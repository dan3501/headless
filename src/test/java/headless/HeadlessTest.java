package headless;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.io.*;

public class HeadlessTest {
	Logger log = LoggerFactory.getLogger(HeadlessTest.class);
	 
	 @Test
	 public void HeadlessChromeDriverTest() throws IOException {
		 System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
		 ChromeOptions chromeOptions = new ChromeOptions();
		 chromeOptions.addArguments("--headless");
		 
		 WebDriver driver = new ChromeDriver(chromeOptions);
		 
		 driver.navigate().to("https://en.wikipedia.org/wiki/UnitedHealth_Group/");
		 driver.navigate().to("http://optum.com/");
		 String pageTitle = driver.getTitle();
		 System.out.println(pageTitle);
		 log.info("Page opened: {}", pageTitle);
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//copying the file into /screenshots directory
		 String out = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss'.png'").format(new Date());
		 Date tm =  new java.util.Date();
		 System.out.println(tm);
		 FileUtils.copyFile(scrFile, new File("output/screenshots/"+out));
		 
		 Assert.assertTrue(pageTitle.contains("Health Services"));
		 log.info("Quitting driver");
		 driver.quit();
	 }
}

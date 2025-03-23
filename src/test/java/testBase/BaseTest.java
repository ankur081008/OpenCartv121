package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	public static WebDriver driver;
    public Logger logger;
    public String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
	public Properties prop = null;
	
	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
	public void setUp() {
		try {
			FileInputStream fis = new FileInputStream(filePath);
		 prop = new Properties();
		prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger = LogManager.getLogger(this.getClass());
		
		// environment execution setup
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.setCapability("browserVersion", "134");
			 * options.setCapability("platformName", "Windows");
			 */
			DesiredCapabilities options = new DesiredCapabilities();
			options.setPlatform(Platform.WIN11);
			

			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();
			} else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if(prop.getProperty("browser").equalsIgnoreCase("Edge")) {
				driver = new EdgeDriver();
			} else {
				driver = new ChromeDriver();
			}
		} 
		
		
		 if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {

				if (prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
					driver = new ChromeDriver();
				} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
					driver = new FirefoxDriver();
				} else if (prop.getProperty("browser").equalsIgnoreCase("Edge")) {
					driver = new EdgeDriver();
				} else {
					driver = new ChromeDriver();
				}
			}	
			
		
		driver.get(prop.getProperty("baseURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().deleteAllCookies();
		}
	  
		
	
	@AfterClass (groups= {"Sanity","Regression","Master","DataDriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String randomAlphaNumeric() {
		return RandomStringUtils.randomAlphabetic(5)+RandomStringUtils.randomNumeric(5);
	}
	
	public String captureScreen(String testCaseName) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.mm.ss").format(new Date());
		TakesScreenshot takesScreenshot =(TakesScreenshot)driver;
		File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		try {
   			FileUtils.copyFile(src, destination);
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   			return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }
	
	
}

package com.silbury.publicare.factories;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.silbury.publicare.common.Constants;


import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static WebDriver driver = null;
    private static Constants.DriverName driverName;
    static String driverPath = Constants.RESOURCES_ROOT + "drivers" + File.separator+"chrome"+File.separator + "chromedriver.exe" ;
    
    public static WebDriver getDriverByName(String webDriverName, boolean headless)
    {
        driverName = Constants.DriverName.valueOf(webDriverName.toUpperCase());
      
        switch(driverName)
        {
            case FIREFOX:
                setFirefoxDriver();
                break;
            case CHROME:
                setChromeDriver(headless);
                break;
                
            case EDGE:
                setEdgeDriver();
                break;
             
            default:
                return driver;
        }
        return driver;
    }
    
 
    private static void setEdgeDriver() {
    	WebDriverManager.edgedriver().setup();			
		driver = new EdgeDriver();
		driver.get(Constants.DefaultUrl.url);
	}


	private static void setFirefoxDriver() {
    	WebDriverManager.firefoxdriver().setup();			
		driver = new FirefoxDriver();
		driver.get(Constants.DefaultUrl.url);
	}

	private static void setChromeDriver(boolean headless)
    {
   	    logger.info("driver path :: "+driverPath);
   	    System.setProperty("webdriver.chrome.driver", driverPath);	
   	    // uses bonigarcia set up 
   	    //WebDriverManager.chromedriver().setup();
    	ChromeOptions caps =  new ChromeOptions();
    	caps.addArguments("window-size=1400,800");
    	if(headless)
    		caps.addArguments("headless");
     
        
		driver = new ChromeDriver(caps);
		driver.get(Constants.DefaultUrl.url);
    }    
}

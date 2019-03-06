package com.silbury.publicare.factories;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.silbury.publicare.common.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverFactory {

    private static WebDriver driver = null;
    private static Constants.DriverName driverName;

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
    	//System.setProperty("webdriver.chrome.driver", driverPath);	
		WebDriverManager.chromedriver().setup();
    	ChromeOptions caps =  new ChromeOptions();
    	caps.addArguments("window-size=1400,800");
    	if(headless)
    		caps.addArguments("headless");
     
        // uses bonigarcia set up 
		driver = new ChromeDriver(caps);
		driver.get(Constants.DefaultUrl.url);
    }    
}

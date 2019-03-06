package com.silbury.publicare.common;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.silbury.publicare.factories.DriverFactory;

import net.thucydides.core.webdriver.DriverSource;

public class CustomWebDriver implements DriverSource
{
    private static final Logger logger = LoggerFactory.getLogger(CustomWebDriver.class);

    @Override
    public WebDriver newDriver()
    {
        String driver_type = System.getProperty("driver_type");
 
        String driver_name = System.getProperty("driver_name");
        
        logger.info("Driver Type :: "+driver_type);
        logger.info("Driver Name :: "+driver_name);
 
        if(driver_name == null || driver_type == null)
        {
            logger.error("System Properties 'driver_type' or 'driver_name' is not set\n" +
                         "driver_type should be 'local' or 'remote'\n" +
                         "driver_name should be the type of browser\n");
            return null;
        }
        Constants.DriverType driverType = Constants.DriverType.valueOf(driver_type.toUpperCase());
 

        switch(driverType)
        {
            case LOCAL:
                return DriverFactory.getDriverByName(driver_name, false);
            case REMOTE:
                return DriverFactory.getDriverByName(driver_name, true);
            default:
                logger.error("System Properties 'driver_type' is not set properly" +
                             "driver_type should be 'local' or 'remote'");
                return null;
        }
    }

    @Override
    public boolean takesScreenshots()
    {
        return true;
    }
   
}

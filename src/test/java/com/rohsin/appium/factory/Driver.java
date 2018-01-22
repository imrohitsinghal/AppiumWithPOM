package com.rohsin.appium.factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.UUID;

public class Driver {

    private static AppiumDriver driver;
    private static AppiumDriverLocalService service;

    /**
     * @return - Returns driver instance
     */
    public static AppiumDriver getAndroidDriver() {
        if (driver != null) {
            return driver;
        } else {
            service = AppiumDriverLocalService
                    .buildService(new AppiumServiceBuilder()
                            .withIPAddress("127.0.0.1").usingPort(4723));
            service.start();
            if (service == null) {
                throw new AppiumServerHasNotBeenStartedLocallyException("SERVICE NOT STARTED");
            }
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Mi_4i");
            desiredCapabilities.setCapability(MobileCapabilityType.APP,
                    new File("").getAbsoluteFile() + File.separator + "apk" + File.separator
                            + "eBay_ebay_mobile.apk");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                    "com.ebay.mobile.activities.MainActivity");
            desiredCapabilities
                    .setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.ebay.mobile");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, false);
//            desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
            desiredCapabilities.setCapability("newCommandTimeout", 99999);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            try {
                driver = new AndroidDriver(service.getUrl(), desiredCapabilities);
                return driver;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @return - Returns relative path with result dir
     */
    public static String getScreenShot() {
        try {
            System.out.println("Adding screen shot");
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            String path = "screenshots/" + UUID.randomUUID() + "" + ".png";
            System.out.println(path);
            FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/reports/" + path));
            return path;
        } catch (Exception e) {
            System.out.println("Screen shot failed");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Kills driver session
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

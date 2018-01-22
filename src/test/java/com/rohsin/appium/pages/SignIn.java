package com.rohsin.appium.pages;

import com.rohsin.appium.logger.LogUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SignIn extends BasePage {

    public SignIn(AppiumDriver driver) {
        super(driver);
        LogUtils.INFO("Instantiated " + SignIn.class.getName());
    }

    private String emailIdLocator = "edit_text_username";
    private String passLocator = "edit_text_password";
    private String cancelButtonLocator = "button_close";


    public SignIn enterUsername(String emailId) {
        waitForElementToBePresent(By.id(emailIdLocator), 5);
        LogUtils.INFO("*** Enter Email Address ***" + emailId);
        click(By.id(emailIdLocator));
        driver.findElement(By.id(emailIdLocator)).sendKeys(emailId);
        return this;
    }

    public ProductDetails cancel() {
        waitForElementToBePresent(By.id(cancelButtonLocator), 5);
        LogUtils.INFO("*** CLOSING SIGN IN PAGE ***");
        click(By.id(cancelButtonLocator));
        return new ProductDetails(driver);
    }

}

package com.mobile.demo.project.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApkManager {

    private static final Logger log = LoggerFactory.getLogger(ApkManager.class);

    @Value("${app.package}")
    private String appPackage;

    @Autowired
    private AndroidDriver androidDriver;

    private final Duration TIMEOUT = Duration.ofSeconds(15);

    // Element locators
    private final By btnGetStarted = By.id("com.vitalpos.posmobile:id/btn_lets_go");
    private final By inputAccountId = By.id("com.vitalpos.posmobile:id/fld_account_id");
    private final By inputUsername = By.id("com.vitalpos.posmobile:id/fld_username");
    private final By inputPassword = By.id("com.vitalpos.posmobile:id/fld_password");
    private final By btnLogin = By.id("com.vitalpos.posmobile:id/btn_log_in");
    private final By titleLocation = By.id("com.vitalpos.posmobile:id/select_item_title");

    private WebDriverWait getWait() {
        return new WebDriverWait(androidDriver, TIMEOUT);
    }

    public boolean isApkInstalled() {
        boolean installed = androidDriver.isAppInstalled(appPackage);
        log.info("APK [{}] installed: {}", appPackage, installed);
        return installed;
    }

    public void removeApk() {
        androidDriver.removeApp(appPackage);
        log.info("APK [{}] removed.", appPackage);
    }

    public void clickGetStarted() {
        getWait().until(ExpectedConditions.elementToBeClickable(btnGetStarted)).click();
        log.info("Clicked on GET STARTED button.");
    }

    public void insertCredentials(String accountNumber, String username, String password) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(inputAccountId));
        androidDriver.findElement(inputAccountId).sendKeys(accountNumber);
        androidDriver.findElement(inputUsername).sendKeys(username);
        androidDriver.findElement(inputPassword).sendKeys(password);
        log.info("Inserted credentials: accountNumber={}, username={}", accountNumber, username);
    }

    public void clickLogin() {
        getWait().until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
        log.info("Clicked on LOGIN button.");
    }

    public String getLocationTitle() {
        String title = getWait().until(ExpectedConditions.visibilityOfElementLocated(titleLocation))
                .getText();
        log.info("Location screen title: {}", title);
        return title;
    }
}

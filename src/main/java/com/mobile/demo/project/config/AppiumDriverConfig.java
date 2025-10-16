package com.mobile.demo.project.config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@Configuration
public class AppiumDriverConfig {

    @Value("${appium.server.url}")
    private String appiumServerUrl;

    @Value("${device.name}")
    private String deviceName;

    @Value("${platform.version}")
    private String platformVersion;

    @Value("${app.path}")
    private String appPath;

    @Value("${default.timeout:30}")
    private int timeout;

    @Bean(destroyMethod = "quit")
    public AndroidDriver androidDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(deviceName);
        options.setPlatformName("Android");
        options.setPlatformVersion(platformVersion);
        options.setAutomationName("UiAutomator2");
        options.setAutoGrantPermissions(true);
        options.setNoReset(false);

        File appFile = new File("src/main/resources/" + appPath);
        if (!appFile.exists()) {
            throw new IllegalStateException("APK not found at: " + appFile.getAbsolutePath());
        }

        options.setApp(appFile.getAbsolutePath());

        URL serverUrl = URI.create(appiumServerUrl).toURL();

        return new AndroidDriver(serverUrl, options);
    }
}

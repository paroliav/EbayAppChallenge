package au.com.wipro.ebay.utils;

import au.com.wipro.ebay.beans.AppConfig;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumHelper {

    PropertiesHelper propertiesHelper = new PropertiesHelper();
    AppConfig config = propertiesHelper.getProperties("app.properties", AppConfig.class);
    private DesiredCapabilities capabilities;
    private String APP_PACKAGE = config.getEbay().getAppPackage();
    private String APP_ACTIVITY = config.getEbay().getAppActivity();
    private String NODE_PATH = config.getAppium().getNodePath();
    private String APPIUM_PATH = config.getAppium().getJsPath();
    private String APPIUM_HOST = config.getAppium().getHost();

    private AppiumDriver driver;
    private AppiumDriverLocalService appiumService;
    private String appiumServiceUrl;

    private DesiredCapabilities getCapabilities() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, false);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("deviceName", "test");
        capabilities.setCapability("newCommandTimeout", 1700);
        capabilities.setCapability("noReset", true);
        return capabilities;
    }

    public AppiumDriver getDriver() throws MalformedURLException {
        int port = getPort();

        driver = new AndroidDriver(new URL(String.format("http://%s:%d/wd/hub", APPIUM_HOST, port)),
                getCapabilities());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    private int getPort() {
        startService();
        appiumServiceUrl = appiumService.getUrl().toString();
        System.out.println("Appium Service Address : - " + appiumServiceUrl);
        return appiumService.getUrl().getPort();
    }

    private void startService() {
        appiumService = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder().usingAnyFreePort()
                        .usingDriverExecutable(new File(NODE_PATH))
                        .withAppiumJS(new File(APPIUM_PATH)));
        appiumService.start();
    }

    public void stopAppiumService() {
        appiumService.stop();
    }


}

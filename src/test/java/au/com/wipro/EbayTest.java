package au.com.wipro;

import au.com.wipro.ebay.actions.AndroidActions;
import au.com.wipro.ebay.pages.Home;
import au.com.wipro.ebay.utils.AppiumHelper;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class EbayTest {
    AppiumDriver driver;
    AppiumHelper helper;
    AndroidActions actions;



    @BeforeTest
    public void setup() throws MalformedURLException {
        helper = new AppiumHelper();
        driver = helper.getDriver();
        actions = new AndroidActions(driver);
    }

    @Test
    public void startApp() {
        Home home = new Home(actions);
        home.openAppMenu();
        home.signIn();
    }

    @AfterTest
    public void tearDown() {
//        driver.quit();
        helper.stopAppiumService();
    }

}

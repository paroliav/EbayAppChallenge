package au.com.wipro.ebay.actions;

import au.com.wipro.ebay.utils.ConsoleLogPrintHelper;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AndroidActions {

    AppiumDriver driver;
    private ConsoleLogPrintHelper logHelper = new ConsoleLogPrintHelper(AndroidActions.class);

    public AndroidActions(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(By by){
        try {
            WebElement element = driver.findElement(by);
            logHelper.print("Element found by:"+by.toString());
            return element;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public List<WebElement> getElements(By by){
        try {
        List<WebElement> elements = driver.findElements(by);
            logHelper.print("Element found by:"+by.toString());
            return elements;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public void tap(By by){
        WebElement element = getElement(by);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try	{
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            if(element != null){
                element.click();
            }
        } catch (Exception ex){
            logHelper.print(by.toString()+" was not clickable");
        }
    }

    public void input(int index, String value) {
//        WebElement inputField = getInputFieldWithIndex(index);
//        if(inputField != null){
//            inputField.clear();
//            inputField.sendKeys(value);
//        }
        driver.getKeyboard().pressKey(value);
        driver.getKeyboard().pressKey(Keys.TAB);
    }

    public WebElement getInputFieldWithIndex(int index) {
        List<WebElement> elements = getElements(By.className("input"));
        if(elements != null){
            try{
                return elements.get(index);
            } catch(ArrayIndexOutOfBoundsException e){
                return null;
            }
        }
        return null;
    }
}

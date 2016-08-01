package com.framework.qa.webelementcore.elementbase.behavior;

import com.framework.qa.utils.exception.*;
import com.framework.qa.utils.exception.TimeoutException;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Clickable.java -  Interface type of click action related class
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_23_2015
 * @since 04/23/2015.
 */
public class Clickable {

    public void click(RemoteWebDriver driver, By locator) throws ApplicationException, ScriptException {
        try {
            WebElement element;
            element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (WebDriverException e){
            throw new ScriptException("Element is not clickable at current point");
        }
    }

}

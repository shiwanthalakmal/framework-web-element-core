package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.webelementcore.elementbase.behavior.Clickable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;

/**
 * Represent HTML <img>
 *
 * @author Shiwantha Lakmal
 */
public abstract class BaseImage extends BaseElement {
    private WebElement element;
    private RemoteWebDriver driver;
    private By locator;
    private Clickable mouse;
    final static Logger log = Logger.getLogger(BaseImage.class);

    public BaseImage(RemoteWebDriver driver, By locator) {
        super(driver, locator);
        this.driver = driver;
        this.locator = locator;
        this.mouse = new Clickable();
    }

    /**
     * Performs a click in the middle of the element.
     */

    public void click() throws ApplicationException, ScriptException {
        mouse.click(driver, locator);
    }

    /**
     * Performs a click on the element if it exists.
     */

    public void clickIfExists() {
        try {

            waitTillElementVisible(5000);
            mouse.click(driver, locator);

        } catch (Exception e) {
            log.error("exception :", e);
        }
    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verifies whether the element exists.
     *
     * @return <code>true</code> if the element is found in the current context.
     */
    /*@Override
    public boolean verifyElementExists() {
        return !driver.findElements(locator).isEmpty();
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the element is displayed in current context.
     *
     * @return <CODE>true</CODE> the element is displayed in current context.
     */
    /*@Override
    public boolean isDisplayed() {
        try {
            this.element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            LOGGER.error("exception :", e);
            return false;
        }
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the element is displayed in current context.
     *
     * @return <CODE>true</CODE> the element is displayed in current context.
     */
    /*@Override
    public boolean isNotDisplayed() {
        return !(isDisplayed());
    }*/

}

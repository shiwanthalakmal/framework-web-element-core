package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.webelementcore.elementbase.behavior.Clickable;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

/**
 * Represent HTML image buttom
 *
 * @author Shiwantha Lakmal
 */
public abstract class BaseImageButton extends BaseElement {
    private WebElement element;
    private RemoteWebDriver driver;
    private By locator;
    private Clickable mouse;
    final static Logger log = Logger.getLogger(BaseImageButton.class);

    public BaseImageButton(RemoteWebDriver driver, By locator) {
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
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/

    /**
     * @ret Verify the element is disabled. return <CODE>true</CODE> the element
     * is disabled.
     */
    @Override
    public boolean isDisabled() {
        this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (!element.isEnabled()) {
            return true;
        } else if (element.getAttribute("disabled") != null) {
            return true;
        } else if (element.getAttribute("class").contains("rtbDisabled")) {
            return true;
        }
        return false;
    }

    /**
     * @ret Verify if the element is enabled. return <CODE>true</CODE> the
     * element is enabled.
     */
    @Override
    public boolean isEnabled() {
        this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        if (element.getAttribute("class").contains("rtbDisabled")) {
            return false;
        }

        return element.isEnabled();
    }

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

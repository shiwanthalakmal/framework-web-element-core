package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.webelementcore.elementbase.behavior.Clickable;
import com.framework.qa.webelementcore.elementbase.behavior.Tickable;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;


/**
 * BaseCheckBox.java - Represent based check-box behaviours
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_23_2015
 * @since 04/23/2015.
 */
public abstract class BaseCheckBox extends BaseElement {
    private WebElement element;
    private RemoteWebDriver driver;
    private By locator;
    private Clickable mouse;
    private Tickable tickable;

    final static Logger log = Logger.getLogger(BaseCheckBox.class);

    public BaseCheckBox(RemoteWebDriver driver, By locator) {
        super(driver, locator);
        mouse = new Clickable();
        this.driver = driver;
        this.locator = locator;
        tickable = new Tickable();
    }

    /**
     * Performs a click in the middle of the element.
     */

    public void click() throws ApplicationException, ScriptException {
        mouse.click(driver, locator);
    }

    /**
     * Ticks the Checkbox if it is not selected.
     */

    public void check() throws ScriptException, ApplicationException {
        tickable.check(driver, locator);
    }

    /**
     * Unticks the Checkbox if it already selected.
     */

    public void unCheck() throws ScriptException, ApplicationException {
        tickable.unCheck(driver, locator);
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

    public void clickIfExists() throws ApplicationException, ScriptException {
        Boolean isPresent = !driver.findElements(locator).isEmpty();
        if (isPresent) {
            mouse.click(driver, locator);
        }

    }

    /**
     * @ret Verify the element is disabled. return <CODE>true</CODE> the element
     * is disabled.
     */
    @Override
    public boolean isDisabled() {
        boolean status = false;
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (!element.isEnabled()) {
                status = true;
            } else if (element.getAttribute("disabled") != null) {
                status = true;
            } else if (element.getAttribute("class").contains("rtbDisabled")) {
                status = true;
            }
        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        }catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        }catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        }catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }catch (NoSuchElementException e){
            throw new ApplicationException("No such element with expression : " +locator);
        }finally {
            return status;
        }
    }

    /**
     * @ret Verify if the element is enabled. return <CODE>true</CODE> the
     * element is enabled.
     */
    @Override
    public boolean isEnabled() throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            if (element.getAttribute("class").contains("rtbDisabled")) {
                return false;
            }
            return element.isEnabled();
        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        }catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        }catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        }catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }catch (NoSuchElementException e){
            throw new ApplicationException("No such element with expression : " +locator);
        }

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
            LOGGER.error("exception:", e);
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

    /**
     * Verify if the element is checked.
     *
     * @return <CODE>true</CODE> the element is checked.
     */

    public boolean verifyChecked() throws ScriptException, ApplicationException {
        return tickable.verifyChecked(driver, locator);
    }

    /**
     * Verify if the element is not checked.
     *
     * @return <CODE>true</CODE> the element is not checked.
     */

    public boolean verifyUnchecked() throws ScriptException, ApplicationException {
        return tickable.verifyUnchecked(driver, locator);
    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/
}

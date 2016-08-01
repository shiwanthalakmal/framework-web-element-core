package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.behavior.Clickable;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

/**
 * BaseButton.java - Represent based button behaviours
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_23_2015
 * @since 04/23/2015.
 */
public abstract class BaseButton extends BaseElement {

    private WebElement element;
    private RemoteWebDriver driver;
    private By locator;
    private Clickable mouse;
    final static Logger log = Logger.getLogger(BaseButton.class);

    /**
     * Constructor
     *
     * @param driver
     * @param locator
     */
    public BaseButton(RemoteWebDriver driver, By locator) {
        super(driver, locator);
        this.mouse = new Clickable();
        this.driver = driver;
        this.locator = locator;

    }

    /**
     * Generic basse  click event
     *
     * @throws ApplicationException
     * @throws ScriptException
     */
    public void click() throws ApplicationException, ScriptException {
        mouse.click(driver, locator);

    }

    /**
     * Performs click event if element is exist
     */
    public void clickIfExists() {
        try {
            waitTillElementVisible(5000);
            mouse.click(driver, locator);
        } catch (Exception e) {
            log.error("exception :", e);
        }

    }

    /**
     * Performs a click on the button and exit the modal dialog.
     *
     * @throws ApplicationException
     * @throws ScriptException
     */
    public void clickButtonAndExitModalDialog() throws ApplicationException, ScriptException {
        mouse.click(driver, locator);
        WorkingMemory wMem = WorkingMemory.getInstance();
        String parent = wMem.getMemory("MODAL_DIALOG_PARENT");
        driver.switchTo().window(parent);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("exception :", e);
        }
    }

    /**
     * Verify the element is disabled.
     *
     * @return boolean
     */
    @Override
    public boolean isDisabled() {
        boolean status = false;
        try {
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (!element.isEnabled()) {
                status = true;
            } else if (element.getAttribute("disabled") != null) {
                status = true;
            } else if (element.getAttribute("class").contains("rtbDisabled")) {
                status = true;
            }
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (NoSuchElementException e) {
            throw new ApplicationException("No such element with expression : " + locator);
        } finally {
            return status;
        }
    }

    /**
     * Verify if the element is enabled
     *
     * @return boolean
     * @throws ApplicationException
     * @throws ScriptException
     */
    @Override
    public boolean isEnabled() throws ApplicationException, ScriptException {
        try {
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            if (element.getAttribute("disabled") != null) {
                return false;
            } else if (element.getAttribute("class").contains("rtbDisabled")) {
                return false;
            }
            return element.isEnabled();
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (NoSuchElementException e) {
            throw new ApplicationException("No such element with expression : " + locator);
        }
    }

    /**
     * Memorize element text
     *
     * @param memoryKey
     */
    public void memorizeElementText(String memoryKey) {
        element = driver.findElement(locator);
        String memText = element.getAttribute("value").toString();
        WorkingMemory wMem = WorkingMemory.getInstance();
        wMem.setMemory(memoryKey, memText);
    }

    /**
     * Mouse hover on an element
     */
    public void mouseHover() {
        try {
            element = driver.findElement(locator);
            String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                    "arguments[0].dispatchEvent(evObj);";
            ((JavascriptExecutor) driver).executeScript(javaScript, element);
        } catch (Exception e) {
            log.error("Exception :", e);
        }
    }

    /**
     * Mouse hover on an element and click on it
     */
    public void mouseHoverAndClick() {
        try {
            element = driver.findElement(locator);

            String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                    "arguments[0].dispatchEvent(evObj);";
            ((JavascriptExecutor) driver).executeScript(javaScript, element);
            element.click();
        } catch (Exception e) {
            log.error("Exception :", e);
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
        } catch (NoSuchElementException e) {
            LOGGER.error("exception :", e);
            return false;
        }
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the button text has the exact text ignoring case sensitive.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if button text equals to the provided text
     * (ignoring case sensitive).
     */
    /*@Override
    public boolean verifyElementTextIsIgnoreCase(String text) {
        element = driver.findElement(locator);
        return element.getAttribute("value").compareToIgnoreCase(text) == 0;
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the button text contains the given text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if button text contains the provided text.
     */
    /*@Override
    public boolean verifyElementTextContains(String text) {
        element = driver.findElement(locator);
        return element.getAttribute("value").contains(text);
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the button text has the exact text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if button text equals to the provided text.
     */
    /*@Override
    public boolean verifyElementTextIs(String text) {
        element = driver.findElement(locator);
        LOGGER.debug("Element Text: " + element.getAttribute("value") + " Compared Text : " + text);
        return element.getAttribute("value").compareTo(text) == 0;
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the button text has the exact memorized text ignoring case
     * sensitive.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if button text equals to the memorized
     * text(ignoring case sensitive).
     */
    /*@Override
    public boolean verifyElementMemorizedTextIsIgnoreCase(String memoryKey) {
        element = driver.findElement(locator);
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return element.getAttribute("value").compareToIgnoreCase(memVal) == 0;
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the button text contains memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if button text contains memorized text.
     */
    /*@Override
    public boolean verifyElementContainsMemorizedText(String memoryKey) {
        element = driver.findElement(locator);
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return element.getAttribute("value").contains(memVal);
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the element has the exact memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element text equals to the memorized text.
     */
    /*@Override
    public boolean verifyElementMemorizedTextIs(String memoryKey) {
        element = driver.findElement(locator);
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return element.getAttribute("value").compareTo(memVal) == 0;
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * @return element
     */
    /*public WebElement getElement() {
        element = driver.findElement(locator);
        return this.element;
    }*/

}

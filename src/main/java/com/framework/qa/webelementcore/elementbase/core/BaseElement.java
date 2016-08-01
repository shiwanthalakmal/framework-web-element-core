package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.behavior.Element;
import com.framework.qa.webelementcore.elementbase.behavior.Typable;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseElement.java - Parent of the all elements types
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_23_2015
 * @since 04/23/2015.
 */
public abstract class BaseElement implements Element {
    private WebElement element;
    protected RemoteWebDriver driver;
    protected By locator;
    private String evaluatedValue;

    final static Logger log = Logger.getLogger(BaseElement.class);

    public String getEvaluatedValue() {
        return this.evaluatedValue;
    }

    public void setEvaluatedValue(String value) {
        this.evaluatedValue = value;
    }


    public BaseElement(RemoteWebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    /**
     * Gets the element text.
     *
     * @Returns the text of the element.
     */

    public String getText() throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            String returnValue = element.getText();
            if (returnValue == null || returnValue.length() == 0 || "".equals(returnValue)) {
                returnValue = element.getAttribute("value");
            }
            return returnValue;
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

    /**
     * Verify if the element is displayed in current context.
     *
     * @return <CODE>true</CODE> the element is displayed in current context.
     */

    public boolean isDisplayed() {
        boolean status = false;
        try {
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            status = element.isDisplayed();
        }catch (NoSuchElementException e) {
            log.error("exception :", e);
            throw new ApplicationException("No such element with expression : " +locator);
        } finally {
            return status;
        }
    }

    /**
     * Verify if the element is displayed in current context.
     *
     * @return <CODE>true</CODE> the element is displayed in current context.
     */

    public boolean isNotDisplayed() throws ApplicationException {
        return !(isDisplayed());
    }

    /**
     * Verify if the element is selected.
     *
     * @return <CODE>true</CODE> the element is selected.
     */

    public boolean isSelected() throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            return element.isSelected();
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

    /**
     * Moves the mouse to the middle of the element.
     */

    public void mouseMove() {
        Actions ac = new Actions(driver);
        this.element = driver.findElement(locator);
        ac.moveToElement(element).perform();
    }

    /**
     * Verify if the element has the exact text as given.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if element text equals to the provided text.
     */

    public boolean verifyElementTextIs(String text) throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            return element.getAttribute("value").compareTo(text) == 0;
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

    /**
     * Verify if the element text has the exact text ignoring case sensitive.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if the element text equals to provided
     * text(ignoring case sensitive).
     */

    public boolean verifyElementTextIsIgnoreCase(String text) throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            return element.getAttribute("value").compareToIgnoreCase(text) == 0;
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

    /**
     * Verify if the element contains the provided text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if element contains the provided text.
     */

    public boolean verifyElementTextContains(String text) throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            return element.getAttribute("value").contains(text);
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

    /**
     * Verify if the element has the exact memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element text equals to the memorized text.
     */

    public boolean verifyElementMemorizedTextIs(String memoryKey) throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
            if (memVal == null) {
                return false;
            }
            return element.getAttribute("value").compareTo(memVal) == 0;
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

    /**
     * Verify if the element has the exact memorized text ignoring case
     * sensitive.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element text equals to the memorized
     * text(ignoring case sensitive).
     */

    public boolean verifyElementMemorizedTextIsIgnoreCase(String memoryKey) throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
            if (memVal == null) {
                return false;
            }
            return element.getAttribute("value").compareToIgnoreCase(memVal) == 0;
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

    /**
     * Verify if the element contains provided memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element contains the memorized text.
     */

    public boolean verifyElementContainsMemorizedText(String memoryKey) throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
            if (memVal == null) {
                return false;
            }
            return element.getAttribute("value").contains(memVal);
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

    /**
     * Verifies whether the element exists.
     *
     * @return <code>true</code> if the element is found in the current context.
     */

    public boolean verifyElementExists() {
        return !driver.findElements(locator).isEmpty();
    }

    /**
     * @ret Verify the element is disabled. return <CODE>true</CODE> the element
     * is disabled.
     */

    public boolean isDisabled() throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (!element.isEnabled()) {
                return true;
            } else {
                String xpath = "//*[contains(@disabled,'')]";

                List<WebElement> elements = (ArrayList<WebElement>) driver.findElements(By.xpath(xpath));

                if ((elements != null) && elements.contains(element)) {
                    return true;
                }

            }

            return false;
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

    /**
     * @ret Verify if the element is enabled. return <CODE>true</CODE> the
     * element is enabled.
     */

    public boolean isEnabled() throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
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

    public void waitTillElementVisible(Integer maxWaitTime) {
        this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public void memorizePartialText(Integer start, Integer end, String key) throws ScriptException, ApplicationException {
        Typable typable = new Typable();
        WorkingMemory wm = WorkingMemory.getInstance();
        String text = typable.getValue(driver, locator);
        String memText = text.substring(start, end);
        wm.setMemory(key, memText);
    }

    public String getMemorizedText(String key) {
        WorkingMemory wm = WorkingMemory.getInstance();
        return wm.getMemory(key);
    }

    public WebElement getElement() throws ApplicationException, ScriptException {
        try{
            return (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
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

    /**
     * Wait until element clickable
     *
     * @param maxWaitTime
     */
    public void waitTillElementClickable(Integer maxWaitTime) throws ApplicationException, ScriptException {
        try{
            this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }
    }

    public void waitTillElementInvisible() throws ApplicationException {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element invisible");
        }

    }
}

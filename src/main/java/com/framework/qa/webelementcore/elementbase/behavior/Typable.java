package com.framework.qa.webelementcore.elementbase.behavior;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Typable.java -  Interface type of enter text action related class
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_23_2015
 * @since 04/23/2015.
 */
public class Typable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Typable.class);

    /**
     * Generic enter text action
     *
     * @param driver
     * @param locator
     * @param text
     * @throws ApplicationException
     * @throws ScriptException
     */
    public void enterText(RemoteWebDriver driver, By locator, String text) throws ApplicationException, ScriptException {
        try {
            WebElement element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            clear(driver, locator);
            int textLength = text.length();
            for (int i = 0; i < textLength; i++) {
                element.sendKeys(text.substring(i, i + 1));
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
        }
    }

    /**
     * Storing enter text values while during the perform
     *
     * @param driver
     * @param locator
     * @param text
     * @param key
     * @throws ApplicationException
     * @throws ScriptException
     */
    public void enterTextWhileMemorizing(RemoteWebDriver driver, By locator, String text, String key) throws ApplicationException, ScriptException {
        try {
            WebElement element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            WorkingMemory wMem = WorkingMemory.getInstance();
            wMem.setMemory(key, text);
            element.clear();
            int textLength = text.length();
            for (int i = 0; i < textLength; i++) {
                element.sendKeys(text.substring(i, i + 1));
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
        }
    }

    /**
     * Generic clear test filed text value
     *
     * @param driver
     * @param locator
     * @throws ApplicationException
     * @throws ScriptException
     */
    public void clear(RemoteWebDriver driver, By locator) throws ApplicationException, ScriptException {
        try {
            WebElement element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            element.clear();
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
     * Return element value tag specific text contains
     *
     * @param driver
     * @param locator
     * @return text contains
     * @throws ApplicationException
     * @throws ScriptException
     */
    public String getValue(RemoteWebDriver driver, By locator) throws ApplicationException, ScriptException {
        try {
            WebElement element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            return element.getAttribute("value").toString();
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
     * Retunmax length of the particular element
     *
     * @param driver
     * @param locator
     * @return max length of the element
     * @throws ApplicationException
     * @throws ScriptException
     */
    public int getMaxLength(RemoteWebDriver driver, By locator) throws ApplicationException, ScriptException {
        try {
            WebElement element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            int maxLen = -1;
            String maxLenStr = element.getAttribute("maxLength").trim();
            maxLen = Integer.parseInt(maxLenStr);
            return maxLen;
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
     * Compare element text contains against the memorized value
     *
     * @param driver
     * @param locator
     * @param memoryKey
     * @return boolean
     * @throws ApplicationException
     * @throws ScriptException
     */
    public boolean verifyElementMemorizedValueIs(RemoteWebDriver driver, By locator, String memoryKey) throws ApplicationException, ScriptException {
        try {
            WebElement element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
            return element.getAttribute("value").compareTo(memVal) == 0;
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
     * Verify the editorialise of teh particular element
     *
     * @param driver
     * @param locator
     * @return boolean
     * @throws ApplicationException
     * @throws ScriptException
     */
    public boolean isEditable(RemoteWebDriver driver, By locator) throws ApplicationException, ScriptException {
        try {
            WebElement element = driver.findElement(locator);
            String value = element.getAttribute("readonly");
            return value == null;
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
     * Verify the particular element contains is empty
     *
     * @param driver
     * @param locator
     * @return boolean
     * @throws ApplicationException
     * @throws ScriptException
     */
    public boolean isEmpty(RemoteWebDriver driver, By locator) throws ApplicationException, ScriptException {
        try {
            WebElement element = driver.findElement(locator);
            String text = element.getAttribute("value");
            return text.isEmpty();
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
}

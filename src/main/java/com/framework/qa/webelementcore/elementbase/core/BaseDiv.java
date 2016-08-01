package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.behavior.Typable;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Represent HTML Div
 *
 * @author Shiwantha Lakmal
 */
public abstract class BaseDiv extends BaseElement {
    private WebElement element;
    private RemoteWebDriver driver;
    private By locator;
    private Typable typable;
    private static final String INNER_HTML = "innerHTML";

    final static Logger log = Logger.getLogger(BaseDiv.class);

    public BaseDiv(RemoteWebDriver driver, By locator) {
        super(driver, locator);
        this.driver = driver;
        this.locator = locator;
        typable = new Typable();

    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/

    /**
     * Performs a click in the middle of the element.
     */

    public void click() throws ApplicationException, ScriptException {
        try {
            element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
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

    /**
     * Verify if the element has the exact text as given.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if element text equals to the provided text.
     */
    @Override
    public boolean verifyElementTextIs(String text) {
        boolean status = false;
        try{
            element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            status = element.getText().compareTo(text) == 0 || element.getAttribute(INNER_HTML).compareTo(text) == 0;
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
     * Verify if the element text has the exact text ignoring case sensitive.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if the element text equals to provided
     * text(ignoring case sensitive).
     */
    @Override
    public boolean verifyElementTextIsIgnoreCase(String text) {
        boolean status = false;
        try{
            element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            status = element.getText().compareToIgnoreCase(text) == 0 || element.getAttribute(INNER_HTML).compareToIgnoreCase(text) == 0;
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
     * Verify if the element contains the provided text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if element contains the provided text.
     */
    @Override
    public boolean verifyElementTextContains(String text) {
        boolean status = false;
        try{
            element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            status = element.getText().contains(text) || element.getAttribute(INNER_HTML).contains(text);
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
     * Verify if the element has the exact memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element text equals to the memorized text.
     */
    @Override
    public boolean verifyElementMemorizedTextIs(String memoryKey){
        boolean status = false;
        try{
            element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
            if (memVal != null) {
                status = element.getText().compareTo(memVal) == 0 || element.getAttribute(INNER_HTML).compareTo(memVal) == 0;
            }else{
                status = false;
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
     * Verify if the element has the exact memorized text ignoring case
     * sensitive.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element text equals to the memorized
     * text(ignoring case sensitive).
     */
    @Override
    public boolean verifyElementMemorizedTextIsIgnoreCase(String memoryKey) {
        boolean status = false;
        try{
            element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
            if (memVal != null) {
                status = element.getText().compareToIgnoreCase(memVal) == 0 || element.getAttribute(INNER_HTML).compareToIgnoreCase(memVal) == 0;
            }else{
                status = false;
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
        } finally {
            return status;
        }

    }

    /**
     * Verify if the element contains provided memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element contains the memorized text.
     */
    @Override
    public boolean verifyElementContainsMemorizedText(String memoryKey) {
        boolean status = false;
        try{
            element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
            if (memVal != null) {
                return element.getText().contains(memVal) || element.getAttribute(INNER_HTML).contains(memVal);
            }else {
                status = false;
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
        } finally {
            return status;
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
        } finally {
            return status;
        }
    }

    /**
     * @ret Verify if the element is enabled. return <CODE>true</CODE> the
     * element is enabled.
     */
    @Override
    public boolean isEnabled() {
        boolean status = false;
        try{
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            if (element.getAttribute("class").contains("rtbDisabled")) {
                status = false;
            }
            status = element.isEnabled();
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
        } finally {
            return status;
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

    public void enterText(String text) throws ScriptException, ApplicationException {
        typable.enterText(driver, locator, text);
    }

    public void clear() throws ScriptException, ApplicationException {
        typable.clear(driver, locator);
    }

    // ----------------not used

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the element is displayed in current context.
     *
     * @return <CODE>true</CODE> the element is displayed in current context.
     */
    /*@Override
    public boolean isDisplayed() {
        try {
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
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

    /**
     * Memorize element text
     *
     * @param memoryKey
     */
    public void memorizeElementText(String memoryKey) {
        element = driver.findElement(locator);
        String memText = element.getText().toString();
        WorkingMemory wMem = WorkingMemory.getInstance();
        wMem.setMemory(memoryKey, memText);
    }

    /*
     * @auther
     */

    public boolean isContainsText() {
        element = driver.findElement(locator);
        return !element.getText().trim().isEmpty();

    }

    /*
     * @author : osaadhir This method will check whether the div contains
     * previously memorized value + count.
     */

    public boolean verifyElementMemorizedTextIsIncreasedBy(String memoryKey, String count) {
        element = driver.findElement(locator);
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        Integer increasedMemVal = Integer.parseInt(memVal) + Integer.parseInt(count);

        return element.getAttribute("textContent").compareTo(increasedMemVal.toString()) == 0;
    }

    /**
     * verify div value contains only digits
     *
     * @return
     */
    public boolean verifyOnlyDigitsPresent() {
        element = driver.findElement(locator);
        String regex = "[0-9]+";
        return element.getText().matches(regex);
    }

    /**
     * verify div value contains only digits in currency format
     *
     * @return
     */
    public boolean verifyOnlyDigitsPresentInCurrencyFormat() {
        element = driver.findElement(locator);
        String regex = "^\\d([0-9,.]+)";
        return element.getText().matches(regex);
    }

    /**
     * verify div value contains only letters
     *
     * @return
     */
    public boolean verifyOnlyLettersPresent() {
        element = driver.findElement(locator);
        String regex = "[a-zA-Z ]+";
        return !element.getText().trim().isEmpty() && element.getText().matches(regex);
    }

    /**
     * verify div value matches the provided regular expression
     *
     * @param expression
     * @return
     */
    public boolean isValidWithRegEx(String expression) {
        boolean valid = false;
        element = driver.findElement(locator);
        String regex = expression;
        try {
            if (element.getText().matches(regex)) {
                valid = true;
            }
        } catch (PatternSyntaxException e) {
            log.error("exception Occurred in isValidWithRegEx :" + e.getMessage(), e);
            valid = false;
        }
        return valid;
    }

    /**
     * Verify the value of the div is a valid email address format.
     *
     * @return
     */
    public boolean verifyValidEmailAddressFormat() {
        Pattern pattern;
        Matcher matcher;
        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(emailPattern);
        element = driver.findElement(locator);
        String elementText = element.getText();
        matcher = pattern.matcher(elementText);
        return matcher.matches();

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

}

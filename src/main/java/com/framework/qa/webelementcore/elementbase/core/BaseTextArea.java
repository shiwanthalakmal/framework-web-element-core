package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.behavior.Typable;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Represent the HTML dom element <textarea.>
 *
 * @author Shiwantha Lakmal
 */
public abstract class BaseTextArea extends BaseElement {
    private WebElement element;
    private RemoteWebDriver driver;
    private By locator;
    private Typable typable;
    final static Logger log = Logger.getLogger(BaseTextArea.class);

    public BaseTextArea(RemoteWebDriver driver, By locator) {
        super(driver, locator);
        this.driver = driver;
        this.locator = locator;
        this.typable = new Typable();
    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/

    public void enterText(String text) throws ScriptException, ApplicationException {
        typable.enterText(driver, locator, text);
    }

    public boolean isEditable() throws ScriptException, ApplicationException {
        return typable.isEditable(driver, locator);
    }

    public void clear() throws ScriptException, ApplicationException {
        typable.clear(driver, locator);
    }

    /**
     * Memorize element text
     *
     * @param memoryKey
     */
    public void memorizeElementText(String memoryKey) {
        element = driver.findElement(locator);
        String text = element.getAttribute("value");
        WorkingMemory wMem = WorkingMemory.getInstance();
        wMem.setMemory(memoryKey, text);
    }

    /**
     * Checking the length of the typable area is match with given value
     *
     * @param length
     */
    public boolean verifyMaxLengthIs(Integer length) throws ScriptException, ApplicationException {
        int maxLen = typable.getMaxLength(driver, locator);
        return maxLen == length;
    }

    public void enterTextWhileMemorizing(String text, String key) throws ScriptException, ApplicationException {
        typable.enterTextWhileMemorizing(driver, locator, text, key);
    }

    public boolean verifyElementMemorizedValueIs(String memoryKey) throws ScriptException, ApplicationException {
        return typable.verifyElementMemorizedValueIs(driver, locator, memoryKey);
    }

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the element has the exact text as given.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if element text equals to the provided text.
     */
    /*@Override
    public boolean verifyElementTextIs(String text) {
        element = driver.findElement(locator);
        String value = element.getAttribute("value");

        return value.compareTo(text) == 0;
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
        String value = element.getAttribute("value");

        return value.compareToIgnoreCase(text) == 0;
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
        String value = element.getAttribute("value");

        return value.contains(text);
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
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        element = driver.findElement(locator);
        String value = element.getAttribute("value");

        if (memVal == null) {
            return false;
        }
        return value.compareToIgnoreCase(memVal) == 0;
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
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        element = driver.findElement(locator);
        String value = element.getAttribute("value");
        if (memVal == null) {
            return false;
        }

        return value.contains(memVal);
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
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        element = driver.findElement(locator);
        String value = element.getAttribute("value");

        if (memVal == null) {
            return false;
        }

        return value.compareTo(memVal) == 0;
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

    /**
     * Verify if the given text field is empty or not
     *
     * @author ChathuryaD
     */

    public boolean isEmpty() throws ScriptException, ApplicationException {
        this.element = driver.findElement(locator);
        return typable.isEmpty(driver, locator);
    }

    /**
     * verify textArea value contains only digits
     *
     * @return
     */
    public boolean verifyOnlyDigitsPresent() throws ScriptException, ApplicationException {
        String elementText = typable.getValue(driver, locator);
        String regex = "[0-9]+";
        return elementText.matches(regex);
    }

    /**
     * verify textArea value contains only digits in currency format
     *
     * @return
     */
    public boolean verifyOnlyDigitsPresentInCurrencyFormat() throws ScriptException, ApplicationException {
        String elementText = typable.getValue(driver, locator);
        String regex = "^\\d([0-9,.]+)";
        return elementText.matches(regex);
    }

    /**
     * verify textArea value contains only letters
     *
     * @return
     */
    public boolean verifyOnlyLettersPresent() throws ScriptException, ApplicationException {
        String elementText = typable.getValue(driver, locator);
        String regex = "[a-zA-Z ]+";
        return !elementText.trim().isEmpty() && elementText.matches(regex);
    }

    /**
     * verify textArea value matches the provided regular expression
     *
     * @param expression
     * @return
     */
    public boolean isValidWithRegEx(String expression) throws ScriptException, ApplicationException {
        boolean valid = false;
        String elementText = typable.getValue(driver, locator);
        String regex = expression;
        try {
            if (elementText.matches(regex)) {
                valid = true;
            }
        } catch (PatternSyntaxException e) {
            log.error("exception Occurred in isValidWithRegEx :" + e.getMessage(), e);
            valid = false;
        }
        return valid;
    }

    /**
     * Verify the value of the textArea is a valid email address format.
     *
     * @return
     */
    public boolean verifyValidEmailAddressFormat() throws ScriptException, ApplicationException {
        Pattern pattern;
        Matcher matcher;
        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(emailPattern);
        String elementText = typable.getValue(driver, locator);
        matcher = pattern.matcher(elementText);
        return matcher.matches();

    }

}

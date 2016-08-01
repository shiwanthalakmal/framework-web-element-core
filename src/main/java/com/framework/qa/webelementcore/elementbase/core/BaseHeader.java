package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.memory.WorkingMemory;
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
 * BaseButton.java - Represent based header behaviours
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_23_2015
 * @since 04/23/2015.
 */
public abstract class BaseHeader extends BaseElement {
    private WebElement element;
    private RemoteWebDriver driver;
    private By locator;
    private static final String INNER_HTML = "innerHTML";
    final static Logger log = Logger.getLogger(BaseHeader.class);

    public BaseHeader(RemoteWebDriver driver, By locator) {
        super(driver, locator);
        this.driver = driver;
        this.locator = locator;

    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/

    /**
     * Verify if the element has the exact text as given.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if element text equals to the provided text.
     */
    @Override
    public boolean verifyElementTextIs(String text) {
        element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        return element.getText().compareTo(text) == 0 || element.getAttribute(INNER_HTML).compareTo(text) == 0;
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
        element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        return element.getText().compareToIgnoreCase(text) == 0 || element.getAttribute(INNER_HTML).compareToIgnoreCase(text) == 0;
    }

    /**
     * Verify if the element contains the provided text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if element contains the provided text.
     */
    @Override
    public boolean verifyElementTextContains(String text) {
        element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        return element.getText().contains(text) || element.getAttribute(INNER_HTML).contains(text);
    }

    /**
     * Verify if the element has the exact memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element text equals to the memorized text.
     */
    @Override
    public boolean verifyElementMemorizedTextIs(String memoryKey) {
        element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return element.getText().compareTo(memVal) == 0 || element.getAttribute(INNER_HTML).compareTo(memVal) == 0;
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
        element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return element.getText().compareToIgnoreCase(memVal) == 0 || element.getAttribute(INNER_HTML).compareToIgnoreCase(memVal) == 0;
    }

    /**
     * Verify if the element contains provided memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element contains the memorized text.
     */
    @Override
    public boolean verifyElementContainsMemorizedText(String memoryKey) {
        element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return element.getText().contains(memVal) || element.getAttribute(INNER_HTML).contains(memVal);
    }

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

    /**
     * verify whether it contains any text or whether it is empty
     *
     * @return
     */
    public boolean isContainsText() {
        element = driver.findElement(locator);
        return !element.getText().trim().isEmpty();
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

}

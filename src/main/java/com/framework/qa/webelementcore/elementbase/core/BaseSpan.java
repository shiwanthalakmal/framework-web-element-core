package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Represent HTML button
 *
 * @author Shiwantha Lakmal
 */
public abstract class BaseSpan extends BaseElement {
    protected WebElement baseSpanElement;
    protected RemoteWebDriver baseSpanDriver;
    private static final String INNER_HTML = "innerHTML";

    final static Logger log = Logger.getLogger(BaseSpan.class);

    public BaseSpan(RemoteWebDriver baseSpanDriver, By locator) {
        super(baseSpanDriver, locator);
        this.baseSpanDriver = baseSpanDriver;
        this.locator = locator;

    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.baseSpanElement = (new WebDriverWait(baseSpanDriver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/

    /**
     * Performs a click in the middle of baseSpanElement.
     */

    public void click() throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        Actions builder = new Actions(baseSpanDriver);
        builder.moveToElement(baseSpanElement).click(baseSpanElement).perform();
    }

    /**
     * Performs a click in the middle of baseSpanElement if it exist.
     */
    public void clickIfExists() {
        try {
            waitTillElementVisible(5000);
            this.click();

        } catch (Exception e) {
            log.error("exception on clickIfExists :", e);
        }

    }

    /**
     * Verify if the span text has the exact text ignoring case sensitive.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if button text equals to the provided text
     * (ignoring case sensitive).
     */
    @Override
    public boolean verifyElementTextIsIgnoreCase(String text) throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        return baseSpanElement.getText().compareToIgnoreCase(text) == 0 || baseSpanElement.getAttribute(INNER_HTML).compareTo(text) == 0;
    }

    /**
     * Verify if the span text contains the given text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if button text contains the provided text.
     */
    @Override
    public boolean verifyElementTextContains(String text) throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        return baseSpanElement.getText().contains(text) || baseSpanElement.getAttribute(INNER_HTML).contains(text);
    }

    /**
     * Verify if the button text has the exact text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if button text equals to the provided text.
     */
    @Override
    public boolean verifyElementTextIs(String text) throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        log.debug("Element Text: " + baseSpanElement.getText() + " Compared Text : " + text);
        return baseSpanElement.getText().compareTo(text) == 0 || baseSpanElement.getAttribute(INNER_HTML).compareTo(text) == 0;
    }

    /**
     * Verify if the button text has the exact memorized text ignoring case
     * sensitive.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if button text equals to the memorized
     * text(ignoring case sensitive).
     */
    @Override
    public boolean verifyElementMemorizedTextIsIgnoreCase(String memoryKey) throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return baseSpanElement.getText().compareToIgnoreCase(memVal) == 0 || baseSpanElement.getAttribute(INNER_HTML).compareToIgnoreCase(memVal) == 0;
    }

    /**
     * Verify if the button text contains memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if button text contains memorized text.
     */
    @Override
    public boolean verifyElementContainsMemorizedText(String memoryKey) throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return baseSpanElement.getText().contains(memVal) || baseSpanElement.getAttribute(INNER_HTML).contains(memVal);
    }

    /**
     * Verify if the baseSpanElement has the exact memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if baseSpanElement text equals to the memorized
     * text.
     */
    @Override
    public boolean verifyElementMemorizedTextIs(String memoryKey) throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return baseSpanElement.getText().compareTo(memVal) == 0 || baseSpanElement.getAttribute(INNER_HTML).compareTo(memVal) == 0;
    }

    /**
     * @ret Verify the baseSpanElement is disabled. return <CODE>true</CODE> the
     * baseSpanElement is disabled.
     */
    @Override
    public boolean isDisabled() throws ScriptException, ApplicationException {
        this.baseSpanElement = getElement();
        if (!baseSpanElement.isEnabled()) {
            return true;
        } else if (baseSpanElement.getAttribute("disabled") != null) {
            return true;
        } else if (baseSpanElement.getAttribute("class").contains("rtbDisabled")) {
            return true;
        }

        return false;
    }

    /**
     * @ret Verify if the baseSpanElement is enabled. return <CODE>true</CODE>
     * the baseSpanElement is enabled.
     */
    @Override
    public boolean isDisplayed() {
        boolean status = false;
        try {
            this.baseSpanElement = getElement();
            status = baseSpanElement.isDisplayed();
        } catch (NoSuchElementException e) {
            log.error("exception :", e);
            throw new ApplicationException("No such element with expression : " +locator);
        } finally {
            return status;
        }
    }

    //TODO- Remove method since baseElement has implementation
    /**
     * Verifies whether the baseSpanElement exists.
     *
     * @return <code>true</code> if the baseSpanElement is found in the current
     * context.
     */
    /*@Override
    public boolean verifyElementExists() {
        return !baseSpanDriver.findElements(locator).isEmpty();
    }*/

    /**
     * Verify if the baseSpanElement is displayed in current context.
     *
     * @return <CODE>true</CODE> the baseSpanElement is displayed in current
     * context.
     */
    @Override
    public boolean isNotDisplayed() {
        return !(isDisplayed());
    }

    /**
     * Memorize baseSpanElement text
     *
     * @param memoryKey
     */
    public void memorizeElementText(String memoryKey) throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        String memText = baseSpanElement.getText().toString();
        WorkingMemory wMem = WorkingMemory.getInstance();
        wMem.setMemory(memoryKey, memText);
    }

    /**
     * Verify whether the link contains previously memorized value + count.
     *
     * @param memoryKey , count
     */

    public boolean verifyElementMemorizedTextIsIncreasedBy(String memoryKey, String count) throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        Integer increasedMemVal = Integer.parseInt(memVal) + Integer.parseInt(count);

        return baseSpanElement.getAttribute("textContent").compareTo(increasedMemVal.toString()) == 0;
    }

    /**
     * verify span value contains only digits
     *
     * @return
     */
    public boolean verifyOnlyDigitsPresent() throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        String regex = "[0-9]+";
        return baseSpanElement.getText().matches(regex);
    }

    /**
     * verify span value contains only digits in currency format
     *
     * @return
     */
    public boolean verifyOnlyDigitsPresentInCurrencyFormat() throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        String regex = "^\\d([0-9,.]+)";
        return baseSpanElement.getText().matches(regex);
    }

    /**
     * verify span value contains only letters
     *
     * @return
     */
    public boolean verifyOnlyLettersPresent() throws ScriptException, ApplicationException {
        baseSpanElement = getElement();
        String regex = "[a-zA-Z ]+";
        return !baseSpanElement.getText().trim().isEmpty() && baseSpanElement.getText().matches(regex);
    }

    /**
     * verify span value matches the provided regular expression
     *
     * @param expression
     * @return
     */
    public boolean isValidWithRegEx(String expression) throws ScriptException, ApplicationException {
        boolean valid = false;
        baseSpanElement = getElement();
        String regex = expression;
        try {
            if (baseSpanElement.getText().matches(regex)) {
                valid = true;
            }
        } catch (PatternSyntaxException e) {
            log.error("exception Occurred in isValidWithRegEx :" + e.getMessage(), e);
            valid = false;
        }
        return valid;
    }

    /**
     * Verify the value of the span is a valid email address format.
     *
     * @return
     */
    public boolean verifyValidEmailAddressFormat() throws ScriptException, ApplicationException {
        Pattern pattern;
        Matcher matcher;
        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(emailPattern);
        baseSpanElement = getElement();
        String elementText = baseSpanElement.getText();
        matcher = pattern.matcher(elementText);
        return matcher.matches();

    }

}

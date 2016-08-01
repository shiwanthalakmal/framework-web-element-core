package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.behavior.Typable;
import com.framework.qa.webelementcore.elementbase.util.KeyBoard;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Represent the HTML dom baseTextFieldElement <input type="text">
 *
 * @author Shiwantha Lakmal
 */

public abstract class BaseTextField extends BaseElement {
    protected WebElement baseTextFieldElement;
    protected KeyBoard keyBoard;
    protected RemoteWebDriver baseTextFieldDriver;
    private Typable typable;
    final static Logger log = Logger.getLogger(BaseButton.class);

    public BaseTextField(RemoteWebDriver baseTextFieldDriver, By locator) throws AWTException {
        super(baseTextFieldDriver, locator);
        this.baseTextFieldDriver = baseTextFieldDriver;
        this.locator = locator;
        keyBoard = new KeyBoard();
        typable = new Typable();
    }

    /**
     * Enter text to the textFiled baseTextFieldElement
     *
     * @param text is the provided text to enter to the baseTextFieldElement
     */

    public void enterText(String text) throws ScriptException, ApplicationException {
        typable.enterText(baseTextFieldDriver, locator, text);
    }

    /**
     * Checks if a text field is editable
     */
    public boolean isEditable() throws ScriptException, ApplicationException {
        return typable.isEditable(baseTextFieldDriver, locator);
    }

    /**
     * Performs a click on the middle of the baseTextFieldElement.
     */
    public void click() {
        baseTextFieldElement = baseTextFieldDriver.findElement(locator);
        baseTextFieldElement.click();
    }

    /**
     * Performs a click on the baseTextFieldElement if it exists.
     */
    public void clickIfExists() {
        try {

            waitTillElementVisible(5000);
            click();

        } catch (Exception e) {
            log.info("exception occured while clicking textfield :", e);
        }

    }

    /**
     * Clears the textField baseTextFieldElement
     */

    public void clear() throws ScriptException, ApplicationException {
        typable.clear(baseTextFieldDriver, locator);
    }

    /**
     * Gets the value of the textField baseTextFieldElement
     */

    public void memorizeElementText(String memoryKey) throws ScriptException, ApplicationException {
        String text = typable.getValue(baseTextFieldDriver, locator);
        WorkingMemory wMem = WorkingMemory.getInstance();
        wMem.setMemory(memoryKey, text);
    }

    /**
     * Gets the maximum length of the textField Element is match with given
     * value
     */

    public boolean verifyMaxLengthIs(Integer length) throws ScriptException, ApplicationException {
        int maxLen = typable.getMaxLength(baseTextFieldDriver, locator);
        return maxLen == length;

    }

    /**
     * Enter text to the textField and memorize for later use.
     *
     * @param text
     * @param key
     */

    public void enterTextWhileMemorizing(String text, String key) throws ScriptException, ApplicationException {
        typable.enterTextWhileMemorizing(baseTextFieldDriver, locator, text, key);
    }

    public boolean verifyElementMemorizedValueIs(String memoryKey) throws ScriptException, ApplicationException {
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return typable.getValue(baseTextFieldDriver, locator).compareTo(memVal) == 0;
    }

    public void enterMemorizedValue(String memoryKey) throws ScriptException, ApplicationException {
        baseTextFieldElement = baseTextFieldDriver.findElement(locator);
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);

        typable.enterText(baseTextFieldDriver, locator, memVal);
    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.baseTextFieldElement = (new WebDriverWait(baseTextFieldDriver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/

    /**
     * Verify if the button text has the exact text ignoring case sensitive.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if button text equals to the provided text
     * (ignoring case sensitive).
     */
    @Override
    public boolean verifyElementTextIsIgnoreCase(String text) throws ScriptException, ApplicationException {
        return typable.getValue(baseTextFieldDriver, locator).compareToIgnoreCase(text) == 0;
    }

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the button text contains the given text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if button text contains the provided text.
     */
    /*@Override
    public boolean verifyElementTextContains(String text) {
        baseTextFieldElement = baseTextFieldDriver.findElement(locator);
        return baseTextFieldElement.getAttribute("value").contains(text);
    }*/

    @Override
    public boolean verifyElementTextIs(String text) {
        baseTextFieldElement = (new WebDriverWait(baseTextFieldDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        String data = baseTextFieldElement.getText();
        String value = baseTextFieldElement.getAttribute("value");
        if (data.equalsIgnoreCase(text) || value.equalsIgnoreCase(text)) {
            return true;
        }
        return false;
    }

    /**
     * Verify if the baseTextFieldElement has the exact memorized text
     * sensitive.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if baseTextFieldElement text equals to the
     * memorized text(case sensitive).
     */
    @Override
    public boolean verifyElementMemorizedTextIs(String memoryKey) throws ScriptException, ApplicationException {

        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return typable.getValue(baseTextFieldDriver, locator).compareTo(memVal) == 0;
    }

    /**
     * Verify if the baseTextFieldElement has the exact memorized text ignoring
     * case sensitive.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if baseTextFieldElement text equals to the
     * memorized text(ignoring case sensitive).
     */
    @Override
    public boolean verifyElementMemorizedTextIsIgnoreCase(String memoryKey) throws ScriptException, ApplicationException {
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return typable.getValue(baseTextFieldDriver, locator).compareToIgnoreCase(memVal) == 0;
    }

    /**
     * Verify if the baseTextFieldElement contains provided memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if baseTextFieldElement contains the memorized
     * text.
     */
    @Override
    public boolean verifyElementContainsMemorizedText(String memoryKey) throws ScriptException, ApplicationException {
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return typable.getValue(baseTextFieldDriver, locator).contains(memVal);
    }

    /**
     * Verify if the baseTextFieldElement contains the entered text, which is
     * not case sensitive
     */

    public boolean verifyElementContainsMemorizedTextIgnoreCase(String memoryKey) throws ScriptException, ApplicationException {
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        memVal = memVal.toLowerCase();

        return typable.getValue(baseTextFieldDriver, locator).toLowerCase().contains(memVal);
    }

    public void enterTextForcefully(String text) {
        WebElement element = baseTextFieldDriver.findElement(locator);
        element.click();
        keyBoard.clearTextField();
        keyBoard.typeText(text);
    }

    public void enterTextIfExists(String text) throws ScriptException, ApplicationException {
        Boolean isPresent = !baseTextFieldDriver.findElements(locator).isEmpty();
        if (isPresent) {
            baseTextFieldElement = baseTextFieldDriver.findElement(locator);
            typable.enterText(baseTextFieldDriver, locator, text);
        } else {
            // ignore clicking

        }

    }

    /**
     * @ret Verify the baseTextFieldElement is disabled. return
     * <CODE>true</CODE> the baseTextFieldElement is disabled.
     */
    @Override
    public boolean isDisabled() {
        this.baseTextFieldElement = (new WebDriverWait(baseTextFieldDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (!baseTextFieldElement.isEnabled()) {
            return true;
        } else if (baseTextFieldElement.getAttribute("disabled") != null) {
            return true;
        } else if (baseTextFieldElement.getAttribute("class").contains("rtbDisabled")) {
            return true;
        }

        return false;
    }

    /**
     * @ret Verify if the baseTextFieldElement is enabled. return
     * <CODE>true</CODE> the baseTextFieldElement is enabled.
     */
    @Override
    public boolean isEnabled() {
        this.baseTextFieldElement = (new WebDriverWait(baseTextFieldDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        if (baseTextFieldElement.getAttribute("class").contains("rtbDisabled")) {
            return false;
        }

        return baseTextFieldElement.isEnabled();
    }

    /**
     * Verifies whether the baseTextFieldElement exists.
     *
     * @return <code>true</code> if the baseTextFieldElement is found in the
     * current context.
     */
    @Override
    public boolean verifyElementExists() {
        return !baseTextFieldDriver.findElements(locator).isEmpty();
    }

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the baseTextFieldElement is displayed in current context.
     *
     * @return <CODE>true</CODE> the baseTextFieldElement is displayed in
     * current context.
     */
    /*@Override
    public boolean isDisplayed() {
        try {
            this.baseTextFieldElement = (new WebDriverWait(baseTextFieldDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            return baseTextFieldElement.isDisplayed();
        } catch (Exception e) {
            LOGGER.error("exception :", e);
            return false;
        }
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the baseTextFieldElement is displayed in current context.
     *
     * @return <CODE>true</CODE> the baseTextFieldElement is displayed in
     * current context.
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
        this.baseTextFieldElement = baseTextFieldDriver.findElement(locator);
        return typable.isEmpty(baseTextFieldDriver, locator);
    }

    /**
     * ----------Modifications Begin----------- Date : Mar 17, 2014 Note : This
     * modification was done to add functions to verify if an entered text
     * contains letters, special characters or numerical characters
     *
     */

    /**
     * verify if an entered text contains letters
     */
    public boolean verifyIfMemorizedTextContainsLetters(String key) {

        boolean isText = false;
        String text = WorkingMemory.getInstance().getMemory(key);

        if (text.matches(".*[a-zA-Z]+.*")) {
            isText = true;
        }

        return isText;

    }

    /**
     * verify if an entered text contains numbers
     */

    public boolean verifyIfMemorizedTextContainsNumbers(String key) {

        boolean isNums = false;
        String text = WorkingMemory.getInstance().getMemory(key);

        if (text.matches(".*\\d.*")) {
            isNums = true;
        }

        return isNums;
    }

    /**
     * verify if an entered text contains special characters
     */

    public boolean verifyIfMemorizedTextContainsSpecialCharacters(String key) {

        boolean isCharacters = false;
        String text = WorkingMemory.getInstance().getMemory(key);
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matchers = pattern.matcher(text);

        if (matchers.find()) {
            isCharacters = true;
        }

        return isCharacters;

    }

    /**
     * ----------Modifications End-----------
     */
    /**
     * verify textbox value contains only digits
     *
     * @return
     */
    public boolean verifyOnlyDigitsPresent() throws ScriptException, ApplicationException {
        String elementText = typable.getValue(baseTextFieldDriver, locator);
        String regex = "[0-9]+";
        return elementText.matches(regex);
    }

    /**
     * verify textbox value contains only digits in currency format
     *
     * @return
     */
    public boolean verifyOnlyDigitsPresentInCurrencyFormat() throws ScriptException, ApplicationException {
        String elementText = typable.getValue(baseTextFieldDriver, locator);
        String regex = "^\\d([0-9,.]+)";
        return elementText.matches(regex);
    }

    /**
     * verify textbox value contains only letters
     *
     * @return
     */
    public boolean verifyOnlyLettersPresent() throws ScriptException, ApplicationException {
        String elementText = typable.getValue(baseTextFieldDriver, locator);
        String regex = "[a-zA-Z ]+";
        return !elementText.trim().isEmpty() && elementText.matches(regex);
    }

    /**
     * verify textbox value matches the provided regular expression
     *
     * @param expression
     * @return
     */
    public boolean isValidWithRegEx(String expression) throws ScriptException, ApplicationException {
        boolean valid = false;
        String elementText = typable.getValue(baseTextFieldDriver, locator);
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
     * Verify the value of the textbox is a valid email address format.
     *
     * @return
     */
    public boolean verifyValidEmailAddressFormat() throws ScriptException, ApplicationException {
        Pattern pattern;
        Matcher matcher;
        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(emailPattern);
        String elementText = typable.getValue(baseTextFieldDriver, locator);
        matcher = pattern.matcher(elementText);
        return matcher.matches();

    }
}
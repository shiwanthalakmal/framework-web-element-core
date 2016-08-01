package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.behavior.Clickable;
import com.framework.qa.webelementcore.elementbase.util.KeyBoard;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.awt.*;

/**
 * Represent HTML button
 *
 * @author Shiwantha Lakmal
 */
public abstract class BaseTableHeader extends BaseElement {
    protected WebElement baseTableHeaderElement;
    private KeyBoard keyBoard;
    protected RemoteWebDriver baseTableHeaderDriver;
    private static final String INNER_HTML = "innerHTML";
    private Clickable mouse;


    final static Logger log = Logger.getLogger(BaseTableHeader.class);

    public BaseTableHeader(RemoteWebDriver baseTableHeaderDriver, By locator) throws AWTException {
        super(baseTableHeaderDriver, locator);
        keyBoard = new KeyBoard();
        this.baseTableHeaderDriver = baseTableHeaderDriver;
        this.locator = locator;
        this.mouse = new Clickable();
    }

    @Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.baseTableHeaderElement = (new WebDriverWait(baseTableHeaderDriver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    /**
     * Performs a click in the middle of the baseTableHeaderElement.
     */

    public void click() throws ApplicationException, ScriptException {
        mouse.click(baseTableHeaderDriver, locator);
    }

    public void clickIfExists() {
        try {

            waitTillElementVisible(5000);
            mouse.click(baseTableHeaderDriver, locator);

        } catch (Exception e) {
            log.error("exception :", e);
        }

    }


    /**
     * Verify if the baseTableHeaderElement has the exact text as given.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if baseTableHeaderElement text equals to the provided text.
     */
    @Override
    public boolean verifyElementTextIs(String text) {
        baseTableHeaderElement = (new WebDriverWait(baseTableHeaderDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        return baseTableHeaderElement.getText().compareTo(text) == 0 || baseTableHeaderElement.getAttribute(INNER_HTML).compareTo(text) == 0;
    }

    /**
     * Verify if the baseTableHeaderElement text has the exact text ignoring case sensitive.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if the baseTableHeaderElement text equals to provided
     * text(ignoring case sensitive).
     */
    @Override
    public boolean verifyElementTextIsIgnoreCase(String text) {
        baseTableHeaderElement = (new WebDriverWait(baseTableHeaderDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        return baseTableHeaderElement.getText().compareToIgnoreCase(text) == 0 || baseTableHeaderElement.getAttribute(INNER_HTML).compareToIgnoreCase(text) == 0;
    }

    /**
     * Verify if the baseTableHeaderElement contains the provided text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if baseTableHeaderElement contains the provided text.
     */
    @Override
    public boolean verifyElementTextContains(String text) {
        baseTableHeaderElement = (new WebDriverWait(baseTableHeaderDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        return baseTableHeaderElement.getText().contains(text) || baseTableHeaderElement.getAttribute(INNER_HTML).contains(text);
    }

    /**
     * Verify if the baseTableHeaderElement has the exact memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if baseTableHeaderElement text equals to the memorized text.
     */
    @Override
    public boolean verifyElementMemorizedTextIs(String memoryKey) {
        baseTableHeaderElement = (new WebDriverWait(baseTableHeaderDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return baseTableHeaderElement.getText().compareTo(memVal) == 0 || baseTableHeaderElement.getAttribute(INNER_HTML).compareTo(memVal) == 0;
    }

    /**
     * Verify if the baseTableHeaderElement has the exact memorized text ignoring case
     * sensitive.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if baseTableHeaderElement text equals to the memorized
     * text(ignoring case sensitive).
     */
    @Override
    public boolean verifyElementMemorizedTextIsIgnoreCase(String memoryKey) {
        baseTableHeaderElement = (new WebDriverWait(baseTableHeaderDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return baseTableHeaderElement.getText().compareToIgnoreCase(memVal) == 0 || baseTableHeaderElement.getAttribute(INNER_HTML).compareToIgnoreCase(memVal) == 0;
    }

    /**
     * Verify if the baseTableHeaderElement contains provided memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if baseTableHeaderElement contains the memorized text.
     */
    @Override
    public boolean verifyElementContainsMemorizedText(String memoryKey) {
        baseTableHeaderElement = (new WebDriverWait(baseTableHeaderDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memVal == null) {
            return false;
        }
        return baseTableHeaderElement.getText().contains(memVal) || baseTableHeaderElement.getAttribute(INNER_HTML).contains(memVal);
    }

    /**
     * Clears the text and enters text forcefully
     *
     * @param text to enter to the table baseTableHeaderElement
     */
    public void enterTextForcefully(String text) {
        keyBoard.clearTextField();
        keyBoard.typeText(text);
    }

    /**
     * @ret Verify the baseTableHeaderElement is disabled. return <CODE>true</CODE> the baseTableHeaderElement
     * is disabled.
     */
    @Override
    public boolean isDisabled() {
        this.baseTableHeaderElement = (new WebDriverWait(baseTableHeaderDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (!baseTableHeaderElement.isEnabled()) {
            return true;
        } else if (baseTableHeaderElement.getAttribute("disabled") != null) {
            return true;
        } else if (baseTableHeaderElement.getAttribute("class").contains("rtbDisabled")) {
            return true;
        }

        return false;
    }

    /**
     * @ret Verify if the baseTableHeaderElement is enabled. return <CODE>true</CODE> the
     * baseTableHeaderElement is enabled.
     */
    @Override
    public boolean isEnabled() {
        this.baseTableHeaderElement = (new WebDriverWait(baseTableHeaderDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        if (baseTableHeaderElement.getAttribute("class").contains("rtbDisabled")) {
            return false;
        }

        return baseTableHeaderElement.isEnabled();
    }

    //TODO- Remove method since baseElement has implementation
    /**
     * Verifies whether the baseTableHeaderElement exists.
     *
     * @return <code>true</code> if the baseTableHeaderElement is found in the current context.
     */
    /*@Override
    public boolean verifyElementExists() {
        return !baseTableHeaderDriver.findElements(locator).isEmpty();
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the baseTableHeaderElement is displayed in current context.
     *
     * @return <CODE>true</CODE> the baseTableHeaderElement is displayed in current context.
     */
    /*@Override
    public boolean isDisplayed() {
        try {
            this.baseTableHeaderElement = baseTableHeaderDriver.findElement(locator);
            return baseTableHeaderElement.isDisplayed();
        } catch (Exception e) {
            LOGGER.error("exception :", e);
            return false;
        }
    }*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the baseTableHeaderElement is displayed in current context.
     *
     * @return <CODE>true</CODE> the baseTableHeaderElement is displayed in current context.
     */
    /*@Override
    public boolean isNotDisplayed() {
        return !(isDisplayed());
    }*/

}

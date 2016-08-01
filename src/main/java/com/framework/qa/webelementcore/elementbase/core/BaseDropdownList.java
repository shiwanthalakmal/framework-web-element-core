package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.behavior.Selectable;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Represent HTML <select.> element
 *
 * @author Shiwantha Lakmal
 */
public abstract class BaseDropdownList extends BaseElement {
    private WebElement element;
    private RemoteWebDriver driver;
    private By locator;
    private Selectable selectable;
    final static Logger log = Logger.getLogger(BaseDropdownList.class);

    public BaseDropdownList(RemoteWebDriver driver, By locator) {
        super(driver, locator);
        this.driver = driver;
        this.locator = locator;
        this.selectable = new Selectable();
    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/

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

    /**
     * Selects an option from the dropdown using the option index.
     *
     * @param index is the position of the option to be selected.
     */

    public void select(Integer index) throws ScriptException, ApplicationException {
        selectable.select(driver, locator, index);
    }

    /**
     * Selects an option from the dropdown by the option text.
     *
     * @param option is the text to be selected from the dropdown.
     */

    public void selectByOption(String option) throws ScriptException, ApplicationException {
        selectable.selectByOption(driver, locator, option);
    }

    /**
     * Selects last option from the dropdown.
     *
     * @throws ScriptException
     * @throws ApplicationException
     */
    public void selectLastOption() throws ScriptException, ApplicationException {
        WebElement element = driver.findElement(locator);
        int size = element.findElements(By.tagName("option")).size() - 1;
        selectable.select(driver, locator, size);
    }

    /**
     * Selects an option from the dropdown that contains the given text.
     *
     * @param option is the text to be selected from the dropdown.
     */

    public void selectByOptionContains(String option) throws ScriptException, ApplicationException {
        selectable.selectByOptionContains(driver, locator, option);

    }

    /**
     * Verify if the element's option has the exact text as given.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if element text equals to the provided text.
     */
    @Override
    public boolean verifyElementTextIs(String text) throws ScriptException, ApplicationException {
        return selectable.verifyElementTextIs(driver, locator, text);
    }

    /**
     * Verify if the element text has the exact text ignoring case sensitive.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if the element text equals to provided
     * text(ignoring case sensitive).
     */
    @Override
    public boolean verifyElementTextIsIgnoreCase(String text) throws ScriptException, ApplicationException {
        return selectable.verifyElementTextIsIgnoreCase(driver, locator, text);
    }

    /**
     * Verify if the element contains the provided text.
     *
     * @param text value to compare with.
     * @return <CODE>true</CODE> if element contains the provided text.
     */
    @Override
    public boolean verifyElementTextContains(String text) {
        element = driver.findElement(locator);
        return element.getText().contains(text);
    }

    /**
     * Verify if the element has the exact memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element text equals to the memorized text.
     */
    @Override
    public boolean verifyElementMemorizedTextIs(String memoryKey) throws ScriptException, ApplicationException {
        return selectable.verifyElementMemorizedTextIs(driver, locator, memoryKey);
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
    public boolean verifyElementMemorizedTextIsIgnoreCase(String memoryKey) throws ScriptException, ApplicationException {
        return selectable.verifyElementMemorizedTextIsIgnoreCase(driver, locator, memoryKey);
    }

    /**
     * Verify if the element contains provided memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element contains the memorized text.
     */
    @Override
    public boolean verifyElementContainsMemorizedText(String memoryKey) {
        element = driver.findElement(locator);
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);

        if (memVal == null) {
            return false;
        }

        return element.getText().contains(memVal);
    }

    /**
     * Verify if the element's selected option is equal memorized text.
     *
     * @param memoryKey is the memorized text to compare with.
     * @return <CODE>true</CODE> if element contains the memorized text.
     * @author ChathuryaD
     */
    public boolean verifyElementIsSelectedMemorizedText(String memoryKey) throws ScriptException, ApplicationException {
        return selectable.verifyElementIsSelectedMemorizedText(driver, locator, memoryKey);
    }

    /**
     * @ret Verify the element is disabled. return <CODE>true</CODE> the element
     * is disabled.
     */
    @Override
    public boolean isDisabled() {
        this.element = driver.findElement(locator);
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
        this.element = driver.findElement(locator);

        if (element.getAttribute("class").contains("rtbDisabled")) {
            return false;
        }
        return element.isEnabled();
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
     * memorized the dropdown's selected option's text
     *
     * @author ChathuryaD
     */
    public void memorizedSelectedOptionText(String memKey) throws ScriptException, ApplicationException {
        selectable.memorizedSelectedOptionText(driver, locator, memKey);
    }

    public String getTextOfSelectedOption() throws ScriptException, ApplicationException {
        return selectable.getTextOfSelectedOption(driver, locator);
    }

    public void selectByMemorizedIndex(String memoryKey) throws ScriptException, ApplicationException {

        if (validParameters(memoryKey)) {

            WorkingMemory workingMemory = WorkingMemory.getInstance();
            String index = workingMemory.getMemory(memoryKey);

            Integer memorizedIndex;
            try {
                memorizedIndex = Integer.parseInt(index);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new ScriptException("Invalid Parameter exception");
            }

            this.select(memorizedIndex);
        }

    }

    public void selectByMemorizedOption(String memoryKey) throws ScriptException, ApplicationException {
        if (validParameters(memoryKey)) {
            WorkingMemory workingMemory = WorkingMemory.getInstance();
            String option = workingMemory.getMemory(memoryKey);
            this.selectByOption(option);
        }
    }

    /**
     * Select Random option from drop down and memorize
     * @param memoryKey
     */
    public void selectAndMemorizedRandomOption(String memoryKey) throws ScriptException, ApplicationException {
        Select dropDown = new Select(driver.findElement(locator));
        dropDown.selectByIndex(new Random().nextInt(dropDown.getOptions().size()));
        memorizedSelectedOptionText(memoryKey);
    }

    private boolean validParameters(String... parameter)
            throws ScriptException {
        for (String param : parameter) {
            if (StringUtils.isBlank(param)) {
                throw new ScriptException("User Provided parameter "
                        + param + " Null/Empty exception");
            }
        }
        return true;
    }


}

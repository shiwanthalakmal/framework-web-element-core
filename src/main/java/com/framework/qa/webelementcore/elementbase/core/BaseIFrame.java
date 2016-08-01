package com.framework.qa.webelementcore.elementbase.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

/**
 * Represent HTML iframe
 *
 * @author Shiwantha Lakmal
 */
public abstract class BaseIFrame extends BaseElement {
    private WebElement element;
    private RemoteWebDriver driver;
    private By locator;
    final static Logger log = Logger.getLogger(BaseIFrame.class);

    public BaseIFrame(RemoteWebDriver driver, By locator) {
        super(driver, locator);
        this.driver = driver;
        this.locator = locator;

    }

    /**
     * Switch to an Iframe from the main web page.
     */

    public void switchHere() {

        element = driver.findElement(locator);
        driver.switchTo().frame(element);
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

    public void waitUntilIframeLoaded(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator.toString()));
    }

    public void waitUntilIframeLoaded() {
        Object result = null;
        do {
            try {
                Thread.sleep(100);
                result = ((JavascriptExecutor) driver).executeScript("return document['readyState'] ? 'complete'==document.readyState : true");

            } catch (Exception e) {
                log.error("exception :", e);
                result = true;
            }
        } while (!("true".equals(result.toString())));
    }
}

package com.framework.qa.webelementcore.elementbase.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

/**
 * Represent javascript Alert, confirmation and prompt boxes.
 *
 * @author Shiwantha Lakmal
 */
public abstract class BaseJsAlert {
    private RemoteWebDriver driver;
    private Alert alert;
    private String evaluatedValue;
    final static Logger log = Logger.getLogger(BaseJsAlert.class);

    public BaseJsAlert(RemoteWebDriver driver, By locator) {
        super();
        this.driver = driver;

    }


    public String getEvaluatedValue() {
        return this.evaluatedValue;
    }

    public void setEvaluatedValue(String value) {
        this.evaluatedValue = value;
    }

    /**
     * Switch to the alert if the alert exists.
     */
    public void switchHere() {
        WebDriverWait wait = new WebDriverWait(this.driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        this.alert = this.driver.switchTo().alert();

    }

    /**
     * Verifies the existence of the alert.
     *
     * @return true if the alert exists
     */
    public boolean verifyAlertExists() {
        try {
            switchHere();
            return true;
        } catch (Exception e) {
            log.error("exception in verifyAlertExists:", e);
            return false;
        }

    }

    /**
     * Verifies the given text contains in the alert.
     *
     * @param text
     * @return true|false
     */
    public boolean verifyAlertTextContains(String text) {
        try {
            switchHere();
            return alert.getText().contains(text);
        } catch (Exception e) {
            log.error("exception in verifyAlertTextContains :", e);
        }
        return false;

    }

    /**
     * Enter text into the prompt alert.
     *
     * @param text
     */
    public void enterPromptText(String text) {
        try {
            switchHere();
            alert.sendKeys(text);
        } catch (Exception e) {
            log.error("exception :", e);
        }
    }

    /**
     * Clicks on the ok button of the alert.
     */
    public void acceptAlert() {
        try {
            switchHere();
            this.alert.accept();
        } catch (Exception e) {
            log.error("exception in acceptAlert :", e);
        } finally {
            this.driver.switchTo().defaultContent();
        }

    }

    /**
     * Closes the popup
     */
    public void dismissAlert() {
        try {
            switchHere();
            this.alert.dismiss();
        } catch (Exception e) {
            log.error("exception in dismissAlert :", e);
        } finally {
            this.driver.switchTo().defaultContent();
        }

    }

}

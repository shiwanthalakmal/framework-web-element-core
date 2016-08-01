package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.util.KeyBoard;
import com.framework.qa.webelementcore.elementbase.util.KeyStrokeExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.Iterator;
import java.util.Set;

public abstract class BaseMagicElement {
    protected RemoteWebDriver driver;
    private KeyBoard keyBoard;
    private KeyStrokeExecutor keyStrokeExecutor;

    private String evaluatedValue;

    /**
     * Using internal prefix to avoid possible conflict with user defined memory
     * keys
     */
    public static final String BROWSER_TYPE_MEMORY_KEY = "internal_browser_type";

    public static final String FIREFOX_BROWSER_MEMORY_VALUE = "firefox";
    public static final String CHROME_BROWSER_MEMORY_VALUE = "chrome";
    public static final String IE_BROWSER_MEMORY_VALUE = "ie";

    final static Logger log = Logger.getLogger(BaseMagicElement.class);

    /**
     * Initialize the magic element object which is dedicated for performing
     * common operations. which are not associated with elements.
     *
     * @param driver  .
     * @param locator .
     * @throws AWTException .
     */
    public BaseMagicElement(RemoteWebDriver driver, By locator) throws AWTException {
        super();
        this.keyBoard = new KeyBoard();
        this.driver = driver;
    }


    public String getEvaluatedValue() {
        return this.evaluatedValue;
    }

    public void setEvaluatedValue(String value) {
        this.evaluatedValue = value;
    }

    /**
     * Verify the page title.
     *
     * @param title .
     * @return <code>true</code> if page title match with the given title.
     */
    public boolean verifyPageTitle(String title) {
        return driver.getTitle().compareTo(title) == 0;
    }

    /**
     * Verify page title contains the given text.
     *
     * @param text to compare with the title.
     * @return <code>true</code> if page title contains the given text.
     */
    public boolean verifyPageTitleContains(String text) {
        return driver.getTitle().contains(text);
    }

    /**
     * Verify the url.
     *
     * @param url to compare with the url.
     * @return <code>true</code> if current url matches with the given url.
     */
    public boolean verifyUrl(String url) {
        return driver.getCurrentUrl().compareTo(url) == 0;
    }

    /**
     * Verify the given text contains in the URL.
     *
     * @param urlPart to compare with the URL.
     * @return <code>true</code> if the current url contains the given string.
     */
    public boolean verifyUrlContains(String urlPart) {
        return driver.getCurrentUrl().contains(urlPart);
    }

    /**
     * Sleep for a given time amount in milli seconds.
     *
     * @param milliSec duration to sleep.
     */
    public void waitFor(Integer milliSec) {
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {

            log.error("InterruptedException", e);
        }
    }

    /**
     * Verifies whether the element exists.
     *
     * @param xpathExpression is the xpath of the element.
     * @return <code>true</code> if the element is found in the current context.
     */
    public boolean verifyElementExists(String xpathExpression) {
        return !driver.findElements(By.xpath(xpathExpression)).isEmpty();
    }

    /**
     * Switch back to the main web page from an iFrame
     */
    public void switchBackToHomePage() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            log.error("exception :", e);
        }

    }

    /**
     * Wait for a modal dialog to display
     *
     * @param title
     */
    public void waitUntilModalDialogDisplay(String title) {
        String parentWindow = driver.getWindowHandle();
        driver.switchTo().window(parentWindow);
        boolean modalDialogShown = false;
        long start = System.currentTimeMillis();
        long now = 0;

        while (!modalDialogShown) {
            if ((now - start) < (6 * 1000)) {
                Set<String> handles = driver.getWindowHandles();
                if (handles.size() > 1) {
                    Iterator<String> itr = handles.iterator();
                    modalDialogShown = switchToWindow(title, parentWindow, itr);
                }
                if (!modalDialogShown) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.error("exception"+e.getMessage());
                    }
                    now = System.currentTimeMillis();
                }
            } else {
                break;
            }

        }
        if (!modalDialogShown) {
            log.debug("Failed to Switched in to Modal Dialog : " + title);
        }
        driver.switchTo().window(parentWindow);
    }

    /**
     * Verify the modal dialog is displayed after a given time period.
     *
     * @param title    of the modal dialog.
     * @param waitTime for the modal dialog to display.
     * @return <code>true</code> if the modal dialog is displayed.
     * @throws InterruptedException
     * @throws Exception            if any problems trying to execute process
     */
    public boolean verifyModalDialogDisplay(String title, Integer waitTime) throws InterruptedException {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            log.error("exception :", e);
        }
        String parentWindow = driver.getWindowHandle();
        driver.switchTo().window(parentWindow);
        boolean modalDialogShown = false;

        long start = System.currentTimeMillis();
        long now = 0;

        while (!modalDialogShown) {
            if ((now - start) < (waitTime * 1000)) {
                Set<String> handles = driver.getWindowHandles();
                Iterator<String> itr = handles.iterator();
                modalDialogShown = switchToWindow(title, parentWindow, itr);
                if (!modalDialogShown) {
                    Thread.sleep(1000);
                    now = System.currentTimeMillis();
                }

            } else {
                break;
            }
        }
        driver.switchTo().window(parentWindow);
        return modalDialogShown;
    }


    /**
     * @param title
     * @param parentWindow
     * @param itr
     * @return
     */
    private boolean switchToWindow(String title, String parentWindow, Iterator<String> itr) {
        boolean modalDialogShown = false;
        while (itr.hasNext()) {
            try {
                driver.switchTo().window(itr.next());
                if (driver.getTitle().equals(title)) {
                    modalDialogShown = true;
                    log.debug("Successfully Verified Modal Dialog  : "
                            + title);
                    break;
                } else {
                    driver.switchTo().window(parentWindow);
                }

            } catch (Exception e) {
                log.error("exception", e);

            }

        }
        return modalDialogShown;
    }

    /**
     * Switch to an iFrame.
     *
     * @param title of the Iframe.
     * @return <CODE>true<CODE> if successfully switched to the Iframe.
     * @throws InterruptedException
     * @throws Exception            if any problem occurs during the Iframe switch.
     */
    public boolean switchToModalDialog(String title) throws InterruptedException {
        String parentWindow = driver.getWindowHandle();
        WorkingMemory wMem = WorkingMemory.getInstance();
        wMem.setMemory("MODAL_DIALOG_PARENT", parentWindow);
        driver.switchTo().window(parentWindow);
        boolean switched = false;

        long start = System.currentTimeMillis();
        long now = 0;

        while (!switched) {
            if ((now - start) < 10000) {
                Set<String> handles = driver.getWindowHandles();
                Iterator<String> itr = handles.iterator();
                while (itr.hasNext()) {
                    try {
                        driver.switchTo().window(itr.next());
                        if (driver.getTitle().equals(title)) {
                            switched = true;
                            log.debug("Successfully Switched in to Modal Dialog : "
                                    + title);
                            break;
                        } else {
                            driver.switchTo().window(parentWindow);
                        }

                    } catch (Exception e) {
                        log.error("exception", e);
                        switched = false;

                    }
                }

                if (!switched) {
                    driver.switchTo().window(parentWindow);
                }
                Thread.sleep(1000);
                now = System.currentTimeMillis();
            } else {

                break;
            }
        }

        if (switched) {
            return true;
        } else {
            log.debug("Failed to Switched in to Modal Dialog : " + title);
            driver.switchTo().window(parentWindow);
            return false;
        }

    }

    /**
     * Wait until the page loading is completed.
     */
    public void waitUntilPageLoadComplete() {
        try {
            waitTillPageLoaded(driver);
        } catch (Exception e) {
            log.error("exception", e);
        }
    }

    /**
     * Wait until page is loaded.
     *
     * @param browser
     * @author Sachithp
     */
    private void waitTillPageLoaded(RemoteWebDriver browser) {

        Object result = null;
        int count = 0;
        do {
            count++;
            try {

                Thread.sleep(100);
                result = ((JavascriptExecutor) driver)
                        .executeScript("return document['readyState'] ? 'complete'==document.readyState : true");


            } catch (InterruptedException e) {
                log.error("exception occured : " + e.getMessage(), e);
                result = true;

            }
            if (count > 600) {
                break;
            }

        } while (!("true".equals(result.toString())));
    }

    /**
     * Clear the textField element.
     */
    public void clearTextField() {
        keyBoard.clearTextField();
    }

    /**
     * Enter text forcefully.
     *
     * @param text to enter.
     */
    public void enterText(String text) {
        keyBoard.typeText(text);
        log.debug("Entered Text Forcefully:" + text);
    }

    /**
     * concatenate a memorized text with set of keys
     *
     * @param          :New key to store memorized text
     * @param memorizedKeys
     * @param delimeter
     */
    public void concatenateMemorizedKeys(String key, String memorizedKeys,
                                         String delimeter) {
        String[] memorizedKeysList = memorizedKeys.split(" ");
        if (memorizedKeysList.length > 0) {
            StringBuilder newKey = new StringBuilder("");
            for (int i = 0; i < memorizedKeysList.length; i++) {
                if (i != memorizedKeysList.length - 1) {
                    newKey.append(WorkingMemory.getInstance().getMemory(
                            memorizedKeysList[i]));
                    if ("delimeter".equals(delimeter)) {
                        newKey.append(delimeter);
                    }
                } else {
                    newKey.append(WorkingMemory.getInstance().getMemory(
                            memorizedKeysList[i]));
                }
            }
            WorkingMemory.getInstance().setMemory(key, newKey.toString());
        }
    }

    public void executeJavaScript(String javascrptToExecute) {
        if(javascrptToExecute == null || javascrptToExecute.trim().isEmpty())
        {
            throw new IllegalArgumentException("JavaScript code is empty.");
        }
        ((JavascriptExecutor) driver).executeScript(javascrptToExecute);
    }

    public boolean isInternetExplorer() {
        return IE_BROWSER_MEMORY_VALUE.equals(WorkingMemory.getInstance().getMemory(BROWSER_TYPE_MEMORY_KEY));
    }

    public boolean isFirefox() {
        return FIREFOX_BROWSER_MEMORY_VALUE.equals(WorkingMemory.getInstance().getMemory(BROWSER_TYPE_MEMORY_KEY));
    }

    public boolean isChrome() {
        return CHROME_BROWSER_MEMORY_VALUE.equals(WorkingMemory.getInstance().getMemory(BROWSER_TYPE_MEMORY_KEY));
    }

    public void setCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        driver.manage().addCookie(cookie);
    }

    public boolean verifyCookieValue(String key, String value) {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                return value.equals(cookie.getValue());
            }
        }
        return false;
    }

    public void deleteCookie(String key) {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                driver.manage().deleteCookieNamed(key);
                return;
            }
        }
        throw new IllegalArgumentException("Cookie with given name does not exist.");
    }

    public void sendKeyStroke(String keystroke) {
        keyStrokeExecutor.executeKeyStroke(keystroke);
    }

}

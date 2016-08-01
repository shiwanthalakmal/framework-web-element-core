package com.framework.qa.webelementcore.elementbase.core;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.framework.qa.utils.memory.WorkingMemory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

public abstract class BaseFlashElement {
    private BufferedImage image;
    private Mouse mouse = new DesktopMouse();
    private Keyboard keyboard = new DesktopKeyboard();
    private ImageTarget imageTarget;
    private ScreenRegion screen;
    private RemoteWebDriver driver;

    final static Logger log = Logger.getLogger(BaseFlashElement.class);

    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            log.error("exception :", e);
        }
        return image;
    }

    public BaseFlashElement(RemoteWebDriver driver, String locator) {

        this.driver = driver;
        this.image = decodeToImage(locator);
        imageTarget = new ImageTarget(image);
        screen = new DesktopScreenRegion();

    }

    public BaseFlashElement(RemoteWebDriver driver, File locator) {

        this.driver = driver;
        imageTarget = new ImageTarget(locator);
        screen = new DesktopScreenRegion();

    }

    public void click() {
        ScreenRegion region = findElement(driver);
        if (region == null) {
            throw new ElementNotFoundException("Flash Element", "", "");
        } else {

            mouse.click(region.getCenter());
        }
    }

    /*
     * clickAll() is a method to click all similar kind of elements in screen.
     */
    public void clickAll() {

        ScreenRegion region = screen.wait(imageTarget, 1000);
        while (region != null) {
            mouse.click(region.getCenter());
            mouse.wheel(1, 1);
            region = screen.wait(imageTarget, 1000);

        }

    }

    public void rightClick() {
        ScreenRegion region = findElement(driver);
        if (region == null) {
            throw new ElementNotFoundException("Flash Element", "", "");
        } else {

            mouse.rightClick(region.getCenter());
        }
    }

    public void doubleClick() {
        ScreenRegion region = findElement(driver);
        if (region == null) {
            throw new ElementNotFoundException("Flash Element", "", "");
        } else {
            mouse.doubleClick(region.getCenter());
        }
    }

    public void clickIfExists() {
        ScreenRegion region = findElement(driver);
        if (region != null) {
            mouse.click(region.getCenter());
        }
    }

    public boolean verifyElementExists() {
        ScreenRegion region = findElement(driver);
        return region != null;

    }

    public void enterText(String text) {
        ScreenRegion region = findElement(driver);
        if (region == null) {
            throw new ElementNotFoundException("Flash Element", "", "");
        } else {
            mouse.click(region.getCenter());
            keyboard.type(text);
        }
    }

    public void enterTextWhileMemorizing(String text, String key) {
        ScreenRegion region = findElement(driver);
        if (region == null) {
            throw new ElementNotFoundException("Flash Element", "", "");
        } else {
            mouse.click(region.getCenter());
            WorkingMemory wMem = WorkingMemory.getInstance();
            wMem.setMemory(key, text);
            keyboard.type(text);
        }
    }

    public void enterMemorizedValue(String memoryKey) {
        ScreenRegion region = findElement(driver);
        if (region == null) {
            throw new ElementNotFoundException("Flash Element", "", "");
        } else {
            String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
            if (memVal == null) {
                memVal = "";
            }
            mouse.click(region.getCenter());
            keyboard.type(memVal);
        }
    }

    public void enterTextIfExists(String text) {
        ScreenRegion region = findElement(driver);
        if (region != null) {
            mouse.click(region.getCenter());
            keyboard.type(text);
        }
    }

    private ScreenRegion findElement(WebDriver driver) {
        ScreenRegion region = screen.wait(imageTarget, 1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long scrollHeight = (long) 1;
        Long clientlHeight = (long) 1;
        int maxscrolles = 0;
        if (region == null) {
            js.executeScript("window.scrollTo(0,0);");
            scrollHeight = (Long) js.executeScript("return document.documentElement.scrollHeight;");
            clientlHeight = (Long) js.executeScript("return document.documentElement.clientHeight;");
            if (clientlHeight > 0) {
                maxscrolles = (int) (scrollHeight / clientlHeight);
            } else {
                maxscrolles = 1;
            }
        }
        int count = 0;
        long currentplace = clientlHeight;
        while (region == null) {
            region = screen.wait(imageTarget, 1000);

            js.executeScript("window.scrollTo(0," + currentplace + ");");
            currentplace += clientlHeight;

            if (maxscrolles < count) {
                break;
            }
            count++;
        }
        return region;

    }

}

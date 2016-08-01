package com.framework.qa.webelementcore.elementbase.core;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;

public abstract class BaseTime extends BaseElement {

    public BaseTime(RemoteWebDriver driver, By locator) throws AWTException {
        super(driver, locator);

    }

}

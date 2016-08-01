package com.framework.qa.webelementcore.element;

import com.framework.qa.webelementcore.elementbase.core.BaseTime;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;

public class Time extends BaseTime {

    public Time(RemoteWebDriver driver, By locator) throws AWTException {
        super(driver, locator);

    }

}

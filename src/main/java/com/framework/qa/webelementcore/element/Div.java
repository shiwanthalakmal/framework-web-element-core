package com.framework.qa.webelementcore.element;

import com.framework.qa.webelementcore.elementbase.core.BaseDiv;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Div extends BaseDiv {

    public Div(RemoteWebDriver driver, By locator) {
        super(driver, locator);

    }

}

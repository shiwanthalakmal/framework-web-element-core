package com.framework.qa.webelementcore.element;

import com.framework.qa.webelementcore.elementbase.core.BaseSpan;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Span extends BaseSpan {

    public Span(RemoteWebDriver driver, By locator) {
        super(driver, locator);

    }

}

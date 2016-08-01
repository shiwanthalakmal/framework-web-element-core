package com.framework.qa.webelementcore.element;

import com.framework.qa.webelementcore.elementbase.core.BaseLink;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Link extends BaseLink {

    public Link(RemoteWebDriver driver, By locator) {
        super(driver, locator);

    }

}

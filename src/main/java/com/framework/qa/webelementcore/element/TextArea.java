package com.framework.qa.webelementcore.element;

import com.framework.qa.webelementcore.elementbase.core.BaseTextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TextArea extends BaseTextArea {

    public TextArea(RemoteWebDriver driver, By locator) {
        super(driver, locator);

    }

}

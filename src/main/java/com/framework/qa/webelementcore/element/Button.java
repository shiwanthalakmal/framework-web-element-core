package com.framework.qa.webelementcore.element;

import com.framework.qa.webelementcore.elementbase.core.BaseButton;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author Shiwantha Lakmal
 */
public class Button extends BaseButton {

    public Button(RemoteWebDriver driver, By locator) {
        super(driver, locator);

    }


}

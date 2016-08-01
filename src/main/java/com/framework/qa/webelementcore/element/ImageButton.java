package com.framework.qa.webelementcore.element;

import com.framework.qa.webelementcore.elementbase.core.BaseImageButton;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ImageButton extends BaseImageButton {

    public ImageButton(RemoteWebDriver driver, By locator) {
        super(driver, locator);

    }

}

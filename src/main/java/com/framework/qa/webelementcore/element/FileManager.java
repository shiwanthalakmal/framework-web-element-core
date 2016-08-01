package com.framework.qa.webelementcore.element;

import com.framework.qa.webelementcore.elementbase.core.BaseFileManager;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FileManager extends BaseFileManager {

	public FileManager(RemoteWebDriver driver, By locator) {
		super(driver, locator);
	}

}

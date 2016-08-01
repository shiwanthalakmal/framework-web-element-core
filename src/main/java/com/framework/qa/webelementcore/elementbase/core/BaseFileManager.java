package com.framework.qa.webelementcore.elementbase.core;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Shiwantha Lakmal
 *
 */
public abstract class BaseFileManager extends BaseElement {

	final static Logger log = Logger.getLogger(BaseFileManager.class);
	public static final String EXECUTION_START_TIME = "execeution_start_time";

	public BaseFileManager(RemoteWebDriver driver, By locator) {
		super(driver, locator);
	}

	public void waitForDownloadToComplete(final String fileName) throws InterruptedException {
		Thread.sleep(1000);// wait for file to be created
		if (fileName == null || fileName.trim().isEmpty()) {
			throw new IllegalArgumentException("fileName cannot be empty");
		}

		try {
			final Date date = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy").parse(EXECUTION_START_TIME);
			File fileDir = new File(System.getProperty("user.home") + File.separator + "Downloads");
			File[] files = fileDir.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File file, String name) {
					Date modified = new Date(file.lastModified());
					return name.startsWith(fileName) && date.before(modified);
				}
			});

			if (files.length == 0) {
				throw new IllegalStateException("No file with the given prefix has started download.");
			}

			for (File file : files) {
				long fileSize;
				do {
					fileSize = file.length();
					Thread.sleep(1000);
				} while (file.length() > fileSize);
			}

		} catch (ParseException e) {
			throw new IllegalStateException("Test case execution start time is not parseable.");
		}
	}

	public boolean verifyDownloadedFileExists(final String fileName, final String fileExtension) {
		if (fileName == null || fileName.trim().isEmpty()) {
			throw new IllegalArgumentException("fileName cannot be empty");
		}

		if (fileExtension == null || fileExtension.trim().isEmpty()) {
			throw new IllegalArgumentException("fileExtension cannot be empty");
		}

		File fileDir = new File(System.getProperty("user.home") + File.separator + "Downloads");
		Date date;
		try {
			date = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy").parse(EXECUTION_START_TIME);
		} catch (ParseException e) {
			log.error("Date convertion error: " + e.toString());
			return false;
		}

		File[] files = fileDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.startsWith(fileName) && name.endsWith(fileExtension));
			}
		});

		for (File file : files) {
			Date modified = new Date(file.lastModified());
			if (date.before(modified)) {
				return true;
			}
		}
		log.error("Verification error: Downloaded File does not exist.");
		return false;
	}

}

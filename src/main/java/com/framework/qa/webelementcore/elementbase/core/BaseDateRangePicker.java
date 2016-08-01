package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

/**
 * BaseDateRangePicker.java - Represent based date-picker behaviours
 *
 * @author Shiwantha Lakmal
 * @version 1.0-SNAPSHOT Last modified 04_23_2015
 * @since 04/23/2015.
 */
public abstract class BaseDateRangePicker extends BaseElement {
    private WebElement element;
    final static Logger log = Logger.getLogger(BaseDateRangePicker.class);

    private static final String CALANDER_HEADER_CLASS = "datepicker-switch";
    private static final String PREV_ARROW_CLASS = "prev";
    private static final String NEXT_ARROW_CLASS = "next";

    public BaseDateRangePicker(RemoteWebDriver driver, By locator) {
        super(driver, locator);
        this.driver = driver;
        this.locator = locator;
    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    @Override
	public void waitTillElementVisible(Integer maxWaitTime) {
		this.element = (new WebDriverWait(driver, (int) maxWaitTime / 1000))
				.until(ExpectedConditions.presenceOfElementLocated(locator));

	}*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verifies whether the element exists.
     *
     * @return <code>true</code> if the element is found in the current context.
     */
	/*@Override
	public boolean verifyElementExists() {
		return !driver.findElements(locator).isEmpty();
	}*/

    //TODO- Remove method since baseElement has implementation
    /**
     * Verify if the element is displayed in current context.
     *
     * @return <CODE>true</CODE> the element is displayed in current context.
     */
	/*@Override
	public boolean isDisplayed() {
		try {
			this.element = driver.findElement(locator);
			return element.isDisplayed();
		} catch (Exception e) {
			LOGGER.error(
					"Element is Displayed Exception Occurred : "
							+ e.getMessage(), e);
			return false;
		}
	}*/

    //TODO- Remove method since baseElement has implementation

    /**
     * Verify if the element is not displayed in current context.
     *
     * @return <CODE>true</CODE> the element is not displayed in current
     * context.
     */
	/*@Override
	public boolean isNotDisplayed() {
		return !(isDisplayed());
	}/*

	/**
	 * Performs a click in the middle of element.
	 */
    public void click() throws ApplicationException, ScriptException {
        try {
            this.element = (new WebDriverWait(driver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (WebDriverException e) {
            throw new ScriptException("Element is not clickable at current point");
        }
    }

    /**
     * Click on the previous arrow of the calendar element
     */
    public void clickPrev() throws ApplicationException, ScriptException {
        try {
            Thread.sleep(1000);
            WebElement prevElement = ((new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath(getPrevElementXpath()))));
            Thread.sleep(1000);
            prevElement.click();
        } catch (InterruptedException e) {
            throw new ScriptException("Invalid Parameter Exception");
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (WebDriverException e) {
            throw new ScriptException("Element is not clickable at current point");
        }
    }

    /**
     * Click on the Next arrow of the calendar element
     */
    public void clickNext() throws ApplicationException, ScriptException {
        try {
            Thread.sleep(1000);
            WebElement nextElement = ((new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath(getNextElementXpath()))));
            Thread.sleep(1000);
            nextElement.click();
        } catch (InterruptedException e) {
            throw new ScriptException("Invalid Parameter Exception");
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (WebDriverException e) {
            throw new ScriptException("Element is not clickable at current point");
        }
    }

    /**
     * Select a date from the calendar
     *
     * @param date
     */
    public void selectDay(Integer date) throws ApplicationException, ScriptException {
        try {
            Thread.sleep(1000);
            WebElement dateElement = ((new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath(getCalenderDateXpath(date)))));
            Thread.sleep(2000);
            dateElement.click();
        } catch (InterruptedException e) {
            throw new ScriptException("Invalid Parameter Exception");
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (WebDriverException e) {
            throw new ScriptException("Element is not clickable at current point");
        }
    }

    /**
     * Select the last day of a selected month in format (dd/mm/yyyy)
     */
    public void selectDate(String value) throws ApplicationException, ScriptException {
        try {
            Thread.sleep(1000);
            String date = value.trim();
            if (isvalidDateFormat(date)) {
                String[] dates = date.split("/", 3);
                selectYear(Integer.valueOf(dates[2].trim()));
                selectMonth(Integer.valueOf(dates[1].trim()));
                selectDay(Integer.valueOf(dates[0].trim()));
            } else {
                log.error("Invalid date provided:" + date);
            }
        } catch (InterruptedException e) {
            throw new ScriptException("Invalid Parameter Exception");
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (WebDriverException e) {
            throw new ScriptException("Element is not clickable at current point");
        }
    }

    /**
     * Select the the user selected date
     */
    public void selectLastDay() throws ApplicationException, ScriptException {
        try {
            Thread.sleep(1000);
            WebElement lastDayElement = ((new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath(getLastDayXpath()))));
            Thread.sleep(2000);
            lastDayElement.click();
        } catch (InterruptedException e) {
            throw new ScriptException("Invalid Parameter Exception");
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (WebDriverException e) {
            throw new ScriptException("Element is not clickable at current point");
        }
    }

    /**
     * Select the user provided year
     *
     * @param year
     */
    public void selectYear(Integer year) throws ApplicationException, ScriptException {
        try {
            Thread.sleep(1000);
            selectHeader();
            if (!isCalenderElementDisplayed(getYearTableXpath())) {
                selectHeader();
            }


            WebElement yearElement = ((new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath(getCalenderYearXpath(year)))));
            Thread.sleep(2000);
            yearElement.click();

        } catch (InterruptedException e) {
            log.error("Invalid year is provided for selection:" + year, e);
            throw new ScriptException("Invalid Parameter Exception");
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (WebDriverException e) {
            throw new ScriptException("Element is not clickable at current point");
        }

    }

    /**
     * Verify provided int is valid
     *
     * @param value
     * @return
     */
    public boolean isValidInteger(Integer value) {
        if (value > 0) {
            return true;
        }
        return false;
    }

    /**
     * Select a user given month
     *
     * @param month
     */
    public void selectMonth(Integer month) throws ApplicationException, ScriptException {
        try {
            Thread.sleep(1000);
            String monthText = getSelectedMonthInText(month);
            if (monthText != null) {
                WebElement monthElement = ((new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.elementToBeClickable(By
                                .xpath(getCalenderMonthXpath(monthText)))));
                Thread.sleep(2000);
                monthElement.click();
            }
        } catch (InterruptedException e) {
            log.error("Invalid month is provided for selection:" + month);
            throw new ScriptException("Invalid Parameter Exception");
        } catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        } catch (WebDriverException e) {
            throw new ScriptException("Element is not clickable at current point");
        }
    }

    /**
     * Convert the month integer to String
     *
     * @param providedMonth
     * @return
     */
    private String getSelectedMonthInText(int providedMonth) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                "Aug", "Sep", "Oct", "Nov", "Dec"};
        if ((providedMonth - 1) < months.length && providedMonth > 0) {
            return months[providedMonth - 1];
        } else {
            log.error("Incorrect integer is provided for month:"
                    + providedMonth);
            return null;
        }
    }

    /**
     * Get the common Xpath id of calendar element
     *
     * @return
     */
    private String getCalenderCommonXpath() {
        return "//div[contains(@class, 'datepicker-dropdown')]";
    }

    /**
     * Get the common Xpath id of calendar element
     *
     * @return
     */
    private String getVisibleCalenderPopUpXpath() {
        return "//div[contains(@class,'datepicker-') and not(contains(@style, 'display: none;'))]";
    }


    /**
     * Validate the user provided date
     *
     * @param date
     * @return
     */
    private boolean isvalidDateFormat(String date) {
        if (!(date.trim().isEmpty() && date.equals(null))) {
            if (date.contains("/")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Click on the calendar header to select year and month
     */
    private void selectHeader() {
        try {
            Thread.sleep(1000);
            WebElement headerElement = ((new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath(getHeaderXpath()))));
            Thread.sleep(2000);
            headerElement.click();
        } catch (Exception e) {
            log.error("Exception" + e);
        }
    }

    /**
     * Build the previous arrow element xpath
     *
     * @return
     */
    private String getPrevElementXpath() {
        return getCalenderCommonXpath() + getVisibleCalenderPopUpXpath() + "//th[contains(@class, '" + PREV_ARROW_CLASS + "')]";
    }

    /**
     * Build the calendar date xpath
     *
     * @param date
     * @return
     */
    private String getCalenderDateXpath(int date) {
        return getCalenderCommonXpath() + "//div[contains(@class,'datepicker-days')]"
                + "//td[(text()='" + date + "') and not(contains(@class, 'old day'))and not(contains(@class, 'new day'))]";
    }

    /**
     * Build xpath of calendar last date of the month
     *
     * @return
     */
    private String getLastDayXpath() {
        return "(//div[contains(@class,'datepicker-days')]"
                + "//td[not(contains(@class, 'old day')) and not(contains(@class, 'new day'))])"
                + "[last()]";
    }


    /**
     * Build xpath of next arrow element
     *
     * @return
     */
    private String getNextElementXpath() {
        return getCalenderCommonXpath() + getVisibleCalenderPopUpXpath() + "//th[contains(@class, '" + NEXT_ARROW_CLASS + "')]";
    }

    /**
     * Build xpath of title of the calendar
     *
     * @return
     */
    private String getHeaderXpath() {
        return getCalenderCommonXpath() + getVisibleCalenderPopUpXpath() + "//th[contains(@class, '" + CALANDER_HEADER_CLASS + "')]";
    }

    /**
     * Build xpath of year table
     *
     * @return
     */
    private String getYearTableXpath() {
        return "//div[contains(@class,'datepicker-year') and not(contains(@style, 'display: block;'))]";
    }

    /**
     * Build xpath of user selected year
     *
     * @param year
     * @return
     */
    private String getCalenderYearXpath(int year) {
        return getCalenderCommonXpath() + "//div[contains(@class,'datepicker-years')]"
                + "//td//span[(text()='" + year + "') and not(contains(@class, 'year old'))and not(contains(@class, 'year new'))]";
    }

    /**
     * Build xpath of user selected month
     *
     * @param month
     * @return
     */
    private String getCalenderMonthXpath(String month) {
        return getCalenderCommonXpath() + "//div[contains(@class,'datepicker-months')]"
                + "//td//span[(text()='" + month + "') and (contains(@class, 'month'))]";
    }

    /**
     * Verify if the selected element is displayed
     *
     * @param xpath
     * @return
     */
    private boolean isCalenderElementDisplayed(String xpath) {
        WebElement tempElement = driver.findElement(By.xpath(xpath));
        return tempElement.isDisplayed();
    }

}

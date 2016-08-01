package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.behavior.Clickable;
import com.framework.qa.webelementcore.elementbase.util.KeyBoard;
import com.framework.qa.webelementcore.util.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class BaseTable extends BaseElement {

    protected WebElement baseTableElement;
    protected RemoteWebDriver baseTableDriver;
    private KeyBoard keyBoard;
    private Clickable mouse;

    final static Logger log = Logger.getLogger(BaseTable.class);
    private static final String EXCEPTION = "exception :";

    /**
     * Table constructor
     *
     * @param baseTableDriver
     * @param locator
     */
    public BaseTable(RemoteWebDriver baseTableDriver, By locator) {
        super(baseTableDriver, locator);
        this.baseTableDriver = baseTableDriver;
        this.locator = locator;
        this.mouse = new Clickable();
    }

    //TODO- Remove method since baseElement has implementation
    /*@Override
    public void waitTillElementVisible(Integer maxWaitTime) {
        this.baseTableElement = (new WebDriverWait(baseTableDriver, (int) maxWaitTime / 1000)).until(ExpectedConditions.presenceOfElementLocated(locator));

    }*/


    public HashMap<String, String> getTableBodyContains(int row, int column) {
        HashMap tableData = new HashMap();
        WebElement table_element = driver.findElement(locator);
        List<WebElement> tr_collection = table_element.findElements(By.tagName("tr"));
        int size = tr_collection.size();
        int row_num, col_num;
        row_num = 1;
        ArrayList<String> tr_contains = new ArrayList<>();
        for (WebElement trElement : tr_collection) {
            System.out.println("row # " + row_num + "text=" + trElement.getText());
            List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
            col_num = 1;
            for (WebElement tdElement : td_collection) {
                System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
                tr_contains.add(tdElement.getText());
                String tr = "row-" + row_num + "_col-" + col_num;
                String td = tdElement.getText();
                tableData.put(tr, td);
                col_num++;
            }
            row_num++;
        }
        return tableData;
    }

    /**
     * Provide specific data cell element  ["row-x_col-x"]
     * @return
     */
    public HashMap<String, WebElement> getCellElement(){
        HashMap tableData = new HashMap();
        WebElement table_element = driver.findElement(locator);
        List<WebElement> tr_collection = table_element.findElements(By.tagName("tr"));
        int size = tr_collection.size();
        int row_num, col_num;
        row_num = 1;
        ArrayList<String> tr_contains = new ArrayList<>();
        for (WebElement trElement : tr_collection) {
            System.out.println("row # " + row_num + "text=" + trElement.getText());
            List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
            col_num = 1;
            for (WebElement tdElement : td_collection) {
                System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
                tr_contains.add(tdElement.getText());
                String tr = "row-" + row_num + "_col-" + col_num;
                WebElement td = tdElement;
                tableData.put(tr, td);
                col_num++;
            }
            row_num++;
        }
        return tableData;
    }

    /**
     * Provide specific data cell contain  ["row-x_col-x"]
     *
     * @return HashMap<String,String> Provide each and every cell under given data table
     */
    public HashMap<String, String> getTableBodyContains() {
        HashMap tableData = new HashMap();
        WebElement table_element = driver.findElement(locator);
        List<WebElement> tr_collection = table_element.findElements(By.tagName("tr"));
        int size = tr_collection.size();
        int row_num, col_num;
        row_num = 1;
        ArrayList<String> tr_contains = new ArrayList<>();
        for (WebElement trElement : tr_collection) {
            System.out.println("row # " + row_num + "text=" + trElement.getText());
            List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
            col_num = 1;
            for (WebElement tdElement : td_collection) {
                System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
                tr_contains.add(tdElement.getText());
                String tr = "row-" + row_num + "_col-" + col_num;
                String td = tdElement.getText();
                tableData.put(tr, td);
                col_num++;
            }
            row_num++;
        }
        return tableData;
    }

    /**
     * Provide specific data cell contain  ["row-x_col-x"] sorted
     *
     * @return HashMap<String,String> Provide each and every cell under given data table
     */
    public LinkedHashMap<String, String> getTableBodyContents() {
        LinkedHashMap tableData = new LinkedHashMap();
        WebElement table_element = driver.findElement(locator);
        List<WebElement> tr_collection = table_element.findElements(By.tagName("tr"));
        int size = tr_collection.size();
        int row_num, col_num;
        row_num = 1;
        ArrayList<String> tr_contains = new ArrayList<>();
        for (WebElement trElement : tr_collection) {
            System.out.println("row # " + row_num + "text=" + trElement.getText());
            List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
            col_num = 1;
            for (WebElement tdElement : td_collection) {
                System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
                tr_contains.add(tdElement.getText());
                String tr = "row-" + row_num + "_col-" + col_num;
                String td = tdElement.getText();
                tableData.put(tr, td);
                col_num++;
            }
            row_num++;
        }
        return tableData;
    }

    /**
     * Method returns all header row elements contains of the given table
     *
     * @return ArrayList<String> Table Header Contains
     * @since 05/28/2015
     */
    public ArrayList<String> getTableHeaderRowElementContains() {
        WebElement table_element = driver.findElement(locator);
        List<WebElement> th_collection = table_element.findElements(By.tagName("th"));
        int size = th_collection.size();
        int row_num = 1;
        row_num = 1;
        ArrayList<String> th_contains = new ArrayList<>();
        for (WebElement thElement : th_collection) {
            System.out.println("row # " + row_num + "text=" + thElement.getText());
            th_contains.add(thElement.getText());
            row_num++;
        }
        return th_contains;
    }

    /**
     * Method returns all row elements contains of the given table
     *
     * @param row
     * @return ArrayList<String> Table Row Contains
     */
    public ArrayList<String> getTableRowsElemetsContains(int row) {
        WebElement table_element = driver.findElement(locator);
        List<WebElement> tr_collection = table_element.findElements(By.tagName("tr"));
        int size = tr_collection.size();
        int row_num, col_num;
        row_num = 1;
        ArrayList<String> tr_contains = new ArrayList<>();
        for (WebElement trElement : tr_collection) {
            if (row_num == row) {
                List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
                col_num = 1;
                for (WebElement tdElement : td_collection) {
                    System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
                    tr_contains.add(tdElement.getText());
                    col_num++;
                }
            }
            row_num++;
        }
        return tr_contains;
    }

    /**
     * Method Return Given Type Elements Included Cell [Elements Existing Cell]
     *
     * @param row Specific Row Details
     * @param tag Element Type [TagName] That Need TO Get
     * @return Given Tag Existing Cell
     */
    public ArrayList<WebElement> getTableCellElementstList(int row, String tag) {
        WebElement table_element = driver.findElement(locator);
        List<WebElement> tr_collection = table_element.findElements(By.tagName("tr"));
        int size = tr_collection.size();
        int row_num, col_num;
        row_num = 1;
        ArrayList<WebElement> web_contains = new ArrayList<>();
        for (WebElement trElement : tr_collection) {
            if (row_num == row) {

                List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
                col_num = 1;
                for (WebElement tdElement : td_collection) {
                    List<WebElement> web_collection = tdElement.findElements(By.tagName(tag));
                    for (WebElement element : web_collection) {
                        web_contains.add(tdElement);
                        System.out.println("row # " + row_num + ", col # " + col_num + " " + tdElement.getText());
                    }
                    col_num++;
                }
            }
            row_num++;
        }
        return web_contains;
    }

    /**
     * Click on the table
     */

    public void click() throws ApplicationException, ScriptException {
        mouse.click(baseTableDriver, locator);
    }

    public void clickOnTableDataContainsText(String text) {
        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        try {
            WebElement ele = baseTableElement.findElement(By.xpath(splitLocator[1] + "//tr//*[text()='" + text + "']"));
            ((JavascriptExecutor) baseTableDriver).executeScript("arguments[0].scrollIntoView(true);", ele);
            ele.click();

        } catch (Exception e) {
            log.error("InterruptedException", e);
        }
    }

    /**
     * Click on the last row of a table
     */
    public void clickOnTableLastRow() {
        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> trCollection = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr"));
        int lastTableRowNumber = trCollection.size();
        if (lastTableRowNumber > 0) {
            WebElement tr = trCollection.get(lastTableRowNumber - 1);
            ((JavascriptExecutor) baseTableDriver).executeScript("arguments[0].scrollIntoView(true);", tr);
            tr.click();
        }
    }

    // Edited By Sachith
    public void memorizeTableText(Integer rowNumber, Integer tdNumber, String memKey) {

        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> trCollection = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr"));
        WebElement trElement = trCollection.get(rowNumber);
        List<WebElement> tdCollection = trElement.findElements(By.xpath("td"));
        WebElement tdElement = tdCollection.get(tdNumber);
        String memVal = tdElement.getText();
        WorkingMemory.getInstance().setMemory(memKey, memVal);

    }

    /**
     * Action to click on a specific table cell
     *
     * @param rowNumber to be clicked
     * @param tdNumber  to be clicked
     */
    public void clickOnTableCell(Integer rowNumber, Integer tdNumber) {

        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> trCollection = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr"));
        WebElement trElement = trCollection.get(rowNumber);
        List<WebElement> tdCollection = trElement.findElements(By.xpath("td"));
        WebElement tdElement = tdCollection.get(tdNumber);
        tdElement.click();

    }

    /**
     * Action to enter text to a specific table cell
     *
     * @param rowNumber to be clicked
     * @param tdNumber  to be clicked
     */
    public void enterTextToTableCell(Integer rowNumber, Integer tdNumber, String text) {

        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> trCollection = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr"));
        WebElement trElement = trCollection.get(rowNumber);
        List<WebElement> tdCollection = trElement.findElements(By.xpath("td"));
        WebElement tdElement = tdCollection.get(tdNumber);
        tdElement.click();
        try {
            this.keyBoard = new KeyBoard();
        } catch (AWTException e) {
            log.error(EXCEPTION, e);
        }
        keyBoard.clearTextField();
        keyBoard.typeText(text);

    }

    /**
     * Action to enter text to a specific table cell while memorizing
     *
     * @param rowNumber to be clicked
     * @param tdNumber  to be clicked
     */
    public void enterTextToTableCellWhileMemorizing(Integer rowNumber, Integer tdNumber, String text, String key) {

        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> trCollection = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr"));
        WebElement trElement = trCollection.get(rowNumber);
        List<WebElement> tdCollection = trElement.findElements(By.xpath("td"));
        WebElement tdElement = tdCollection.get(tdNumber);
        tdElement.click();
        WorkingMemory wMem = WorkingMemory.getInstance();
        wMem.setMemory(key, text);
        tdElement.click();
        try {
            this.keyBoard = new KeyBoard();
        } catch (AWTException e) {
            log.error(EXCEPTION, e);
        }
        keyBoard.clearTextField();
        keyBoard.typeText(text);
    }

    public void clickOnTableDataContainsMemoryKey(String memoryKey) {
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);

        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> trCollection = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr"));
        boolean found = false;
        for (WebElement trElement : trCollection) {
            if (found) {
                break;
            }
            List<WebElement> tdCollection = trElement.findElements(By.xpath("td"));
            for (WebElement tdElement : tdCollection) {
                if (tdElement.getText().equalsIgnoreCase(memVal)) {
                    ((JavascriptExecutor) baseTableDriver).executeScript("arguments[0].scrollIntoView(true);", tdElement);
                    tdElement.click();
                    found = true;
                    break;
                }

            }

        }
    }

    public boolean verifyTableDataContainsText(String text) {
        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> ele = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr//*[contains(text(), '" + text + "')]"));
        if (!ele.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean verifyTableDataNotContainsText(String text) {

        return !(verifyTableDataContainsText(text));
    }

    public boolean verifyTableColumnDataContainsText(Integer column, String text) {
        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> trCollection = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr"));
        boolean found = false;
        for (WebElement trElement : trCollection) {
            if (found) {
                break;
            }
            try {
                List<WebElement> tdCollection = trElement.findElements(By.xpath("td"));

                if (tdCollection.get(column).getText().contains(text)) {
                    found = true;
                }
            } catch (Exception e) {
                log.error(EXCEPTION, e);
            }
        }

        return found;
    }

    public boolean verifyTableColumnDataNotContainsText(Integer column, String text) {

        return !(verifyTableColumnDataContainsText(column, text));
    }

    /**
     * Verifies the given table contains a text stored in memory
     *
     * @param memoryKey
     * @return true if it contains the text
     */
    public boolean verifyTableDataContainsMemorizedText(String memoryKey) {
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> ele = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr//*[contains(text(), '" + memVal + "')]"));
        if (!ele.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Verify the BaseTableElement is disabled. return <CODE>true</CODE> the BaseTableElement is
     * disabled.
     */
    @Override
    public boolean isDisabled() {
        this.baseTableElement = (new WebDriverWait(baseTableDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (!baseTableElement.isEnabled()) {
            return true;
        } else if (baseTableElement.getAttribute("disabled") != null) {
            return true;
        } else if (baseTableElement.getAttribute("class").contains("rtbDisabled")) {
            return true;
        }

        return false;
    }

    /**
     * Verify if the BaseTableElement is displayed in current context.
     *
     * @return <CODE>true</CODE> the BaseTableElement is displayed in current context.
     */
    @Override
    public boolean isDisplayed() {
        boolean status = false;
        try {
            this.baseTableElement = (new WebDriverWait(baseTableDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
            status = baseTableElement.isDisplayed();
        } catch (NoSuchElementException e) {
            log.error("exception :", e);
            throw new ApplicationException("No such element with expression : " +locator);
        } finally {
            return status;
        }
    }

    /**
     * Verify if the BaseTableElement is displayed in current context.
     *
     * @return <CODE>true</CODE> the BaseTableElement is displayed in current context.
     */
    @Override
    public boolean isNotDisplayed() {
        return !(isDisplayed());
    }

    /**
     * @ret Verify if the BaseTableElement is enabled. return <CODE>true</CODE> the
     * BaseTableElement is enabled.
     */
    @Override
    public boolean isEnabled() {
        this.baseTableElement = (new WebDriverWait(baseTableDriver, Constant.TIMEOUT_IMPLICIT / 1000)).until(ExpectedConditions.elementToBeClickable(locator));
        if (baseTableElement.getAttribute("class").contains("rtbDisabled")) {
            return false;
        }
        return baseTableElement.isEnabled();
    }

    /**
     * Verifies whether the BaseTableElement exists.
     *
     * @return <code>true</code> if the BaseTableElement is found in the current context.
     */
    @Override
    public boolean verifyElementExists() {
        return !baseTableDriver.findElements(locator).isEmpty();
    }

    /**
     * @param key
     */
    public void memorizedNumberOfRowsInTable(String key) {
        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> checkboxCollection = baseTableElement.findElements(By.xpath(splitLocator[1] + "/tbody/tr"));

        WorkingMemory wMem = WorkingMemory.getInstance();
        wMem.setMemory(key, Integer.toString(checkboxCollection.size()));
    }

    public String getTextOfACell(Integer row, Integer column) {

        String text = "";

        baseTableElement = baseTableDriver.findElement(locator);
        String tableXpath = locator.toString();
        String[] splitLocator = tableXpath.split("By.xpath: ");
        List<WebElement> tableRowCollection = baseTableElement.findElements(By.xpath(splitLocator[1] + "//tr"));
        WebElement tableRow = tableRowCollection.get(row - 1);
        List<WebElement> tableCellCollection = tableRow.findElements(By.xpath("td"));
        WebElement tableCell = tableCellCollection.get(column - 1);
        text = tableCell.getText();
        return text;
    }

}

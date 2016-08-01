package com.framework.qa.webelementcore.elementbase.core;

import com.framework.qa.utils.exception.ApplicationException;
import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;

import java.util.List;

public class BaseUnorderedList extends BaseElement {

    protected WebElement baseUnorderedListElement;
    protected RemoteWebDriver baseUnorderedListDriver;
    final static Logger log = Logger.getLogger(BaseUnorderedList.class);

    public BaseUnorderedList(RemoteWebDriver baseUnorderedListDriver, By locator) {
        super(baseUnorderedListDriver, locator);
        this.baseUnorderedListDriver = baseUnorderedListDriver;
        this.locator = locator;

    }

    /**
     * Verify whether list is containing mentioned option or not.
     */
    public boolean verifyListContainsOption(String option) {

        List<WebElement> allElements = findElements(baseUnorderedListDriver.findElement(locator));

        for (WebElement item : allElements) {
            if (item.getText().toLowerCase().contains(option.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Verify whether list is containing memorized option or not.
     */
    public boolean verifyListContainsMemorizedOption(String memoryKey) {
        String memVal = WorkingMemory.getInstance().getMemory(memoryKey);

        if (memVal == null) {
            return false;
        }

        List<WebElement> allElements = baseUnorderedListDriver.findElements(locator);

        for (WebElement item : allElements) {
            if (item.getText().trim().toLowerCase().contains(memVal.toLowerCase().trim())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Performs a click on the first baseUnorderedListElement.
     */

    public void clickFirstOption() throws ApplicationException, ScriptException {
        try{
            baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);

            List<WebElement> links = findElements(baseUnorderedListElement);

            for (WebElement item : links) {
                if (item.isDisplayed() && !item.getText().isEmpty()) {
                    item.click();
                    return;
                }
            }

        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }
    }

    /**
     * Performs a click on the first baseUnorderedListElement and memorize.
     */

    public void clickAndMemorizedFirstOption(String memoryKey) throws ApplicationException, ScriptException {
        try{
            baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);

            List<WebElement> links = findElements(baseUnorderedListElement);

            for (WebElement item : links) {
                if (item.isDisplayed() && !item.getText().isEmpty()) {
                    WorkingMemory wMem = WorkingMemory.getInstance();
                    String memText = item.getText();
                    memText = memText.replaceAll("\\(.*\\)", "");
                    wMem.setMemory(memoryKey, memText);

                    item.click();

                    return;
                }
            }

        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }
    }

    /**
     * Performs a click on the baseUnorderedListElement with the given text.
     */

    public void clickOption(String option) throws ApplicationException, ScriptException {
        try{
            baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);

            List<WebElement> links = findElements(baseUnorderedListElement);

            for (WebElement item : links) {
                if (item.getText().trim().toLowerCase().contains(option.trim().toLowerCase())) {
                    item.click();
                    return;
                }
            }

        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }
    }

    /**
     * Performs a click on the baseUnorderedListElement with the given text and
     * memorize.
     */

    public void clickAndMemorizedSelectedOption(String option, String memoryKey) throws ApplicationException, ScriptException {
        try{
            baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);

            List<WebElement> links = findElements(baseUnorderedListElement);

            for (WebElement item : links) {
                if (item.getText().trim().toLowerCase().contains(option.trim().toLowerCase())) {
                    item.click();
                    WorkingMemory wMem = WorkingMemory.getInstance();
                    wMem.setMemory(memoryKey, option);
                    return;
                }
            }

        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }
    }

    /**
     * Performs a click on a random baseUnorderedListElement.
     */

    public void clickRandomOption() throws ApplicationException, ScriptException {
        try{
            baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);

            List<WebElement> links = findElements(baseUnorderedListElement);

            int max = links.size() - 1;

            int rand = generateRandomNumber(max, 0);
            WebElement randitem = links.get(rand);

            while (!randitem.isDisplayed()) {
                rand = generateRandomNumber(max, 0);
                randitem = links.get(rand);
            }

            randitem.click();

        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }
    }

    /**
     * Identify the baseUnorderedListElement list inside the ul tag
     */
    private List<WebElement> findElements(WebElement ele) {
        List<WebElement> links = ele.findElements(By.xpath(".//li"));
        if (links.isEmpty()) {
            links = ele.findElements(By.xpath(".//a"));
        }
        if (links.isEmpty()) {
            links = ele.findElements(By.xpath(".//div"));
        }
        return links;
    }

    /**
     * Generating random integer number to select random option
     */
    protected int generateRandomNumber(int max, int min) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    /**
     * Performs a click on a random baseUnorderedListElement and memorize.
     */

    public void clickAndMemorizedSelectedRandomOption(String memoryKey) throws ApplicationException, ScriptException {
        try{
            baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);

            List<WebElement> links = findElements(baseUnorderedListElement);

            int max = links.size() - 1;

            int rand = generateRandomNumber(max, 0);
            WebElement randitem = links.get(rand);

            while (!randitem.isDisplayed() || randitem.getText().isEmpty()) {
                rand = generateRandomNumber(max, 0);
                randitem = links.get(rand);
            }

            WorkingMemory wMem = WorkingMemory.getInstance();
            String memText = randitem.getText();
            memText = memText.replaceAll("\\(.*\\)", "");
            wMem.setMemory(memoryKey, memText);

            randitem.click();

        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }
    }

    /**
     * Performs a click on the baseUnorderedListElement of the given index.
     */

    public void clickOptionByIndex(Integer index) throws ApplicationException, ScriptException {
        try{
            baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);

            List<WebElement> links = findElements(baseUnorderedListElement);
            WebElement item = links.get(index - 1);

            if (item.isDisplayed() || !item.getText().isEmpty()) {
                item.click();
            }

        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }
    }

    /**
     * Performs a click on the baseUnorderedListElement of the given index and
     * memorize.
     */

    public void clickAndMemorizeOptionByIndex(Integer index, String memoryKey) throws ApplicationException, ScriptException {
        try{
            baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);

            List<WebElement> links = findElements(baseUnorderedListElement);
            WebElement item = links.get(index - 1);

            if (item.isDisplayed() || !item.getText().isEmpty()) {

                WorkingMemory wMem = WorkingMemory.getInstance();
                String memText = item.getText();
                memText = memText.replaceAll("\\(.*\\)", "");
                wMem.setMemory(memoryKey, memText);

                item.click();
            }

        }catch (TimeoutException e) {
            throw new ApplicationException("Time out after waiting for element");
        } catch (ElementNotVisibleException e) {
            throw new ScriptException("Element Not Clickable");
        } catch (InvalidElementStateException e) {
            throw new ApplicationException("Element Not editable");
        } catch (StaleElementReferenceException e) {
            throw new ScriptException("Element No longer attached to page DOM");
        }
    }

    public String getTextByIndex(Integer index) {
        String text = "";
        baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);
        List<WebElement> list = findElements(baseUnorderedListElement);
        WebElement element = list.get(index - 1);
        text = element.getText();

        return text;
    }

    /**
     * Return size of the list
     *
     * @return size of the list.
     */
    public int getListSize() {
        baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);

        List<WebElement> links = findElements(baseUnorderedListElement);
        return links.size();
    }

    /**
     * Return visibility of given index in list
     *
     * @param index
     * @return visibility of given index in list
     */
    public boolean getVisibilityByIndex(Integer index) {
        baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);
        List<WebElement> list = findElements(baseUnorderedListElement);
        WebElement element = list.get(index - 1);
        if (element.isDisplayed()) {
            return true;
        }
        return false;
    }

    /**
     * Return true if given index is already selected in list
     *
     * @param index
     * @param attributeName
     * @param attributevalue
     * @return return true if given index is match with attributname and value
     */
    public boolean verifyIndexIsAlreadySelected(Integer index, String attributeName, String attributevalue) {
        baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);
        List<WebElement> list = findElements(baseUnorderedListElement);
        WebElement element = list.get(index - 1);
        String result = element.getAttribute(attributeName);
        if (result.contains(attributevalue)) {
            return true;
        }
        return false;
    }

    /**
     * Click last visible option in given list
     */
    public void clickLastVisibleOption() {
        baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);
        List<WebElement> list = findElements(baseUnorderedListElement);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).isDisplayed()) {
                list.get(i).click();
                return;
            }
        }
    }

    /**
     * Verify whether full list is containing mentioned css attribute value or not.
     */
    public boolean verifyFullListContainsAttributeValue(String attribute, String option) {
        boolean attributeOption = true;
        List<WebElement> allElements = findElements(baseUnorderedListDriver.findElement(locator));

        for (WebElement item : allElements) {
            if (!(item.getAttribute(attribute).toLowerCase().contains(option.toLowerCase()))) {
                attributeOption = false;
            }
        }

        return attributeOption;
    }

    /**
     * Verify whether list is containing mentioned css attribute value or not.
     */
    public boolean verifyListContainsAttributeValue(String attribute, String option) {
        boolean attributeOption = false;
        List<WebElement> allElements = findElements(baseUnorderedListDriver.findElement(locator));

        for (WebElement item : allElements) {
            if (item.getAttribute(attribute).toLowerCase().contains(option.toLowerCase())) {
                attributeOption = true;
                return attributeOption;
            }
        }

        return attributeOption;
    }

    /**
     * Use to get all the list elements
     *
     * @return Web element list
     */
    public List<WebElement> getElements() {
        baseUnorderedListElement = baseUnorderedListDriver.findElement(locator);
        List<WebElement> list = findElements(baseUnorderedListElement);
        return list;
    }

}

package com.framework.qa.webelementcore.elementbase.core;


import com.framework.qa.utils.exception.ScriptException;
import com.framework.qa.utils.memory.WorkingMemory;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseTextElement {
    protected RemoteWebDriver driver;
    private String evaluatedValue;
    final static Logger log = Logger.getLogger(BaseTextElement.class);

    private static final String PARAM_ERROR = "Invalid Param Details Passed";

    /**
     * Initialize the element object which is dedicated for performing String
     * operations. (which are not associated with elements.)
     *
     * @param driver
     * @param locator .
     * @throws AWTException .
     */
    public BaseTextElement(RemoteWebDriver driver, By locator) throws AWTException {
        super();
        this.driver = driver;
    }

    public String getEvaluatedValue() {
        return this.evaluatedValue;
    }

    public void setEvaluatedValue(String value) {
        this.evaluatedValue = value;
    }

    /**
     * Split a given String by specified index and store the array in the memory
     *
     * @param text
     * @param startIndex
     * @param endIndex
     * @param memoryKey
     * @throws ScriptException
     */

    public void splitByIndex(String text, String startIndex, String endIndex,
                             String memoryKey) throws ScriptException {

        Integer start;
        Integer end;
        String splittedText = "";

        if (validParameters(memoryKey, startIndex, endIndex, text)) {

            try {
                start = Integer.parseInt(startIndex);

                if (start == 0) {
                    throw new ScriptException(PARAM_ERROR);
                }
                end = Integer.parseInt(endIndex);
                splittedText = text.substring(start, end);

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new ScriptException(PARAM_ERROR);
            }

            start = start - 1;
            splittedText = text.substring(start, end);

            String[] stringArray = new String[1];
            stringArray[0] = splittedText;
            setMemoryArray(memoryKey, stringArray);
        }

    }

    /**
     * Split a memorized String by specified index and store the array in the
     * memory
     *
     * @param memorizedKey
     * @param startIndex
     * @param endIndex
     * @param memoryKey
     * @throws ScriptException
     */
    public void splitMemorizedTextByIndex(String memorizedKey,
                                          String startIndex, String endIndex, String memoryKey)
            throws ScriptException {

        WorkingMemory workingMemory = WorkingMemory.getInstance();

        if (validParameters(memorizedKey, memoryKey, startIndex, endIndex)) {

            String textToSplit = workingMemory.getMemory(memorizedKey);

            if (validParameters(textToSplit)) {

                this.splitByIndex(textToSplit, startIndex, endIndex, memoryKey);

            }
        }

    }

    /**
     * Split a string into characters and store it in the working memory as an
     * array
     *
     * @param text
     * @param memoryKey
     * @throws ScriptException
     */
    public void splitIntoCharacters(String text, String memoryKey)
            throws ScriptException {
        if (validParameters(text, memoryKey)) {
            String[] array = new String[text.length()];
            for (int i = 0; i < text.length(); i++) {
                array[i] = Character.toString(text.charAt(i));
            }
            setMemoryArray(memoryKey, array);
        }
    }

    /**
     * Split a memorized string into characters and store it in the working
     * memory as an array
     *
     * @param memorizedKey
     * @param memoryKey
     * @throws ScriptException
     */
    public void splitMemorizedTextIntoCharacters(String memorizedKey,
                                                 String memoryKey) throws ScriptException {
        splitIntoCharacters(
                WorkingMemory.getInstance().getMemory(memorizedKey), memoryKey);
    }

    /**
     * Split a string by user provided delimiter and store it in the working
     * memory as an array
     *
     * @param text
     * @param delimiter
     * @param memoryKey
     * @throws ScriptException
     */
    public void splitByDelimiter(String text, String delimiter, String memoryKey)
            throws ScriptException {
        if (validParameters(text, memoryKey)) {
            if (StringUtils.isNotEmpty(delimiter)) {
                String[] data = text.split(Pattern.quote(delimiter));
                setMemoryArray(memoryKey, data);
            } else {
                throw new ScriptException(
                        "Invalid Param Details Passed for Delimiter : " + delimiter);
            }
        }
    }

    /**
     * Split a memorized string by user provided delimiter and store it in the
     * working memory as an array
     *
     * @param memorizedKey
     * @param delimiter
     * @param memoryKey
     * @throws ScriptException
     */
    public void splitMemorizedTextByDelimiter(String memorizedKey,
                                              String delimiter, String memoryKey)
            throws ScriptException {
        splitByDelimiter(WorkingMemory.getInstance().getMemory(memorizedKey),
                delimiter, memoryKey);
    }

    /**
     * Extract the digits in a string and store it in the working memory as an
     * array
     *
     * @param text
     * @param memoryKey
     * @throws ScriptException
     */
    public void splitByDigits(String text, String memoryKey)
            throws ScriptException {
        if (validParameters(text, memoryKey)) {

            String patternString = "((^?-[0-9]*\\.?[0-9]+)|([0-9]*\\.?[0-9]+))";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(text);

            List<String> stringArrayList = new ArrayList<String>();
            while (matcher.find()) {
                String str = matcher.group(1);
                stringArrayList = createArray(str, stringArrayList);
            }

            String[] array = new String[stringArrayList.size()];
            array = stringArrayList.toArray(array);
            setMemoryArray(memoryKey, array);
        }
    }

    private List<String> createArray(String str, List<String> stringArrayList) {
        if (!str.contains(".")) {
            for (int i = 0; i < str.length(); i++) {
                stringArrayList.add(Character.toString(str.charAt(i)));
            }

        } else {
            stringArrayList.add(str);
        }

        if (stringArrayList.contains("-")) {
            int negIndex = stringArrayList.indexOf("-");
            int numIndex = negIndex + 1;
            String sign = "-";
            String negNumber = sign.concat(stringArrayList.get(numIndex));
            stringArrayList.remove(negIndex);
            stringArrayList.set(negIndex, negNumber);

        }

        return stringArrayList;
    }

    /**
     * Extract the digits in a memorized string and store it in the working
     * memory as an array
     *
     * @param memorizedKey
     * @throws ScriptException
     */
    public void splitMemorizedTextByDigits(String memorizedKey, String memoryKey)
            throws ScriptException {

        WorkingMemory workingMemory = WorkingMemory.getInstance();
        String text = workingMemory.getMemory(memorizedKey);

        splitByDigits(text, memoryKey);
    }

    /**
     * Get the memorized array index value to a String and store in the memory.
     *
     * @param memorizedKey
     * @param memoryKey
     * @throws ScriptException
     * @throws NumberFormatException
     */
    public void getMemorizedArrayValue(String memorizedKey, String index,
                                       String memoryKey) throws ScriptException {
        try {
            if (validParameters(memorizedKey, index, memoryKey)) {
                String[] array = WorkingMemory.getInstance().getMemoryArray(
                        memorizedKey);
                int arrayIndex = Integer.parseInt(index) - 1;
                if (array != null && arrayIndex <= array.length) {
                    setMemory(memoryKey, array[arrayIndex]);
                } else {
                    throw new ScriptException(
                            "Invalid Param Details Passed for Array : "
                                    + memorizedKey + "[" + index + "]");
                }
            }
        } catch (Exception e) {
            log.error("exception occured at getMemorizedArrayValue" + e);
            throw new ScriptException(
                    "Invalid Param Details Passed for Array : " + memorizedKey
                            + "[" + index + "]");
        }

    }


    /**
     * get Character of a memorized string  at a user provided index
     *
     * @param memorizedText
     * @param index
     * @param memoryKey
     * @throws ScriptException
     */
    public void getCharacterAtPosition(String memorizedText, String index,
                                       String memoryKey) throws ScriptException {
        try {
            if (validParameters(memorizedText, index, memoryKey)) {
                String text = WorkingMemory.getInstance().getMemory(memorizedText);
                int passedIndex = Integer.parseInt(index) - 1;
                setMemory(memoryKey, Character.toString(text.charAt(passedIndex)));
            }

        } catch (Exception e) {
            log.error("exception occured at getCharacterAtPosition" + e);
            throw new ScriptException(
                    PARAM_ERROR + " : "
                            + memorizedText + "[" + index + "]");
        }

    }

    /**
     * get Character of a memorized string  at a memorized index
     *
     * @param memorizedText
     * @param memorizedIndex
     * @param memoryKey
     * @throws ScriptException
     */
    public void getCharacterAtMemorizedPosition(String memorizedText, String memorizedIndex,
                                                String memoryKey) throws ScriptException {
        WorkingMemory workingMemory = WorkingMemory.getInstance();
        getCharacterAtPosition(memorizedText, workingMemory.getMemory(memorizedIndex), memoryKey);
    }

    /**
     * Concatenate two Strings and input the value into the working memory
     *
     * @param firstString
     * @param secondString
     * @param memoryKey
     * @throws ScriptException
     */
    public void concatenateTexts(String firstString, String secondString,
                                 String memoryKey) throws ScriptException {
        if (validParameters(memoryKey)) {
            StringBuilder text = new StringBuilder();
            if (StringUtils.isNotBlank(firstString)) {
                text.append(firstString);
            }
            if (StringUtils.isNotBlank(secondString)) {
                text.append(secondString);
            }
            setMemory(memoryKey, text.toString());
        }
    }

    /**
     * Concatenate two memorized Strings and input the value into the working
     * memory
     *
     * @param fisrtMemorizedKey
     * @param secondMemorizedKey
     * @param memoryKey
     * @throws ScriptException
     */
    public void concatenateMemorizedTexts(String fisrtMemorizedKey,
                                          String secondMemorizedKey, String memoryKey)
            throws ScriptException {
        if (validParameters(
                WorkingMemory.getInstance().getMemory(fisrtMemorizedKey),
                WorkingMemory.getInstance().getMemory(secondMemorizedKey))) {
            String text = new StringBuilder(WorkingMemory.getInstance()
                    .getMemory(fisrtMemorizedKey)).append(
                    WorkingMemory.getInstance().getMemory(secondMemorizedKey))
                    .toString();
            setMemory(memoryKey, text);
        }
    }

    private void setMemoryArray(String memoryKey, String[] array) {
        if (null != array) {
            WorkingMemory workingMem = WorkingMemory.getInstance();
            workingMem.setMemoryArray(memoryKey, array);
        }
    }

    private void setMemory(String memoryKey, String value) {
        if (null != value) {
            WorkingMemory workingMem = WorkingMemory.getInstance();
            workingMem.setMemory(memoryKey, value);
        }
    }

    private boolean validParameters(String... parameter)
            throws ScriptException {
        for (String param : parameter) {
            if (StringUtils.isBlank(param)) {
                throw new ScriptException("User Provided Parameter"
                        + param + " Null/Empty exception");
            }
        }
        return true;
    }

}

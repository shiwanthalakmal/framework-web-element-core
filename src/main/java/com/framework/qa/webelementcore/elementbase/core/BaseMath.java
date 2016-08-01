package com.framework.qa.webelementcore.elementbase.core;


import com.framework.qa.utils.memory.WorkingMemory;
import com.framework.qa.webelementcore.elementbase.util.Expression;
import com.framework.qa.webelementcore.elementbase.util.MathExpressionProcessor;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;

/**
 * Performs mathematical calculations
 *
 * @author Shiwantha Lakmal
 */

public abstract class BaseMath {
    protected RemoteWebDriver driver;
    private MathExpressionProcessor math;
    private String evaluatedValue;

    final static Logger log = Logger.getLogger(BaseMath.class);

    public String getEvaluatedValue() {
        return this.evaluatedValue;
    }

    public void setEvaluatedValue(String value) {
        this.evaluatedValue = value;
    }

    public BaseMath(RemoteWebDriver driver, By locator) {
        super();
        this.driver = driver;
        math = new MathExpressionProcessor();
    }

    /**
     * parses a symbolized mathematical expression, memorizes the result with
     * the given memory key and returns the result
     *
     * @param exp       Expression object containing symbolized math expression
     * @param memoryKey memory key
     */
    public void evaluateExpressionAndMemorize(Expression exp, String memoryKey) {
        String expString = exp.getExp();
        String mathExp = math.parseSymbolExp(expString);
        setEvaluatedValue(mathExp);
        String result = math.evaluate(mathExp);
        WorkingMemory wMem = WorkingMemory.getInstance();
        wMem.setMemory(memoryKey, result);
    }

    /**
     * formats the math result saved with the given memory key to a user given
     * decimal format
     *
     * @param memoryKey key for the math result value
     * @param format    decimal format
     */
    public void format(String memoryKey, String format) {
        WorkingMemory wMem = WorkingMemory.getInstance();
        String memVal = wMem.getMemory(memoryKey);
        String formattedVal = math.formatNumberToCustomFormat(memVal, format);
        wMem.setMemory(memoryKey, formattedVal);
    }


    /**
     * verify whether the given value is equals to memorized numerical value
     *
     * @param memoryKey key of the memorized numerical value
     * @param value     value that needs to be compared
     */
    public boolean verifyMemorizedNumericalValueIs(String memoryKey, String value) {
        WorkingMemory wMem = WorkingMemory.getInstance();
        String memorizedValue = wMem.getMemory(memoryKey);
        return math.compareNumericalValues(memorizedValue, value);
    }

    /**
     * verify whether the given value of the memory key is equals to memorized numerical value
     *
     * @param memoryKeyNeedsToCompare key of the memorized numerical value that needs to be compared
     */
    public boolean verifyMemorizedNumericalValueEqualsTo(String memoryKey, String memoryKeyNeedsToCompare) {
        WorkingMemory wMem = WorkingMemory.getInstance();
        String memorizedValue = wMem.getMemory(memoryKey);
        String valueNeedsToCompare = wMem.getMemory(memoryKeyNeedsToCompare);

        return math.compareNumericalValues(memorizedValue, valueNeedsToCompare);
    }
}

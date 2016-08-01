package com.framework.qa.webelementcore.elementbase.util;

import com.framework.qa.utils.memory.WorkingMemory;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.apache.log4j.Logger;
import com.framework.qa.webelementcore.elementbase.util.MathExpressionSymbol.ShortCode;
import com.framework.qa.webelementcore.elementbase.util.MathExpressionSymbol.Operand;
import com.framework.qa.webelementcore.elementbase.util.MathExpressionSymbol.Operation;
import com.framework.qa.webelementcore.elementbase.util.MathExpressionSymbol.Function;

import java.text.DecimalFormat;

/**
 * @author Shiwantha Lakmal
 *         <p/>
 *         Process symbolized math expressions and outputs relevant mathematical
 *         expressions
 */
public class MathExpressionProcessor {

    private String symbolString;
    final static Logger log = Logger.getLogger(MathExpressionProcessor.class);

    /**
     * returns the mathematical expression from a symbolized expression
     *
     * @param symbolStr symbolized math expression
     * @return
     */
    public String parseSymbolExp(String symbolStr) {
        String exp = "";
        try {
            exp = generateMathExpression(symbolStr);
        } catch (IllegalArgumentException iae) {
            log.error("Invalid or empty expression!", iae);
        } catch (Exception e) {
            log.error("exception", e);
        }

        return exp;
    }

    private String generateMathExpression(String symbolStr) {
        String exp;
        StringBuilder mathExp = new StringBuilder();
        int startIndex = 0;
        int endIndex = 1;
        int valueEndIndex = 1;
        String symbolSubString;
        this.symbolString = symbolStr.trim();
        int symbolStringLength = symbolString.length();

        while (endIndex <= symbolStringLength) {
            symbolSubString = symbolString.substring(startIndex, endIndex);

            ShortCode shortCodeMatch = shortCodeMatch(symbolSubString, ShortCode.values());

            switch (shortCodeMatch) {
                case PARENTHESIS_START:
                    valueEndIndex = handleParanthesisStart(mathExp, startIndex) + startIndex;
                    startIndex = valueEndIndex + 1;
                    endIndex = startIndex + 1;
                    continue;
                case PARENTHESIS_END:
                    valueEndIndex = handleParanthesisEnd(mathExp, startIndex) + startIndex;
                    startIndex = valueEndIndex + 1;
                    endIndex = startIndex + 1;
                    continue;
                case NEWLINE:
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case SEPARATOR:
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case COMMA:
                    mathExp.append(ShortCode.COMMA.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                default:
                    // do nothing
            }

            Operand operandMatch = operandMatch(symbolSubString, Operand.values());

            switch (operandMatch) {
                case VALUE:
                    valueEndIndex = handleOperandValue(mathExp, startIndex);
                    startIndex = valueEndIndex + 1;
                    endIndex = startIndex + 1;
                    continue;
                case ACTION_PARAMETER:
                    valueEndIndex = handleOperandActionParameter(mathExp, startIndex);
                    startIndex = valueEndIndex + 1;
                    endIndex = startIndex + 1;
                    continue;
                case MEMORY_KEY:
                    valueEndIndex = handleOperandMemory(mathExp, startIndex);
                    startIndex = valueEndIndex + 1;
                    endIndex = startIndex + 1;
                    continue;
                case ELEMENT_ACTION:
                    valueEndIndex = handleOperandElementAction(mathExp, startIndex);
                    startIndex = valueEndIndex + 1;
                    endIndex = startIndex + 1;
                    continue;
                case FUNCTION:
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                default:
                    // do nothing
            }

            Operation operationMatch = operationMatch(Operation.values(), symbolSubString);
            switch (operationMatch) {
                case NULL:
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case ADDITION:
                    mathExp.append(Operation.ADDITION.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case SUBTRACTION:
                    mathExp.append(Operation.SUBTRACTION.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case MULTIPLICATION:
                    mathExp.append(Operation.MULTIPLICATION.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case DIVISION:
                    mathExp.append(Operation.DIVISION.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case EXPONENTIATION:
                    mathExp.append(Operation.EXPONENTIATION.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case MODULO:
                    mathExp.append(Operation.MODULO.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                default:
                    // do nothing
            }

            Function functionMatch = functionMatch(Function.values(), symbolSubString);
            switch (functionMatch) {
                case ABSOLUTE_VALUE:
                    mathExp.append(Function.ABSOLUTE_VALUE.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case ARC_COSINE:
                    mathExp.append(Function.ARC_COSINE.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case ARC_SINE:
                    mathExp.append(Function.ARC_SINE.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case ARC_TANGENT:
                    mathExp.append(Function.ARC_TANGENT.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case CUBIC_ROOT:
                    mathExp.append(Function.CUBIC_ROOT.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case NEAREST_UPPER_INTEGER:
                    mathExp.append(Function.NEAREST_UPPER_INTEGER.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case COSINE:
                    mathExp.append(Function.COSINE.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case HYPERBOLIC_COSINE:
                    mathExp.append(Function.HYPERBOLIC_COSINE.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case EULERS_NUMBER_RAISED_TO_E_POWER_X:
                    mathExp.append(Function.EULERS_NUMBER_RAISED_TO_E_POWER_X.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case NEAREST_LOWER_INTEGER:
                    mathExp.append(Function.NEAREST_LOWER_INTEGER.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case LOGARITHMUS_NATURALIS_BASE_E:
                    mathExp.append(Function.LOGARITHMUS_NATURALIS_BASE_E.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case LOGARITHM_BASE_10:
                    mathExp.append(Function.LOGARITHM_BASE_10.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case LOGARITHM_BASE_2:
                    mathExp.append(Function.LOGARITHM_BASE_2.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case SINE:
                    mathExp.append(Function.SINE.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case HYPERBOLIC_SINE:
                    mathExp.append(Function.HYPERBOLIC_SINE.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case SQUARE_ROOT:
                    mathExp.append(Function.SQUARE_ROOT.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case TANGENT:
                    mathExp.append(Function.TANGENT.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                case HYPERBOLIC_TANGENT:
                    mathExp.append(Function.HYPERBOLIC_TANGENT.getValue());
                    startIndex = endIndex;
                    endIndex++;
                    continue;
                default:
                    endIndex++;

            }

        }

        exp = mathExp.toString();
        if (exp.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return exp;
    }

    /**
     * @param mathExp
     * @param startIndex
     * @return
     */
    private int handleParanthesisEnd(StringBuilder mathExp, int startIndex) {
        int valueEndIndex;
        mathExp.append(ShortCode.PARENTHESIS_END.getValue());
        valueEndIndex = symbolString.substring(startIndex).indexOf("]");
        if (valueEndIndex == -1) {
            throw new IllegalArgumentException();
        }
        return valueEndIndex;
    }

    /**
     * @param mathExp
     * @param startIndex
     * @return
     */
    private int handleParanthesisStart(StringBuilder mathExp, int startIndex) {
        int valueEndIndex;
        mathExp.append(ShortCode.PARENTHESIS_START.getValue());
        valueEndIndex = symbolString.substring(startIndex).indexOf("]");
        if (valueEndIndex == -1) {
            throw new IllegalArgumentException();
        }
        return valueEndIndex;
    }

    /**
     * @param mathExp
     * @param startIndex
     * @return
     */
    private int handleOperandMemory(StringBuilder mathExp, int startIndex) {
        int valueStartIndex;
        int valueEndIndex;
        boolean memValIsCurrency = true;
        valueStartIndex = symbolString.substring(startIndex).indexOf("[");
        if (valueStartIndex == -1) {
            throw new IllegalArgumentException();
        }

        valueStartIndex = symbolString.substring(startIndex).indexOf("[", valueStartIndex + 1) + startIndex;
        if (valueStartIndex == -1) {
            throw new IllegalArgumentException();
        }

        valueEndIndex = symbolString.indexOf("]", valueStartIndex + 1);
        if (valueEndIndex == -1) {
            throw new IllegalArgumentException();
        }

        String memoryKey = symbolString.substring(valueStartIndex + 1, valueEndIndex);
        String memoryValue = WorkingMemory.getInstance().getMemory(memoryKey);
        if (memValIsCurrency) {
            memoryValue = formatCurrencyToNumber(memoryValue);
        }
        mathExp.append(memoryValue);
        return valueEndIndex;
    }

    /**
     * @param mathExp
     * @param startIndex
     * @return
     */
    private int handleOperandActionParameter(StringBuilder mathExp, int startIndex) {
        int valueStartIndex;
        int valueEndIndex;
        boolean valueIsCurrency = true;
        valueStartIndex = symbolString.substring(startIndex).indexOf("[");
        if (valueStartIndex == -1) {
            throw new IllegalArgumentException();
        }

        valueStartIndex = symbolString.substring(startIndex).indexOf("[", valueStartIndex + 1) + startIndex;
        if (valueStartIndex == -1) {
            throw new IllegalArgumentException();
        }

        valueEndIndex = symbolString.indexOf("]", valueStartIndex + 1);
        if (valueEndIndex == -1) {
            throw new IllegalArgumentException();
        }

        String value = getValueOfOperandActionParameter(valueStartIndex + 1, valueEndIndex);
        if (valueIsCurrency) {
            value = formatCurrencyToNumber(value);
        }
        mathExp.append(value);
        return valueEndIndex;
    }

    private String getValueOfOperandActionParameter(int startIndex, int endIndex) {
        String value = symbolString.substring(startIndex, endIndex);
        value = value.split(":")[3];
        return value;
    }

    /**
     * @param mathExp
     * @param startIndex
     * @return
     */
    private int handleOperandElementAction(StringBuilder mathExp, int startIndex) {
        int valueStartIndex;
        int valueEndIndex;
        boolean valueIsCurrency = true;
        valueStartIndex = symbolString.substring(startIndex).indexOf("[");
        if (valueStartIndex == -1) {
            throw new IllegalArgumentException();
        }

        valueStartIndex = symbolString.substring(startIndex).indexOf("[", valueStartIndex + 1) + startIndex;
        if (valueStartIndex == -1) {
            throw new IllegalArgumentException();
        }

        valueEndIndex = symbolString.indexOf("]", valueStartIndex + 1);
        if (valueEndIndex == -1) {
            throw new IllegalArgumentException();
        }

        String value = symbolString.substring(valueStartIndex + 1, valueEndIndex);
        if (valueIsCurrency) {
            value = formatCurrencyToNumber(value);
        }
        mathExp.append(value);
        return valueEndIndex;
    }

    /**
     * @param mathExp
     * @param startIndex
     * @return
     */
    private int handleOperandValue(StringBuilder mathExp, int startIndex) {
        int valueStartIndex;
        int valueEndIndex;
        boolean valueIsCurrency = true;
        valueStartIndex = symbolString.substring(startIndex).indexOf("[");
        if (valueStartIndex == -1) {
            throw new IllegalArgumentException();
        }

        valueStartIndex = symbolString.substring(startIndex).indexOf("[", valueStartIndex + 1) + startIndex;
        if (valueStartIndex == -1) {
            throw new IllegalArgumentException();
        }

        valueEndIndex = symbolString.indexOf("]", valueStartIndex + 1);
        if (valueEndIndex == -1) {
            throw new IllegalArgumentException();
        }

        String value = symbolString.substring(valueStartIndex + 1, valueEndIndex);
        if (valueIsCurrency) {
            value = formatCurrencyToNumber(value);
        }
        mathExp.append(value);
        return valueEndIndex;
    }

    /**
     * @param symbolSubString
     * @param shortCodeValues
     * @return
     */
    private ShortCode shortCodeMatch(String symbolSubString, ShortCode[] shortCodeValues) {
        ShortCode shortCodeMatch = ShortCode.NOMATCH;
        for (ShortCode shortCode : shortCodeValues) {
            if (shortCode.toString().equals(symbolSubString)) {
                shortCodeMatch = shortCode;
                break;
            }
        }
        return shortCodeMatch;
    }

    /**
     * @param symbolSubString
     * @param operandValues
     * @return
     */
    private Operand operandMatch(String symbolSubString, Operand[] operandValues) {
        Operand operandMatch = Operand.NOMATCH;
        for (Operand operand : operandValues) {
            if (operand.toString().equals(symbolSubString)) {
                operandMatch = operand;
                break;
            }
        }
        return operandMatch;
    }

    private Operation operationMatch(Operation[] operationValues, String symbolSubString) {
        Operation operationMatch = Operation.NOMATCH;
        for (Operation operation : operationValues) {
            if (operation.toString().equals(symbolSubString)) {
                operationMatch = operation;
                break;
            }
        }
        return operationMatch;
    }

    private Function functionMatch(Function[] functionValues, String symbolSubString) {
        Function functionMatch = Function.NOMATCH;
        for (Function function : functionValues) {
            if (function.toString().equals(symbolSubString)) {
                functionMatch = function;
                break;
            }
        }
        return functionMatch;
    }

    /**
     * evaluates a math expression and returns the result
     *
     * @param expString expression to evaluate
     * @return
     */
    public String evaluate(String expString) {
        try {
            Expression exp = new ExpressionBuilder(expString).build();
            double result = exp.evaluate();

            String resultString = String.valueOf(result);
            if (resultString.endsWith(".0")) {
                resultString = resultString.substring(0, resultString.indexOf("."));
            }

            if (resultString.contains(".")) {
                resultString = String.valueOf(result + "f");
                float fVal = Float.valueOf(resultString);
                resultString = String.valueOf(fVal);
            }
            return resultString;
        } catch (IllegalArgumentException iae) {
            log.error("Invalid or empty expression!", iae);
            throw new IllegalArgumentException();
        }
    }

    /**
     * formats the number to a given decimal format
     *
     * @param number number to format
     * @param format decimal format
     * @return
     */
    public String formatNumberToCustomFormat(String number, String format) {
        double nmbr = Double.valueOf(number);
        DecimalFormat df = new DecimalFormat(format);
        return df.format(nmbr);
    }

    /**
     * formats currency values to their number format (e.g. : Rs.1,345.67 to
     * 1345.67)
     *
     * @param currency currency to convert to number
     * @return
     */
    private String formatCurrencyToNumber(String currency) {
        String curr = currency.replaceAll("[^0-9-.]", "");
        if (curr.startsWith(".")) {
            curr = curr.replaceFirst("[.]", "");
        }

        return curr;
    }

    /**
     * verify whether the given two values are numerically equals
     *
     * @param memorizedValue      1st value
     * @param valueNeedsToCompare 2nd value
     * @return
     */
    public boolean compareNumericalValues(String memorizedValue, String valueNeedsToCompare) {
        double memorizedDoubleValue = Double.valueOf(memorizedValue);
        double doubleValueNeedsToCompare = Double.valueOf(valueNeedsToCompare);
        return memorizedDoubleValue == doubleValueNeedsToCompare;
    }


}

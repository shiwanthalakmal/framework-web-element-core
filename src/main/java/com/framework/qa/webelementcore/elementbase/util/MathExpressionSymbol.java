package com.framework.qa.webelementcore.elementbase.util;

/**
 * @author Shiwantha Lakmal
 *         <p/>
 *         Store math expression symbols needed for mathematical expression processing
 */

public class MathExpressionSymbol {

    public enum Operand {
        VALUE("OR[1]"),
        ACTION_PARAMETER("OR[2]"),
        MEMORY_KEY("OR[3]"),
        ELEMENT_ACTION("OR[4]"),
        FUNCTION("OR[5]"),
        NOMATCH("");

        private String value;

        Operand(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

    }

    public enum Operation {
        NULL("OT[0]", ""),
        ADDITION("OT[1]", "+"),
        SUBTRACTION("OT[2]", "-"),
        MULTIPLICATION("OT[3]", "*"),
        DIVISION("OT[4]", "/"),
        EXPONENTIATION("OT[5]", "^"),
        MODULO("OT[6]", "%"),
        NOMATCH("", "");

        private String symbol;
        private String value;

        Operation(String symbol, String value) {
            this.symbol = symbol;
            this.value = value;
        }

        public String toString() {
            return symbol;
        }

        public String getValue() {
            return value;
        }

    }

    public enum ShortCode {
        PARENTHESIS_START("ST", "("),
        PARENTHESIS_END("ED", ")"),
        OPERAND("OR", ""),
        OPERATOR("OT", ""),
        NEWLINE("___", ""),
        SEPARATOR("/", ""),
        COMMA("CM", ","),
        FUNCTION("FN", ""),
        NOMATCH("", "");

        private String symbol;
        private String value;

        ShortCode(String symbol, String value) {
            this.symbol = symbol;
            this.value = value;
        }

        public String toString() {
            return symbol;
        }

        public String getValue() {
            return value;
        }

    }

    public enum Function {
        ABSOLUTE_VALUE("FN[1]", "abs"),
        ARC_COSINE("FN[2]", "acos"),
        ARC_SINE("FN[3]", "asin"),
        ARC_TANGENT("FN[4]", "atan"),
        CUBIC_ROOT("FN[5]", "cbrt"),
        NEAREST_UPPER_INTEGER("FN[6]", "ceil"),
        COSINE("FN[7]", "cos"),
        HYPERBOLIC_COSINE("FN[8]", "cosh"),
        EULERS_NUMBER_RAISED_TO_E_POWER_X("FN[9]", "exp"),
        NEAREST_LOWER_INTEGER("FN[10]", "floor"),
        LOGARITHMUS_NATURALIS_BASE_E("FN[11]", "log"),
        LOGARITHM_BASE_10("FN[12]", "log10"),
        LOGARITHM_BASE_2("FN[13]", "log2"),
        SINE("FN[14]", "sin"),
        HYPERBOLIC_SINE("FN[15]", "sinh"),
        SQUARE_ROOT("FN[16]", "sqrt"),
        TANGENT("FN[17]", "tan"),
        HYPERBOLIC_TANGENT("FN[18]", "tanh"),
        NOMATCH("", "");

        private String symbol;
        private String value;

        Function(String symbol, String value) {
            this.symbol = symbol;
            this.value = value;
        }

        public String toString() {
            return symbol;
        }

        public String getValue() {
            return value;
        }

    }

}

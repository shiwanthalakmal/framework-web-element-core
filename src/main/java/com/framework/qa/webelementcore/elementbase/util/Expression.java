package com.framework.qa.webelementcore.elementbase.util;

public class Expression {
    String exp;

    public Expression(String exp) {
        this.exp = exp;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String toString() {
        return this.getExp();
    }


}

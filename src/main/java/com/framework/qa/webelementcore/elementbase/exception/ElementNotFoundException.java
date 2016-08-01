package com.framework.qa.webelementcore.elementbase.exception;

import com.thoughtworks.selenium.SeleniumException;

public class ElementNotFoundException extends SeleniumException {

    public ElementNotFoundException(String message) {
        super(message);

    }

    public ElementNotFoundException(String message, String message2, String message3) {
        super(message);

    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}

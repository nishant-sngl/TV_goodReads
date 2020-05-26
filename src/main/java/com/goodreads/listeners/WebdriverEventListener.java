package com.goodreads.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * This file will be used to log the before and after of every selenium event.
 */
public class WebdriverEventListener extends AbstractWebDriverEventListener {

    //All these methods to be implemented as per the requirement.

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend){
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend){
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        // Do nothing.
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        // Do nothing.
    }

}

package com.goodreads.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebdriverEventListener extends AbstractWebDriverEventListener {

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend){
        System.out.println("before change value");
    }
}

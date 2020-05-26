package com.goodreads.pages;

import com.goodreads.base.Base;
import com.goodreads.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class HomePage extends BasePage{
    private EventFiringWebDriver driver = Base.driver;
    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "logo")
    private WebElement logo;

    @FindBy(id = "sitesearch_field")
    private WebElement searchField;

    @FindBy(xpath = "//img[@alt='search']")
    WebElement searchEnter;


    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isPageOpen() {
        return logo.isDisplayed();
    }

    public SearchPage enterSearchText(String s){
        searchField.sendKeys(s);
        searchField.submit();
        return new SearchPage();
    }
}

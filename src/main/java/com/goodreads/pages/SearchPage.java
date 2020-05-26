package com.goodreads.pages;

import com.goodreads.base.Base;
import com.goodreads.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPage extends BasePage {
    private EventFiringWebDriver driver = Base.driver;
    private WebDriverWait wait = new WebDriverWait(driver,10);

    @FindBy(xpath = "//form[@name='searchForm']")
    private WebElement searchForm;

    @FindBy(xpath = "//div[@class='loginModal']//preceding-sibling::div[@class='modal__close']/button")
    WebElement loginPopupCloseBtn;

    @FindBy(id = "search_query_main")
    WebElement searchBar;

//    String list1 = "//table[@class='tableList']//a[@class='";
//    String list2 = "']";

    public SearchPage(){
        PageFactory.initElements(driver, this);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isPageOpen() {
        return searchForm.isDisplayed();
    }

    public void clickOnFilterBy(String linkId){
        String id = null;
        switch (linkId){
            case "books":
                id="booksLink";
                break;
            case "groups":
                id="groupsLink";
                break;
            case "quotes":
                id="quotesLink";
                break;
        }
        driver.findElement(By.id(id)).click();
    }
    public void closeLoginPopup(){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginPopupCloseBtn));
            Base.clickJS(loginPopupCloseBtn);
        }catch (TimeoutException e){}
        wait.until(ExpectedConditions.invisibilityOf(loginPopupCloseBtn));
    }

    public ArrayList<String> getBooksList(List<WebElement> elements){
        ArrayList<String> list = new ArrayList<String>();
        for (WebElement ele:
                elements) {
            list.add(ele.getAttribute("href"));
        }
        return list;
    }

    public void enterSearchText(String s){
        searchBar.clear();
        searchBar.sendKeys(s);
        searchBar.submit();
    }

    public HashMap<String, Integer> getBooksListFromAllSearches(Map<String, String> map){
        HashMap<String, Integer> booksUrlMap = new HashMap<String, Integer>();
        for (Map.Entry<String, String> m:
             map.entrySet()) {
            ArrayList<String> list = new ArrayList<String>();
            String tag = m.getKey();
            String value = m.getValue();
            System.out.println("checking for: " + tag + " : "+ value);
            enterSearchText(value);
            if (!tag.equals("books"))
                clickOnFilterBy(tag);
            switch (tag){
                case "books":
                    list = getBooksList(driver.findElements(By.id("bookTitle")));
                    break;
                case "groups":
                    list = getBooksList(driver.findElements(By.id("groupName")));
                    break;
                case "quotes":
                    list = getBooksList(driver.findElements(By.xpath("//a[@class='authorOrTitle' and contains(@href,'book')]")));
                    break;
            }
            for (String l:
                 list) {
                if (booksUrlMap.get(l)!=null)
                    booksUrlMap.put(l,booksUrlMap.get(l)+1);
                else
                    booksUrlMap.put(l,1);
            }
        }
        return booksUrlMap;
    }
}

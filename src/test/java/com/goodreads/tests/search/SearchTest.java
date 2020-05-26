package com.goodreads.tests.search;

import com.goodreads.base.Base;
import com.goodreads.pages.HomePage;
import com.goodreads.pages.SearchPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class SearchTest extends Base {
    private HomePage homePage;
    private SearchPage searchPage;

    private SearchTest(){
        super();
    }

    @BeforeMethod
    private void setupTest(){
        init();
        homePage= new HomePage();
    }
    @Test
    private void searchBook(){
        String input = System.getProperty("input");
        Map<String, String> map = splitStringByHashTags(input);
        homePage.isPageOpen();
        searchPage = homePage.enterSearchText("");
        searchPage.closeLoginPopup();
        HashMap<String, Integer> urlMap = searchPage.getBooksListFromAllSearches(map);

        List<Map.Entry<String , Integer>> list = new LinkedList<>(urlMap.entrySet());
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        for (Map.Entry<String, Integer> val:
             urlMap.entrySet()) {
            System.out.println("As per the given inputs, i guess the book name is: " + val.getKey());
            break;
        }
    }
}

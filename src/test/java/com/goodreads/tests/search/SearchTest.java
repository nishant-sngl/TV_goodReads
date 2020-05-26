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
//        String input = "#books book1 #groups grp1 #quotes quote1 #events event1 event2";
        String input = "#books book1 #groups grp1 #quotes quote1";
        Map<String, String> map = splitStringByHashTags(input);
        for (Map.Entry<String, String> m:
             map.entrySet()) {
            System.out.println(m.toString());
        }
        homePage.isPageOpen();
        searchPage = homePage.enterSearchText(map.get("books"));
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
            System.out.println(val.getValue() + " : " + val.getKey());
            break;
        }
    }
}

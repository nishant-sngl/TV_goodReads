package com.goodreads.base;

import com.goodreads.listeners.WebdriverEventListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class Base implements TestInterface {
    public static EventFiringWebDriver driver;
    public static Properties prop;
    private static String browser;
    public static String baseUrl;

    public Base(){
        FileInputStream fis;
        try {
            prop = new Properties();
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/goodreads/configs/config.properties");
            prop.load(fis);
        }catch (FileNotFoundException e1) { e1.printStackTrace();
        }catch (IOException e) { e.printStackTrace(); }
    }

    public void init(){
        browser = prop.getProperty("browser");
        baseUrl = prop.getProperty("baseUrl");
        WebDriver temp = null;
        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver 3");
            temp = new ChromeDriver();
        }
        driver = new EventFiringWebDriver(temp);
        driver.register(new WebdriverEventListener());
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("wait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("pageLoadTimeout")), TimeUnit.SECONDS);

        driver.get(baseUrl);
    }

    public static void clickJS(WebElement ele) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
        return;
    }

    /**
     * This function will split the string on the basis of the hashtags given.
     * In case of no hashtags, then the string will be stored with #books
     * @param s
     * @return
     */
    public static HashMap<String, String > splitStringByHashTags(String s){
        String[] strArr = s.split("#");
        HashMap<String, String > map = new HashMap<String, String>();
        if (strArr.length!=0){
            for (String each: strArr){
                int i = each.indexOf(" ");
                if (i>0){
                    String tag = each.substring(0,i);
                    String text = each.substring(i+1,each.length());
                    map.put(tag,text.trim());
                }
            }
        }else {
            map.put("books", s);
        }
        return map;
    }
}

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

public class HelloWorld extends WebBaseClass {

    @Test public void test1() {
        automate.getUrl("https://www.pearson.com/us/");
        automate.sendKeys(By.className("search-box-input"), "");
        automate.click(By.className("search-box-icon"));
    }


    @Test public void test2() {
        automate.getUrl("https://www.pearson.com/us/");
        automate.click(By.xpath("//*[@id=\"top\"]/footer/div/div/section[2]/div/div/div[3]/div/ul/li[2]/a"));
    }
}

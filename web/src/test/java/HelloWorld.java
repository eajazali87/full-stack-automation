import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



//@Listeners(CustomListener.class)
public class HelloWorld extends WebBaseClass {

    @Test
    public void test1() {
        automate.getUrl("https://www.pearson.com/us/");
        automate.sendKeys(By.className("search-box-input"), "");
        automate.click(By.className("search-box-icon"));
    }

    @Test
    public void test2() {
        automate.getUrl("https://www.pearson.com/us/");
        automate.click(By.xpath("//*[@id=\"top\"]/footer/div/div/section[2]/div/div/div[3]/div/ul/li[2]/a"));
        automate.isElementPresent(By.xpath("//*[@id=\"main-content\"]/div/section[2]/div/div/div[1]/div/div/figure/a/figcaptio"));
    }

    @Test
    public void test3() {
        Assert.assertEquals(true,true);
    }
}

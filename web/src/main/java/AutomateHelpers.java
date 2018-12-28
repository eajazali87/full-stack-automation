import org.openqa.selenium.*;

public class AutomateHelpers {
    WebDriver driver;
    WebElement webElement = null;
    JavascriptExecutor js = null;
    final String errorColorCode = "\u001B[31m";

    public AutomateHelpers(WebDriver driver) {
        this.driver = driver;
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    //click
    public void click(By element) {
        try {
            webElement = driver.findElement(element);
            webElement.click();
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, click operation didn't happen");
        }
    }

    //click using js
    public void clickUsingJS(By element) {
        try {
            webElement = driver.findElement(element);
            js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", webElement);
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, click operation using JS didn't happen");
        }
    }

    //send keys
    public void sendKeys(By element, String text) {
        try {
            webElement = driver.findElement(element);
            webElement.clear();
            webElement.sendKeys(text);
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, sendkeys operation didn't happen");
        }
    }
}

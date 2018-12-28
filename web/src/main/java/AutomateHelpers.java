import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AutomateHelpers {
    WebDriver driver;
    WebElement webElement = null;
    JavascriptExecutor js = null;
    Dimension dimension = null;
    Color c = null;
    StringBuffer strBuffer = null;
    StringBuilder strBuilder = null;
    BufferedReader br = null;
    Actions action = null;
    boolean elementVisible = false;
    java.util.List<WebElement> listWebElements = null;
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

    //Tab
    public void tabSpace(By element) {
        try {
            webElement = driver.findElement(element);
            webElement.sendKeys(Keys.TAB);
            webElement.sendKeys(Keys.SPACE);
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, tab space operation didn't happen");
        }
    }

    public void tabSpaceAction(By element) {
        try {
            webElement = driver.findElement(element);
            Actions action = new Actions(driver);
            action.sendKeys(webElement, Keys.SPACE).build().perform();
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, tab space operation didn't happen");
        }
    }

    //is element present
    public boolean isElementPresent(By element) {
        try {
            webElement = driver.findElement(element);
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": element is not present");
            return false;
        }
    }

    //is element displayed
    public boolean isElementDisplayed(By element) {
        try {
            return driver.findElement(element).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": element is not displayed");
            return false;
        }
    }

    //is element Enabled
    public boolean isElementEnabled(By element) {
        try {
            webElement = driver.findElement(element);
            return webElement.isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": element is not enabled");
            return false;
        }
    }

    //set windowsize
    public void setWindowSize(int width, int height) {
        boolean windowSet = false;
        dimension = new Dimension(width, height);
        try {
            windowSet = true;
            driver.manage().window().setSize(dimension);
        } catch (Exception e) {
            if (windowSet) {
                try {
                    driver.manage().window().setSize(dimension);
                } catch (Exception ex) {
                    System.out.println(
                        errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + ": dimension is not set");
                }
            }
        }
    }

    //get css value
    public String getCSSValue(By element, String property) {
        try {
            webElement = driver.findElement(element);
            return webElement.getCssValue(property);
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to get the css property for the element");
            return null;
        }
    }

    //get Text
    public String getText(By element) {
        try {
            webElement = driver.findElement(element);
            return webElement.getText();
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to get the text value for the element");
            return null;
        }
    }

    //select value from a drop down
    public void selectItem(By element) {
        try {
            webElement = driver.findElement(element);
            Select select = new Select(webElement);
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to select the element");
        }
    }

    //get Attribute value
    public String getAttributeValue(By element, String attribute) {
        try {
            return driver.findElement(element).getAttribute(attribute);
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to get the attributes for the element");
            return null;
        }
    }

    //assertValues
    public boolean assertValue(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            return true;
        } catch (AssertionError ae) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " - Assertion Error: " + ae.getMessage());
            return false;
        }
    }

    //hover on an element
    public void hoverOnElement(By element) {
        try {
            action = new Actions(driver);
            action.moveToElement(driver.findElement(element))
                .moveToElement(driver.findElement(element)).build().perform();
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to hover on the element");
        }
    }

    //Tab on an element
    public void tabOnElement(By element) {
        try {
            webElement = driver.findElement(element);
            webElement.sendKeys(Keys.TAB);
            webElement.sendKeys(Keys.ENTER);
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to TAB on the element");
        }
    }

    //focus on an element by Id
    public void focusOnElementById(String element) {
        try {
            js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('" + element + "').focus()");
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to focus on the element");
        }
    }

    public void unFocusOnElementById(String element) {
        try {
            js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('" + element + "').blur()");
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to un-focus on the element");
        }
    }

    //key operation on active element
    public void keyOperationOnActiveElement(Keys key) {
        try {
            driver.switchTo().activeElement().sendKeys(key);
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + ": no such element, unable to perform '" + key
                    + "' key operation on the element");
        }
    }

    public String hex2Rgba(String colorStr) {
        c = new Color(Integer.valueOf(colorStr.substring(1, 3), 16),
            Integer.valueOf(colorStr.substring(3, 5), 16),
            Integer.valueOf(colorStr.substring(5, 7), 16));
        strBuffer = new StringBuffer();
        strBuffer.append("rgba(");
        strBuffer.append(c.getRed());
        strBuffer.append(", ");
        strBuffer.append(c.getGreen());
        strBuffer.append(", ");
        strBuffer.append(c.getBlue());
        strBuffer.append(", ");
        strBuffer.append(c.getTransparency());
        strBuffer.append(")");
        return strBuffer.toString();
    }

    public String hex2Rgb(String colorStr) {
        c = new Color(Integer.valueOf(colorStr.substring(1, 3), 16),
            Integer.valueOf(colorStr.substring(3, 5), 16),
            Integer.valueOf(colorStr.substring(5, 7), 16));
        strBuffer = new StringBuffer();
        strBuffer.append("rgb(");
        strBuffer.append(c.getRed());
        strBuffer.append(", ");
        strBuffer.append(c.getGreen());
        strBuffer.append(", ");
        strBuffer.append(c.getBlue());
        strBuffer.append(")");
        return strBuffer.toString();
    }

    public boolean assertCSSProperties(String propertyType, String expectedValue, Object[] arr) {
        boolean isCSSPropertyPresent = false;
        try {
            if (Arrays.asList(arr).contains(expectedValue)) {
                isCSSPropertyPresent = true;
            } else {
                isCSSPropertyPresent = false;
            }
        } catch (Exception e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + " - Assertion Error: " + e.getMessage());
        }
        return isCSSPropertyPresent;
    }

    //isElementVisibleOnPage
    public boolean isElementsVisibleOnPage(By element) {
        try {
            listWebElements = driver.findElements(element);
            if (listWebElements.size() > 0) {
                elementVisible = true;
                return elementVisible;
            } else {
                elementVisible = false;
                return elementVisible;
            }
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + ": element is not visible");
            return false;
        }
    }

    public int countNumberOfItems(By element) {
        try {
            int numberOfItems = driver.findElements(element).size();
            return numberOfItems;
        } catch (NoSuchElementException e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to get size of elements");
            return 0;
        }
    }

    //Print all the contents from a file
    public void printFileContents(String jsFilePath) {
        try {
            br = new BufferedReader(new FileReader(jsFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Return a file as a string
    public String readFileAsString(String fileName) throws IOException {
        br = new BufferedReader(new FileReader(fileName));
        try {
            strBuilder = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                strBuilder.append(line);
                strBuilder.append(System.getProperty("line.separator"));
                line = br.readLine();
            }
            return strBuilder.toString();
        } finally {
            br.close();
        }
    }

    //Return all attributes and values of an element
    public String getAllAttributes(By element) {
        js = (JavascriptExecutor) driver;
        webElement = driver.findElement(element);
        Object attributes = "";
        try {
            attributes = js.executeScript(
                "var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
                webElement);
            return attributes.toString();
        } catch (Exception e) {
            System.out.println(
                errorColorCode + Thread.currentThread().getStackTrace()[2].getMethodName() + ":"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + " - " + element
                    + ": no such element, unable to get all attributes of the element");
            return null;
        }
    }
}

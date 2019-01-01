import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.*;

import java.io.File;
import java.io.IOException;

public class CustomListener extends WebBaseClass implements ITestListener, IInvokedMethodListener {
    String methodName;

    public CustomListener() {
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override public void onTestFailure(ITestResult iTestResult) {
        Object currentClass = iTestResult.getInstance();
        WebDriver webDriver = ((WebBaseClass) currentClass).driver;
        if (webDriver != null) {
            try {
                File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFile, new File(
                    System.getProperty("user.dir") + "/TestFailureScreenShots/" + methodName
                        + ".jpg"));
            } catch (ScreenshotException e) {
                System.out.println("unable to take screen shot" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("driver is null");
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        methodName = iInvokedMethod.getTestMethod().getMethodName();
    }
}

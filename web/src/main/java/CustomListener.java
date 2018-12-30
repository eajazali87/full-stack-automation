import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends WebBaseClass implements ITestListener {

    public CustomListener() {
    }

    @Override public void onTestStart(ITestResult iTestResult) {

    }

    @Override public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Failed Test");
        automate.takeScreenShotOnFailure();
    }

    @Override public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override public void onStart(ITestContext iTestContext) {

    }

    @Override public void onFinish(ITestContext iTestContext) {

    }
}

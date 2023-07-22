package api.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	@Override
	public void onStart(ITestContext context) {
		
		String timestamp = new SimpleDateFormat("YYYY.MM.dd.HH.MM.SS").format(new Date());
		
		repName =" Test -Reports - " + timestamp + ".html";
		
		//1.ui-> setup (ExtentSparkReporter)
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
		sparkReporter.config().setDocumentTitle("RestAssuredproject");
		sparkReporter.config().setReportName("petstore user API");
		sparkReporter.config().setTheme(Theme.DARK);
		
		//2.environment variable & comman data to appear-> (ExtentReports)
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "petstore user API");
		extent.setSystemInfo("OperatingSystem", System.getProperty("os.name"));
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","Karthi");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	
	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
	}

	
}

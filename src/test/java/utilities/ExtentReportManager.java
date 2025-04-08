package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter; // UI of the report
    public ExtentReports extent; // Populate common info on the Report
    public ExtentTest test; // Create test case entries in the report and update status of test methods
    
    String repName;

    // onStart: Initialize the report and set system information
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd,HH.mm.ss").format(new Date()); // Time stamp
        repName = "Test-Report-" + timeStamp + ".html";

        // Define the report file path
        sparkReporter = new ExtentSparkReporter("." + File.separator + "reports" + File.separator + repName);

        // Report configuration
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); // Title of the Report
        sparkReporter.config().setReportName("OpenCart Functional Testing"); // Name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Set system information for the report
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        // Get parameters from the XML file (e.g., OS, Browser)
        String os = context.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        // If groups are specified, add them to the report
        List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
        if (!includeGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includeGroups.toString());
        }
    }

    // onTestSuccess: Log success information when a test passes
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName()); // Create a new entity in the report
        test.assignCategory(result.getMethod().getGroups()); // Display groups in the report
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    // onTestFailure: Log failure information when a test fails
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        // Capture screenshot if needed
		String imgPath = new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
    }

    // onTestSkipped: Log skipped test information
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    // onFinish: Finalize the report and open it in the browser
    public void onFinish(ITestContext context) {
        extent.flush();

        // Define the report file path
        String pathOfExtentReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            // Open the generated report in the default browser
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Uncomment the following code if you want to send the report by email (optional)
        /* try {
            URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);

            // Create the email message
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("your-email@gmail.com", "your-email-password"));
            email.setSSLOnConnect(true);
            email.setFrom("from-email@gmail.com");
            email.setSubject("Test Report - Please Find Attached Report...");
            email.setHtmlMsg("<html><body>Hi Team, <br> Please check the attached report...</body></html>");
            email.addTo("recipient@example.com");
            email.send(); // Send the email
        } catch (Exception e) {
            e.printStackTrace();
        } */
    }
}

package com.example;


import java.io.FileInputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
// 
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
    public static Logger log = LogManager.getLogger(AppTest.class);
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeMethod
    public void mybfre() throws Exception
    {
        driver=new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        ExtentSparkReporter r = new ExtentSparkReporter("D:\\CC2report.html");
        report = new ExtentReports();
        report.attachReporter(r);

        log.info("URL is being opened!!!");
        driver.get("https://www.barnesandnoble.com/");
        log.info("Successfully opened!");
         PropertyConfigurator.configure("C:\\Users\\sumit\\OneDrive\\Desktop\\ccdemo2\\src\\resources\\log4j.properties");

    }
    
    @Test(priority = 0)
    public void Test1() throws Exception
    {
        FileInputStream fs = new FileInputStream("D:\\Login.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheet("Login");
        XSSFRow row = sheet.getRow(1);
        String name = row.getCell(0).getStringCellValue(); 
        driver.findElement(By.xpath("//*[@id=\'rhf_header_element\']/nav/div/div[3]/form/div/div[1]/a")).click();
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(name);
        // test.log(Status.PASS,"passed");
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button")).click();
        WebElement check1=driver.findElement(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1"));
        // Assert.assertTrue(check1.getText().contains("chetan bhagat"), "Name not found!");

        log.info("TestCase 1 passed Successfully!");
    }


    @Test(priority = 1)
    public void Test2() throws InterruptedException {
        WebElement audiobooks = driver.findElement(By.linkText("Audiobooks"));
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.moveToElement(audiobooks).perform();
        driver.findElement(By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        driver.findElement(By.linkText("Funny Story")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,500)");
        driver.findElement(By.xpath("//*[@id='otherAvailFormats']/div/div")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,600)");
         driver.findElement(By.xpath("//*[@id='prodInfoContainer']/div[3]/form[1]/input[11]")).click();
        // Thread.sleep(2000);
        // WebElement cart = driver.findElement(By.xpath("//*[@id='add-to-bag-main']/div[1]"));
        // String add = cart.getText();
        // if (add.equals("Item Successfully Added To Your cart")) log.info("TestCase 2 Executed Successfully!");
        // else
        //     log.error("Error Occurred!");
        Thread.sleep(2000);
    }


    @Test(priority = 2)
    public void Test3() throws Exception{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1100)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='footer']/div/dd/div/div/div[1]/div/a[5]/span")).click();
        Thread.sleep(2000);
        js.executeScript("windows.scrollBy(0,400)");
        driver.findElement(By.xpath("//*[@id='rewards-modal-link']")).click();
        WebElement check2=driver.findElement(By.xpath("//*[@id=\"dialog-title\"]"));
        Assert.assertTrue(check2.getText().contains("Sign in or Create an Account"), "Sign in first!!");
        log.info("TestCase 3 passed Successfully!");
    }


    @AfterMethod
    public void myaftrmthd() throws Exception{
        log.info("Tests Executed!!!");
        report.flush();

        driver.quit();
    }
}
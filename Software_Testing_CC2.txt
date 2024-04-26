package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
    static WebDriver dri;

    @Test(enabled = false)
    public void Testcase1() throws Exception {
        WebDriverManager.chromedriver().setup();
        dri = new ChromeDriver();
        dri.get("https://www.barnesandnoble.com");
        dri.manage().window().maximize();
        dri.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[1]/a")).click();
        dri.findElement(By.linkText("Books")).click();
        WebElement sear = dri
                .findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[2]/div/input[1]"));
        sear.sendKeys("Chetan Bhagat");
        sear.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        String str = dri
                .findElement(By.xpath("//*[@id=\"searchGrid\"]/div/section[1]/section[1]/div/div[1]/div[1]/h1/span"))
                .getText();
        if (str.equals("Chetan Bhagat")) {
            System.out.println("The text matches");
        } else {
            System.out.println("The text doesnot match");
        }
        dri.close();
    }

    @Test
    public void TestCase2() throws Exception {
        WebDriverManager.chromedriver().setup();
        dri = new ChromeDriver();
        dri.get("https://www.barnesandnoble.com");
        dri.manage().window().maximize();

        WebElement hov = dri.findElement(By.xpath("//*[@id=\"rhfCategoryFlyout_Audiobooks\"]"));
        new Actions(dri).moveToElement(hov).perform();
        dri.findElement(
                By.xpath("//*[@id=\"navbarSupportedContent\"]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]"))
                .click();
        Thread.sleep(2000);
        dri.findElement(By.xpath("//*[@id=\"addToBagForm_2940178667866\"]/input[11]")).click();
        dri.findElement(By.linkText("Item Successfully Added To Your Cart")).click();
        dri.close();
    }

    @Test(enabled = false)
    public void Testcase3() throws Exception {
        WebDriverManager.chromedriver().setup();
        dri = new ChromeDriver();
        dri.get("https://www.barnesandnoble.com");
        dri.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) dri;

        js.executeScript("window.scrollBy(0,1800)");
        dri.findElement(By.xpath("/html/body/main/div[3]/div[3]/div/section/div/div/div/div/div/a[1]/span")).click();
        js.executeScript("window.scrollBy(0,2000)");
        dri.findElement(By.xpath("//*[@id=\"rewards-modal-link\"]")).click();
        dri.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[1]/div/div/label")).click();
        WebElement ema = dri.findElement(By.className("user-error"));
        ema.sendKeys("abbdas");
        dri.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("dhjadfj");
        dri.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[4]/div/div/button")).click();
        String str = dri.findElement(By.xpath("//*[@id=\"mloginErrors\"]/em")).getText();
        if (str.equals("Please enter a valid email address")) {
            System.out.println("Mail id is checked properly");
        } else
            System.out.println("Mail id is not checked properly");
        dri.close();
    }

}
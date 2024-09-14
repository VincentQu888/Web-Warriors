package com.example.Web.Warriors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Screenshot {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "\"C:\\Users\\lilli\\Downloads\\Web-Warriors\\chromedriver.exe\"");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080");
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("screenshot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

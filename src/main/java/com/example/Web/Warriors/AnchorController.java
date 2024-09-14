package com.example.Web.Warriors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.apache.commons.io.FileUtils;


@Controller
public class AnchorController {

    @GetMapping("/")
    String index(Model model){
        return "index";
    }

    @PostMapping("/screenshotter")
    private String takeScreenshot(Model model) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lilli\\Downloads\\Web-Warriors\\chromedriver.exe");
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
        return "index";
    }
    
}
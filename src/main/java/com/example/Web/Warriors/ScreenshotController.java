package com.example.Web.Warriors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.io.FileUtils;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ScreenshotController {

    @PostMapping("/judge")
    private String judge(HttpServletRequest request, Model model) {
        try {
            String userHtml = request.getParameter("userHtml");
            String userCss = request.getParameter("userCss");   

            String filePath = "src/main/resources/templates/userInput.html";
            File file = new File(filePath);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(userHtml + "<head><style>" + userCss + "</style></head>");
            }

        } catch (Exception e) {
        }
        takeScreenshot("userinput");
        return "redirect:arena";
    }

    public void takeScreenshot(String link) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://localhost:8080/" + link);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("screenshot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

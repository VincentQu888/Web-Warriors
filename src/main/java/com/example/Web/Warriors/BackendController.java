package com.example.Web.Warriors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import jakarta.servlet.http.HttpServletRequest;

import java.io.*;



@Controller
public class BackendController {

    static int level = 1;

    @PostMapping("/judge")
    private String judge(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) throws IOException, InterruptedException {
        //screenshotting user submission
        


        String userHtml = "";
        String userCss = "";
        try {
            userHtml = request.getParameter("userHtml");
            userCss = request.getParameter("userCss");   

            String filePath = "src/main/resources/templates/userInput.html";
            File file = new File(filePath);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(userHtml + "<head><style>" + userCss + "</style></head>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        takeScreenshot("userinput");

        //judging user submission
        Process process = Runtime.getRuntime().exec("C:/Users/andyj/AppData/Local/Programs/Python/Python312/python.exe judge.py");

        OutputStream stdin = process.getOutputStream(); //pass user level into judge
        PrintWriter out = new PrintWriter(stdin);
        out.println(1);
        out.flush();

        process.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line, output = "";
        while((line = reader.readLine()) != null){
            output = line;
        }

        double prediction = Double.parseDouble(output);
        if(prediction > 0.5){
            redirectAttributes.addFlashAttribute("prediction", "Passed"); //level number, success message
            level++;
            redirectAttributes.addFlashAttribute("levelCount", level);
            redirectAttributes.addFlashAttribute("levelName", levelName(level));
        }else{
            redirectAttributes.addFlashAttribute("prediction", "Failed"); //old code, fail message, level number
            redirectAttributes.addFlashAttribute("oldHtml", userHtml);
            redirectAttributes.addFlashAttribute("oldCss", userCss);
            redirectAttributes.addFlashAttribute("levelCount", level);
            redirectAttributes.addFlashAttribute("levelName", levelName(level));
        }

        return "redirect:arena";
    }


    //other mapping
    @GetMapping("/arena")
    String arena(Model model){
        model.addAttribute("levelCount", level);
        model.addAttribute("levelName", levelName(level));
        return "arena";
    } 

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        model.addAttribute("levelCount", level);
        model.addAttribute("levelName", levelName(level));
        return "index";
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

    public String levelName(int levelCount) {
        String names[] = {"Citizen", "Soldier", "Knight", "Captain", "Commander", "Warrior"};
        return names[levelCount-1];
    }
}

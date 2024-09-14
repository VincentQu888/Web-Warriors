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

    @GetMapping("/arena")
    String arena(Model model){
        return "arena";
    }

    @GetMapping("/userinput")
    String userInput(Model model) {
        return "userinput";
    }
    
}
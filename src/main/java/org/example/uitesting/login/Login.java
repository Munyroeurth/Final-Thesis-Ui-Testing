package org.example.uitesting.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Login {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.saucedemo.com/");

            String usernameInput = "standard_user";
            String passwordInput = "1234567";

            // Tìm và nhập Username
            By usernameLocator = Nlp.createLocator("username", "placeholder", "Username");
            WebElement usernameField = driver.findElement(usernameLocator);
            usernameField.sendKeys(usernameInput);

            System.out.printf("pwdInput :" + usernameInput + "\n");

            // Tìm và nhập Password
            By passwordLocator = Nlp.createLocator("password", "placeholder", "Password");
            WebElement passwordField = driver.findElement(passwordLocator);
            passwordField.sendKeys(passwordInput);

            System.out.printf("pwdInput :" + passwordInput + "\n");

            // Tìm và nhấn nút Login
            By loginButtonLocator = Nlp.createLocator("nút login", "button", "click");
            WebElement loginButton = driver.findElement(loginButtonLocator);
            loginButton.click();

            System.out.println("Đăng nhập thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
//            driver.quit();
        }

        List<Map<String,String>> testCases = new ArrayList<>();
        // Thêm test case vào danh sách
        testCases.add(createTestCase("Fill Username \nFill Password \nClick login", "standard_user", "secret_sauce", "login"));
//        testCases.add(createTestCase("Fill Username \nFill Password \nClick login", "stand_use", "wrong_pwd"));


    }
    public static Map<String, String> createTestCase(String description, String username , String password, String login) {
        Map<String, String> testCase = new LinkedHashMap<>();
        testCase.put("description", description);
        testCase.put("username", username);
        testCase.put("password", password);
        testCase.put("login", login);
        return testCase;
    }
}

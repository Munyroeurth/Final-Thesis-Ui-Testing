package org.example.nlplogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginAutomation {
    public static void main(String[] args) {
        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Mở trang web chứa màn hình login
//            driver.get("https://example.com/login");
            driver.get("https://www.saucedemo.com/");

            // Dữ liệu từ NLP
            String usernameInput = "admin";
            String passwordInput = "123456";

            // Tìm và nhập Username
            By usernameLocator = NLPToLocator.createLocator("username", "placeholder", "Username");
            WebElement usernameField = driver.findElement(usernameLocator);
            usernameField.sendKeys(usernameInput);

            // Tìm và nhập Password
            By passwordLocator = NLPToLocator.createLocator("password", "placeholder", "Password");
            WebElement passwordField = driver.findElement(passwordLocator);
            passwordField.sendKeys(passwordInput);

            // Tìm và nhấn nút Login
            By loginButtonLocator = NLPToLocator.createLocator("nút login", "text", "");
            WebElement loginButton = driver.findElement(loginButtonLocator);
            loginButton.click();

            System.out.println("Đăng nhập thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
            // Đóng trình duyệt
//            driver.quit();
        }
    }
}

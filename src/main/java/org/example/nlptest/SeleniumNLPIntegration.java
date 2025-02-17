package org.example.nlptest;

import org.example.nlptest.NLPToLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumNLPIntegration {
    public static void main(String[] args) {
        // Cấu hình đường dẫn ChromeDriver
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Mở trang web
//            driver.get("https://example.com");
            driver.get("https://www.saucedemo.com/");

            // Dữ liệu từ NLP
            String element = "nút";
            String text = "Đăng nhập";
            String location = "góc phải";

            // Tạo XPath từ NLP
            By locator = NLPToLocator.createLocator(element, text, location);

            // Định vị phần tử và nhấp vào
            WebElement button = driver.findElement(locator);
            button.click();

            System.out.println("Đã nhấp vào nút Đăng nhập thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
            // Đóng trình duyệt
//            driver.quit();
        }
    }
}

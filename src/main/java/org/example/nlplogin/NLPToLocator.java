package org.example.nlplogin;

import org.openqa.selenium.By;

public class NLPToLocator {
    // Phương thức tạo XPath từ mô tả ngôn ngữ tự nhiên
    public static By createLocator(String element, String attribute, String value) {
        String xpath = "";

        // Xác định phần tử dựa trên từ khóa
        switch (element.toLowerCase()) {
            case "username":
                xpath = "//input[@placeholder='Username']";
                break;
            case "password":
                xpath = "//input[@placeholder='Password']";
                break;
            case "nút login":
            case "login":
                xpath = "//button[text()='Login']";
                break;
            default:
                xpath = "//*";
        }
        return By.xpath(xpath);
    }
}

package org.example.uitesting.login;

import org.openqa.selenium.By;

public class Nlp {
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
                break;//*[@id="password"]
//            case "nút login":
            case "login":
                xpath = "//button[@login='Click']";
//                xpath = "//*[@id=\"login-button\"]";
                break;//*[@id="login-button"]
            default:
                xpath = "//*";
        }
        return By.xpath(xpath);
    }
}

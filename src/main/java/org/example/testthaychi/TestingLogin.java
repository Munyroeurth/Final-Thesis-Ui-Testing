package org.example.testthaychi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestingLogin {
    public static void main(String[] args) {

        WebDriver driver;
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        List<WebElement> inputElements = driver.findElements(By.tagName("input"));

        Map<String, String> xpathGen = new HashMap<>();

        String inputXpath = null;
        for (WebElement input : inputElements) {
            String inputId = input.getAttribute("id");
            String inputName = input.getAttribute("name");
            inputXpath = getXPath(input);

            // Tạo XPath động dựa trên thuộc tính id hoặc name
            String xpath;
            if (inputId != null && !inputId.isEmpty()) {
                xpath = "//*[@id='" + inputId + "']";
                xpathGen.put(inputId, xpath);  // Lưu với key là id
            } else if (inputName != null && !inputName.isEmpty()) {
                xpath = "//*[@name='" + inputName + "']";
                xpathGen.put(inputName, xpath); // Lưu với key là name
            }
            System.out.printf("data=" + xpathGen + "\n");
            System.out.printf("inputXpath : " + inputXpath + "\n");
            System.out.println("XPath used: " + xpathGen.get("username"));

        }
//         Duyệt qua xpaths để gửi thông tin đăng nhập
        if (xpathGen.containsKey("username")) {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathGen.get("username"))));
            usernameField.sendKeys("yourUsername");
        }

        if (inputXpath != null) {
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputXpath)));
            passwordField.sendKeys("yourPassword");
        }

        // Thực hiện login nếu nút submit tồn tại
        if (inputXpath != null) {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(inputXpath)));
            loginButton.click();
        }
//        driver.quit();

    }
    public static String getXPath(WebElement element) {
        String id = element.getAttribute("id");

        if (id != null && !id.isEmpty()) {
            return "//*[@id='" + id + "']";
        } else {
            return "0";
//            return getAbsoluteXPath(element);
        }
    }
}

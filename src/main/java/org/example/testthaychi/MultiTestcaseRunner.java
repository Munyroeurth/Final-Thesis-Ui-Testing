package org.example.testthaychi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class MultiTestcaseRunner {
    public static void main(String[] args) throws InterruptedException {
        String targetURL = "http://saucedemo.com";

        // Danh sách test case
        List<Map<String, String>> testCases = new ArrayList<>();

        // Thêm test case vào danh sách
        testCases.add(createTestCase("Fill Username \nFill Password \nClick login", "standard_user", "secret_sauce", "login"));
//        testCases.add(createTestCase("Fill Username \nFill Password \nClick login", "stand_use", "wrong_pwd"));

        // Khởi tạo WebDriver
        WebDriver driver = new ChromeDriver();
//        driver.get(targetURL);

        // Lặp qua các test case
        for (Map<String, String> testCase : testCases) {
//            driver.get(targetURL);
            String description = testCase.get("description");
            String username = testCase.get("username");
            String password = testCase.get("password");

            System.out.println("Running test case with description: " +"\n" + description +"\n"+ username+ "\n" + password);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Map<String, String> xpaths = getAllXPaths(description, driver);
            xpaths.forEach((key, value) -> System.out.println(key + " => " + value));

            System.out.print("=================================================\n");

            // Thực hiện hành động dựa trên mô tả
            for (Map.Entry<String, String> entry : xpaths.entrySet()) {
                String action = entry.getKey();
                String xpath = entry.getValue();
                driver.get(targetURL);
                try {
                    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//                    driver.get(targetURL);
                    if (action.toLowerCase().contains("fill username")) {
                        element.sendKeys(username);
//                        element.clear();
//                        Thread.sleep(20000);
                    } else if (action.toLowerCase().contains("fill password")) {
                        element.sendKeys(password);
//                        element.clear();
//                        Thread.sleep(20000);
                    } else if (action.toLowerCase().contains("click login")) {
                        element.click();
                    }
                } catch (Exception e) {
                    System.out.println("Error executing action: " + action + ", XPath: " + xpath);
                }
            }
        }
//        driver.quit();
    }

    public static Map<String, String> createTestCase(String description, String username, String password, String login) {
        Map<String, String> testCase = new LinkedHashMap<>();
        testCase.put("description", description);
        testCase.put("username", username);
        testCase.put("password", password);
        testCase.put("login", login);
        return testCase;
    }

    public static Map<String, String> getAllXPaths(String description, WebDriver driver) throws InterruptedException {
        String[] actions = description.split("\n");
        Map<String, String> result = new LinkedHashMap<>();

        List<WebElement> inputElements = driver.findElements(By.tagName("input"));
        List<WebElement> buttonElements = driver.findElements(By.tagName("button"));
        List<WebElement> allElements = new ArrayList<>();
        allElements.addAll(inputElements);
        allElements.addAll(buttonElements);

        // Xử lý từng hành động
        for (String action : actions) {
            String xpath = null;

            if (action.toLowerCase().contains("fill username")) {
                xpath = findMatchingElement(allElements, "username");
            } else if (action.toLowerCase().contains("fill password")) {
                xpath = findMatchingElement(allElements, "password");
            } else if (action.toLowerCase().contains("click login")) {
                xpath = findMatchingElement(allElements, "login");
            } else {
                xpath = "No matching element found for action: " + action;
            }
            result.put(action, xpath);
        }
        return result;
    }

    public static String findMatchingElement(List<WebElement> elements, String keyword) {
        for (WebElement element : elements) {
            String id = element.getAttribute("id");
            String name = element.getAttribute("name");
            String placeholder = element.getAttribute("placeholder");
            String text = element.getText(); // Lấy văn bản hiển thị trên nút

            // Kiểm tra các thuộc tính khớp với keyword
            if ((id != null && id.toLowerCase().contains(keyword)) ||
                    (name != null && name.toLowerCase().contains(keyword)) ||
                    (placeholder != null && placeholder.toLowerCase().contains(keyword)) ||
                    (text != null && text.toLowerCase().contains(keyword))) {
                return getXPath(element);
            }
        }
        return "No matching element found";
    }

    public static String getXPath(WebElement element) {
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        String className = element.getAttribute("class");

        if (id != null && !id.isEmpty()) {
            return "//*" + "[@id='" + id + "']";
        } else if (name != null && !name.isEmpty()) {
            return "//*" + "[@name='" + name + "']";
        } else if (className != null && !className.isEmpty()) {
            return "//*" + "[contains(@class, '" + className + "')]";
        } else {
            return "Element does not have an easily identifiable XPath";
        }
    }
}

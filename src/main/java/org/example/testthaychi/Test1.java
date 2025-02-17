package org.example.testthaychi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        String description = "Fill Username \nFill Password \nClick login\n";
        String targetURL = "http://saucedemo.com";
        String[] actions = description.split("\n");
//        String targetURL = "http://dksdh.vnu.edu.vn/dkmh/login.asp";

        WebDriver driver = new ChromeDriver();
        driver.get(targetURL);

        Map<String, String> xpaths = getAllXPaths(description, driver);

        xpaths.forEach((key, value) -> System.out.println(key + " => " + value));

        System.out.print("=================================================\n");

//        Map<String, String> data = performActions(driver, xpaths,description.split("\n"),testcase).toString();
//
//        // Kiểm tra kết quả thực hiện hành động
//        data.forEach((action, result) -> {
//            System.out.println(action + " => " + result);
//        });
//        driver.quit();
    }
    public static Map<String, String> createTestCase(String description1, String username, String password, String login) {
        Map<String, String> testCase = new HashMap<>();
        testCase.put("description", description1);
        testCase.put("username", username);
        testCase.put("password", password);
        testCase.put("login", login);
        return testCase;
    }
    public static Map<String, String> getAllXPaths(String description, WebDriver driver) {

        String[] actions = description.split("\n");

        Map<String, String> result = new LinkedHashMap<>();

        List<WebElement> inputElements = driver.findElements(By.tagName("input"));
        List<WebElement> buttonElements = driver.findElements(By.tagName("button"));

        // Xử lý từng hành động
        for (String action : actions) {
            String xpath = null;

            if (action.toLowerCase().contains("fill username")) {
                xpath = findMatchingElement(inputElements, "username");
            } else if (action.toLowerCase().contains("fill password")) {
                xpath = findMatchingElement(inputElements, "password");
            } else if (action.toLowerCase().contains("click login")) {
                xpath = findMatchingElement(inputElements, "login");
            } else {
                xpath = "No matching element found for action: " + action;
            }
            result.put(action, xpath);
        }
//        driver.quit();
        return result;

    }

    public static String findMatchingElement(List<WebElement> elements, String keyword) {
        for (WebElement element : elements) {
            String id = element.getAttribute("id");
            String name = element.getAttribute("name");
            String placeholder = element.getAttribute("placeholder");

            if ((id != null && id.toLowerCase().contains(keyword)) ||
                    (name != null && name.toLowerCase().contains(keyword)||
                            (placeholder != null && placeholder.toLowerCase().contains(keyword)))) {
                return getXPath(element);
            }
        }
        return "No matching element found";
    }

    public static Map<String, String> performActions(WebDriver driver, Map<String, String> xpaths, String[] actions, Testcase testcase) {

        Map<String, String> actionResults = new LinkedHashMap<>();

        xpaths.forEach((action, xpath) -> {
            try {
                if (xpath != null && !xpath.equals("No matching element found")) {
                    WebElement element = driver.findElement(By.xpath(xpath));

                    if (action.toLowerCase().contains("fill username")) {
                        element.sendKeys(testcase.username);
                        element.clear();
                        Thread.sleep(2000);
//                        actionResults.put(action, "Success: Username filled");
                    } else if (action.toLowerCase().contains("fill password")) {
                        element.sendKeys(testcase.password);
                        element.clear();
                        Thread.sleep(2000);
//                        actionResults.put(action, "Success: Password filled");
                    } else if (action.toLowerCase().contains("click login")) {
                        element.click();
                        Thread.sleep(2000);
//                        actionResults.put(action, "Success: Login button clicked");
                    }
                } else {
                    actionResults.put(action, "Error: Unable to locate element");
                }
            } catch (Exception e) {
                actionResults.put(action, "Error: " + e.getMessage());
            }
        });

        return actionResults;
    }

    public static String getXPath(WebElement element) {
        String tagName = element.getTagName();
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
            return getAbsoluteXPath(element) ;
        }
    }

    private static String getAbsoluteXPath(WebElement element) {
        String script = "function absoluteXPath(element) {" +
                "var comp, comps = [];" +
                "var parent = null;" +
                "var xpath = '';" +
                "var getPos = function(element) {" +
                "var position = 1, curNode;" +
                "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {" +
                "if (curNode.nodeName == element.nodeName) {" +
                "++position;" +
                "}" +
                "}" +
                "return position;" +
                "};" +

                "if (element instanceof Document) {" +
                "return '/';" +
                "}" +

                "for (; element && !(element instanceof Document); element = element.parentNode) {" +
                "comp = comps[comps.length] = {};" +
                "comp.name = element.nodeName;" +
                "comp.position = getPos(element);" +
                "}" +

                "for (var i = comps.length - 1; i >= 0; i--) {" +
                "comp = comps[i];" +
                "xpath += '/' + comp.name.toLowerCase();" +
                "if (comp.position !== null) {" +
                "xpath += '[' + comp.position + ']';" +
                "}" +
                "}" +

                "return xpath;" +
                "} return absoluteXPath(arguments[0]);";
        return script;
    }
}

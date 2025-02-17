package org.example.testthaychi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapXpath {
    public static void main(String[] args) {
        String description = "Fill Username \nFill Password \nClick login";
//        String description = "ten truy cap \nmat khau \ndang nhap";
        String targetURL = "http://saucedemo.com";
//        String targetURL = "http://dksdh.vnu.edu.vn/dkmh/login.asp";

        WebDriver driver = new ChromeDriver();
        driver.get(targetURL);

        Map<String, String> xpaths = getAllXPaths(description, targetURL);

        xpaths.forEach((key, value) -> System.out.println(key + " => " + value));


    }

    public static Map<String, String> getAllXPaths(String description, String targetURL) {
        WebDriver driver = new ChromeDriver();
        driver.get(targetURL);

//        driver.findElement(By.xpath(""))

        String[] actions = description.split("\n");
        Map<String, String> result = new HashMap<>();

        List<WebElement> inputElements = driver.findElements(By.tagName("input"));
        List<WebElement> buttonElements = driver.findElements(By.tagName("button"));

        // Xử lý từng hành động
        for (String action : actions) {
            String xpath = null;
//            String xpath = "";

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



//    public static void main(String[] args) {
//        // Mô tả các hành động
//        String[] actions = {"Fill password", "Click login", "Fill username"};
//
//        WebDriver driver = new ChromeDriver();
//            driver.get("http://saucedemo.com");
////        driver.get("http://dksdh.vnu.edu.vn/dkmh/login.asp");
////        driver.get("https://qldt.utc.edu.vn/CMCSoft.IU.Web.info/(S(mbssz0uzdnzqxa2x5clbczak))/Login.aspx");
//
//        // Tìm tất cả các input trên trang
//        List<WebElement> inputElements = driver.findElements(By.tagName("input"));
//        // Lặp qua cả hai danh sách đồng thời
//        int minSize = Math.min(actions.length, inputElements.size());
//        for (int i = 0; i < minSize; i++) {
//            String action = actions[i];
//            WebElement input = inputElements.get(i);
//            String xpath = getXPath(input);
//            System.out.println(action + " => " + xpath);
//        }
//        driver.quit();
//    }
}

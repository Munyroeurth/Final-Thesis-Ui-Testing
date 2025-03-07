package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String descripion = "Fill username\nFill password\nClick login";
        String targetURL = "http://saucedemo.com";
        getAllXPaths(descripion, targetURL); //Xpaths cua username, password, login

        WebDriver driver;
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        driver.get("https://www.saucedemo.com/");
        List<WebElement> inputElements = driver.findElements(By.tagName("input"));

        for(WebElement input :inputElements){
            String inputId = input.getAttribute("id");
            String inputXpath = getXPath(input);
            String inputAbsoluteXPath = getAbsoluteXPath(input);

            System.out.printf("inputId :" + inputId +"\n");

            System.out.printf("inputXpath : " + inputXpath + "\n");

//            System.out.printf("inputAbsoluteXPath : " + inputAbsoluteXPath + "\n");
        }

        driver.quit();
    }

    //
    public static List<String> getAllXPaths(String description, String targetURL){
        return null;
    }

    public static String getXPath(WebElement element) {
        String id = element.getAttribute("id");

        if (id != null && !id.isEmpty()) {
            return "//*[@id='" + id + "']";
        } else {
            return getAbsoluteXPath(element);
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
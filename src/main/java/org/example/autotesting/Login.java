package org.example.autotesting;

import org.example.autotesting.constant.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    private WebDriver driver;

    public Login(WebDriver webDriver) {
        this.driver = webDriver;
    }
    public void loginFunctions(String userName, String password){
        WebElement txt_username = driver.findElement(By.xpath(Locator.TXT_USERNAME));
        if (txt_username.isDisplayed()){
            txt_username.clear();
            txt_username.sendKeys(userName);
        }
        WebElement txt_pwd = driver.findElement(By.xpath(Locator.TXT_PASSWORD));
        if (txt_pwd.isDisplayed()){
            txt_pwd.clear();
            txt_pwd.sendKeys(password);
        }
        WebElement btn_login = driver.findElement(By.xpath(Locator.BTN_LOGIN));
        if (btn_login.isDisplayed())
        {
            btn_login.click();
        }

    }
}

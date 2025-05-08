package org.example.autotesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
    private WebDriver driver;
    @FindBy(id = "username") WebElement txtUserName;

    @FindBy(id ="password") WebElement txtPass;

    @FindBy(xpath = "//*[@id=\"login-button\"]") WebElement btnLogin;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public void loginFunction (String userName, String pass)
    {
        txtUserName.clear();
        txtUserName.sendKeys(userName);

        txtPass.clear();
        txtPass.sendKeys(pass);

        btnLogin.click();
    }
}

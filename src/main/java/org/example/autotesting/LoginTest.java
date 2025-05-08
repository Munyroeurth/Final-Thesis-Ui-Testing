package org.example.autotesting;

import org.example.autotesting.common.CommonBase;
import org.example.autotesting.constant.Common;
import org.junit.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LoginTest extends CommonBase {
    public LoginTest() {
    }
    @BeforeMethod
    public void openChrome () throws InterruptedException {
        driver = initChromeDriver(Common.URL_SAUCE_DEMO);
    }
    @Test
    public void loginSuccess() throws InterruptedException{
        Login login =  new Login(driver);
        login.loginFunctions("admin12345","234567");
    }
    @AfterMethod
    public void closeDriver()
    {
        driver.close();
    }
}

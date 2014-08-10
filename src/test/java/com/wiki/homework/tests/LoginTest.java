package com.wiki.homework.tests;

import com.wiki.homework.pageobjects.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class LoginTest extends TestBase {
    public void login() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.openPage();
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "http://testhomework.wikia.com/wiki/Test-homework_Wiki",
                "This is not home page"
        );

        homePage.mouseOverLogin().loginUser("testUserName", "test123");
        Assert.assertTrue(
                homePage.getUserNameAfterLogin().contains("TestUserName"),
                "You are not logged in as testUserName"
        );
    }
}

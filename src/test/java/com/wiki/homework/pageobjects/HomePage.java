package com.wiki.homework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(className = "ajaxLogin")
    private WebElement loginElement;

    @FindBy(id = "UserLoginDropdown")
    private WebElement loginDropdown;

    @FindBy(name ="username")
    private WebElement userName;

    @FindBy(how = How.NAME, using = "password")
    private WebElement password;

    @FindBy(className  ="login-button")
    private WebElement loginButton;

    @FindBy(id = "AccountNavigation")
    private WebElement accountNavigation;

    @FindBy(className = "contribute")
    private WebElement contributeButton;

    @FindBy(partialLinkText =  "Add a Video")
    private WebElement addVideo;

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    private String pageUrl = "http://testhomework.wikia.com";

    public HomePage openPage() {
        this.driver.get(pageUrl);
        return this;
    }

    public HomePage mouseOverLogin() {
        Actions action = new Actions(this.driver);
        action.moveToElement(loginElement).perform();
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String loginAttribute = webDriver.findElement(By.id("UserLoginDropdown")).getAttribute("class");
                return loginAttribute.contains("show");
            }
        });
        return this;
    }

    public HomePage typeUsername(String username) {
        this.userName.sendKeys(username);
        return this;
    }

    public HomePage typePassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public HomePage submitLogin() {
        this.loginButton.submit();
        return this;
    }

    public HomePage loginUser(String username, String password) {
        typeUsername(username);
        typePassword(password);
        submitLogin();
        return this;
    }

    public String getUserNameAfterLogin() {
        return this.accountNavigation.getText();
    }

    public HomePage clickContributeButton() {
        this.contributeButton.click();
        return this;
    }

    public Boolean isDropdownExpanded() {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String loginAttribute = webDriver.findElement(By.className("contribute")).getAttribute("class");
                return loginAttribute.contains("active");
            }
        });
        WebElement el = driver.findElement(By.cssSelector(".contribute.active"));
        return el != null;
    }

    public AddVideoPage clickAddVideoOnDropdown() {
        this.addVideo.click();
        return new AddVideoPage(driver);
    }
}

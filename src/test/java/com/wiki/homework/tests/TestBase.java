package com.wiki.homework.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.*;
import java.io.File;

public class TestBase {
    private final String MAC_DRIVER = "/resources/chrmoedrivers/chromedriver_mac";
    private final String LINUX_DRIVER = "/resources/chrmoedrivers/chromedriver_linux";
    private final String WINDOWS_DRIVER = "/resources/chrmoedrivers/chromedriver.exe";

    public WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void getDriver(@Optional String browser) {
        if (browser==null||browser.equals("")||browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            setupChromeDriverPath();
            driver = new ChromeDriver();
        }
    }

    public void setupChromeDriverPath() {
        File cDriver = null;
        if (System.getProperty("os.name").contains("Mac")) {
            cDriver = new File(TestBase.class.getResource(MAC_DRIVER).getFile());
        } else if (System.getProperty("os.name").contains("Linux")) {
            cDriver = new File(TestBase.class.getResource(LINUX_DRIVER).getFile());
        } else if (System.getProperty("os.name").contains("Windows")) {
            cDriver = new File(TestBase.class.getResource(WINDOWS_DRIVER).getFile());
        }
        System.setProperty("webdriver.chrome.driver", cDriver.getAbsolutePath());
    }

    @AfterClass
    public void afterTest() {
        driver.quit();
    }
}

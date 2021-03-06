package com.wiki.homework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddVideoPage {
    @FindBy(id = "wpWikiaVideoAddUrl")
    private WebElement videoAddUrl;

    @FindBy(className  = "submits")
    private WebElement videoAddSubmit;

    private final WebDriver driver;

    public AddVideoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AddVideoPage typeVideoUrl(String videoUrl) {
        this.videoAddUrl.sendKeys(videoUrl);
        return this;
    }

    public VideosPage submitAddingVideo() {
        this.videoAddSubmit.submit();
        return new VideosPage(driver);
    }
}

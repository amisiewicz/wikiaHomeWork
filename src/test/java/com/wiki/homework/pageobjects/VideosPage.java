package com.wiki.homework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VideosPage {

    @FindBy(css = ".msg a")
    private WebElement videoAddedMessage;

    @FindBy(partialLinkText = "File:")
    private WebElement videoLink;

    private final WebDriver driver;

    public VideosPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getAddedVideoTitleFromMessage() {
        return this.videoAddedMessage.getText();
    }

    public VideosPage clickLinkInMessage() {
        this.videoLink.click();
        return this;
    }
}

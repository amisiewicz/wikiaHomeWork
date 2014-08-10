package com.wiki.homework.tests;

import com.wiki.homework.pageobjects.AddVideoPage;
import com.wiki.homework.pageobjects.HomePage;
import com.wiki.homework.pageobjects.VideosPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class AddVideoTest extends TestBase {
    public void addVideo() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.openPage() //
                .mouseOverLogin() //
                .loginUser("testUserName", "test123") //
                .clickContributeButton(); //

        Assert.assertTrue(homePage.isDropdownExpanded(), "Dropdown did not expand");

        AddVideoPage addVideoPage = homePage.clickAddVideoOnDropdown();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "http://testhomework.wikia.com/wiki/Special:WikiaVideoAdd",
                "This is not adding video page"
        );

        addVideoPage.typeVideoUrl("http://www.youtube.com/watch?v=h9tRIZyTXTI");
        VideosPage videosPage = addVideoPage.submitAddingVideo();
        String addedVideoTitleFromMessage=videosPage.getAddedVideoTitleFromMessage();

        Assert.assertTrue(addedVideoTitleFromMessage.contains("The Best Classical Music In The World"),
                "Added video title is different then expected"
        );

        videosPage.clickLinkInMessage();

        String fileName = driver.getCurrentUrl();
        Assert.assertTrue(
                fileName.contains(addedVideoTitleFromMessage.replace(' ', '_')),
                "Filename is different then video title showed in flash message"
        );
    }
}

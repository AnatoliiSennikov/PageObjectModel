package com.otus;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class GitHubTest {
    protected static WebDriver driver;

    @Test
    public void githubTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://github.com/");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenide");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//a[@href='/selenide/selenide']")).click();
        driver.findElement(By.xpath("//nav[@aria-label='Repository']//span[text()='Issues']")).click();
        driver.findElement(By.xpath("//a[@data-hovercard-type='issue']")).click();

        driver.findElements(By.xpath("//a[@data-hovercard-type='issue']")).get(0).click();

        String title =
                driver.findElement(By.xpath("//*[@id=\"partial-discussion-header\"]/div[2]/div[1]/span")).getAttribute("title");

        Assert.assertEquals(title, "Status: Open");

        driver.quit();
    }
}

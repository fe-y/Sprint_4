package ru.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.scooter.pageobjects.MainPage;
import ru.praktikum.scooter.pageobjects.OrderPage;
import ru.praktikum.scooter.pageobjects.RentPage;

public class AccordionTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru");

        mainPage = new MainPage(driver);
    }

    @Test
    public void testQuestion0() {
        mainPage.clickQuestion(0);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

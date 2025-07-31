package ru.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.scooter.pageobjects.MainPage;
import ru.praktikum.scooter.pageobjects.OrderPage;
import ru.praktikum.scooter.pageobjects.RentPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;

    public OrderScooterTest(String name, String surname, String address, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][] {
                {"Анна", "Иванова", "ул. Ленина, 1", "89111112233"},
                {"Пётр", "Сидоров", "ул. Пушкина, 5", "89222223344"},
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver(); // не headless — будто забыли
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void testOrderScooter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickUpperOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, surname, address, phone);


    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
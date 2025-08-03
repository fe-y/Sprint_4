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

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    private WebDriver driver;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phone;

    public OrderScooterTest(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    @Parameterized.Parameters(name = "Test data: {0} {1}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"Анна", "Иванова", "ул. Ленина, 1", "89111112233"},
                {"Пётр", "Сидоров", "ул. Пушкина, 5", "89222223344"},
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void orderFromHeaderButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickQuestion();
        mainPage.clickUpperOrderButton();

        fillOrderForm();

        RentPage rentPage = new RentPage(driver);
        rentPage.setDate("05.08.2025");
        rentPage.setRentalPeriod();
        rentPage.selectColor();
        rentPage.setComment("Позвонить за 10 минут");
        rentPage.submitOrder();
        rentPage.confirmOrder();

        assertTrue("Заказ не оформлен", rentPage.isOrderConfirmed());
    }

    @Test
    public void orderFromMiddleButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickQuestion();
        mainPage.clickLowerOrderButton();

        fillOrderForm();

        RentPage rentPage = new RentPage(driver);
        rentPage.setDate("15.08.2025");
        rentPage.setRentalPeriod();
        rentPage.selectColor();
        rentPage.setComment("Проверка");
        rentPage.submitOrder();
        rentPage.confirmOrder();

        assertTrue("Заказ не оформлен", rentPage.isOrderConfirmed());
    }

    private void fillOrderForm() {
        OrderPage orderPage = new OrderPage(driver);

        orderPage.setFirstName(firstName);
        orderPage.setLastName(lastName);
        orderPage.setAddress(address);
        orderPage.setMetroStation("Тверская");  // Можно тоже параметризовать, если нужно
        orderPage.setPhoneNumber(phone);
        orderPage.clickNextButton();
    }
}
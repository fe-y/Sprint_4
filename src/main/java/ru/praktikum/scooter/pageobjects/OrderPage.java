package ru.praktikum.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStation = By.className("select-search__input");
    private By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.cssSelector(".Button_Middle__1CSJM");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderForm(String name, String surname, String address, String phoneNumber) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStation).click();
        driver.findElement(By.className("select-search__row")).click();
        driver.findElement(phone).sendKeys(phoneNumber);
        driver.findElement(nextButton).click();
    }
}


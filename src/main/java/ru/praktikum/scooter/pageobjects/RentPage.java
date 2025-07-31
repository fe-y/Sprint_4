package ru.praktikum.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {

    private WebDriver driver;

    // Локаторы
    private By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodDropdown = By.className("Dropdown-control");
    private By rentalPeriodOption1Day = By.xpath("//div[text()='сутки']");
    private By scooterColorBlack = By.id("black");
    private By scooterColorGrey = By.id("grey");
    private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath("//button[text()='Заказать']");
    private By confirmOrderButton = By.xpath("//button[text()='Да']");
    private By successMessage = By.xpath("//div[contains(text(),'Заказ оформлен')]");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDeliveryDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
    }

    public void chooseRentalPeriodOneDay() {
        driver.findElement(rentalPeriodDropdown).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriodOption1Day))
                .click();
    }

    public void selectColorBlack() {
        driver.findElement(scooterColorBlack).click();
    }

    public void selectColorGrey() {
        driver.findElement(scooterColorGrey).click();
    }

    public void enterComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(confirmOrderButton))
                .click();
    }

    public boolean isOrderConfirmed() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(successMessage))
                .isDisplayed();
    }
}
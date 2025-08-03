package ru.praktikum.scooter.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {

    private final WebDriver driver;

    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-control");
    private final By rentalPeriodOption = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']");
    private final By colorCheckbox = By.id("black");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By confirmYesButton = By.xpath("//button[text()='Да']");
    private final By orderConfirmationModalHeader = By.className("Order_ModalHeader__3FDaJ");
    private final By orderConfirmationModal = By.className("Order_Modal__Y0Vsz");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDate(String date) {
        WebElement input = driver.findElement(dateInput);
        input.clear();
        input.sendKeys(date);
        input.sendKeys(Keys.ENTER);
    }

    public void setRentalPeriod() {
        driver.findElement(rentalPeriodDropdown).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriodOption)).click();
    }

    public void selectColor() {
        driver.findElement(colorCheckbox).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void submitOrder() {
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(confirmYesButton))
                .click();
    }

    public String getOrderConfirmationText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement modalHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationModalHeader));
            return modalHeader.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public boolean isOrderConfirmed() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationModal))
                    .isDisplayed();
        } catch (TimeoutException e) {
            System.err.println("Окно подтверждения заказа не появилось (баг в Chrome).");
            return false;
        }
    }
    public String getOrderSuccessMessage() {
        return driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText();
    }

}
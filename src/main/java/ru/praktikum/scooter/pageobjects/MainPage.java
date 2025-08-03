package ru.praktikum.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    private By upperOrderButton = By.xpath("//button[text()='Заказать']");
    private By lowerOrderButton = By.xpath("(//button[text()='Заказать'])[2]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickQuestion(int index) {
        acceptCookiesIfVisible();
        WebElement question = driver.findElement(By.id("accordion__heading-" + index));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", question);
        question.click();
    }

    public String getAccordionAnswerText(int index) {
        clickQuestion(index);
        By answerLocator = By.id("accordion__panel-" + index);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return driver.findElement(answerLocator).getText();
    }

    private void acceptCookiesIfVisible() {
    }

    public void clickUpperOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(upperOrderButton))
                .click();
    }

    public void clickLowerOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(lowerOrderButton))
                .click();
    }

    public void clickQuestion() {
    }
}
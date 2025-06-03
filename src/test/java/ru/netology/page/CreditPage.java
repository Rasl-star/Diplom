package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CreditPage {
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement nameField = $("[data-test-id='name'] input");
    private final SelenideElement cvcField = $("[placeholder='999']");
    private final SelenideElement continueButton = $("[data-test-id='action-continue']");

    private final SelenideElement successNotification = $("[data-test-id='notification-success']");
    private final SelenideElement errorNotification = $("[data-test-id='notification-error']");

    public void fillCardInfo(String number, String month, String year, String name, String cvc) {
        cardNumberField.setValue(number);
        monthField.setValue(month);
        yearField.setValue(year);
        nameField.setValue(name);
        cvcField.setValue(cvc);
        continueButton.click();
    }

    public void verifySuccessNotification() {
        successNotification.shouldBe(visible);
    }

    public void verifyErrorNotification() {
        errorNotification.shouldBe(visible);
    }
}

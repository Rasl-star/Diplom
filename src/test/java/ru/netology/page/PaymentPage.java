package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

public class PaymentPage {
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement holderField = $("[placeholder='ИВАН ИВАНОВ']");
    private final SelenideElement cvcField = $("[placeholder='999']");
    private final SelenideElement continueButton = $("button.button");
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");

    public void fillCardInfo(String cardNumber, String month, String year, String holder, String cvc) {
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        holderField.setValue(holder);
        cvcField.setValue(cvc);
        continueButton.click();
    }

    public void verifySuccessNotification() {
        successNotification.shouldBe(visible).shouldHave(text("Операция одобрена Банком"));
    }

    public void verifyErrorNotification() {
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка! Банк отказал в проведении операции."));
    }
}

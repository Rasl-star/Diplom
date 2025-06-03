package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class TourPage {

    private final SelenideElement heading = $("h2.heading");
    private final SelenideElement payByCardButton = $("button[data-test-id='pay-by-card']");
    private final SelenideElement creditButton = $$("button").findBy(Condition.text("Купить в кредит"));

    public TourPage() {
        heading.shouldHave(text("Путешествие дня"));
    }

    public PaymentPage payByCard() {
        payByCardButton.click();
        return new PaymentPage();
    }

    public CreditPage payByCredit() {
        creditButton.click();
        return new CreditPage();
    }
}
package ru.netology.test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DbHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.PaymentPage;
import ru.netology.page.TourPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Tests")
@Feature("Purchase Tour - Negative Scenarios")
@Owner("your-name")
public class UINegativeTest {

    @BeforeEach
    void setup() {
        Configuration.headless = true;
        DbHelper.clearTables();
        open("http://localhost:8080");
    }

    @Test
    @Story("Card Payment Declined")
    @Severity(SeverityLevel.NORMAL)
    void shouldShowErrorIfCardDeclined() {
        PaymentPage paymentPage = new TourPage().payByCard();
        paymentPage.fillCardInfo("4444 4444 4444 4442", "12", "25", "IVAN PETROV", "123"); // DECLINED карта
        paymentPage.verifyErrorNotification();

        String actualStatus = DbHelper.getPaymentStatus();
        assertEquals("DECLINED", actualStatus);
    }

    @Test
    @Story("Credit Purchase Declined")
    @Severity(SeverityLevel.NORMAL)
    void shouldShowErrorIfCreditDeclined() {
        CreditPage creditPage = new TourPage().payByCredit();
        creditPage.fillCardInfo("4444 4444 4444 4442", "12", "25", "ANNA SIDOROVA", "456"); // DECLINED карта
        creditPage.verifyErrorNotification();

        String actualStatus = DbHelper.getCreditStatus();
        assertEquals("DECLINED", actualStatus);
    }

    @Test
    @Story("Invalid Card Number")
    @Severity(SeverityLevel.CRITICAL)
    void shouldNotSubmitWithInvalidCardNumber() {
        PaymentPage paymentPage = new TourPage().payByCard();
        paymentPage.fillCardInfo("1234 5678 9012 3456", "12", "25", "TEST TEST", "999"); // несуществующая карта
        paymentPage.verifyErrorNotification();

        assertNull(DbHelper.getPaymentStatus());
    }

    @Test
    @Story("Empty Form Submission")
    @Severity(SeverityLevel.CRITICAL)
    void shouldShowValidationErrorsIfFieldsEmpty() {
        PaymentPage paymentPage = new TourPage().payByCard();
        paymentPage.fillCardInfo("", "", "", "", "");
        paymentPage.verifyErrorNotification(); // при пустых полях должна быть валидация

        assertNull(DbHelper.getPaymentStatus());
    }
}

package ru.netology.test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DbHelper;
import ru.netology.page.PaymentPage;
import ru.netology.page.TourPage;
import ru.netology.page.CreditPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI Tests")
@Feature("Purchase Tour")
@Story("Successful Tour Purchase by Credit")
@Story("Successful Tour Purchase")
@Owner("your-name")
@Severity(SeverityLevel.CRITICAL)
public class UIPositiveTest {

    @BeforeEach
    void setup() {
        Configuration.headless = true; // Можно убрать, если нужен запуск с UI
        DbHelper.clearTables(); // очистка таблиц перед тестом
    }

    @Test
    void shouldBuyTourByCardSuccessfully() {
        open("http://localhost:8080");
        TourPage tourPage = new TourPage();
        PaymentPage paymentPage = tourPage.payByCard();

        // Данные карты со статусом APPROVED
        paymentPage.fillCardInfo("4444 4444 4444 4441", "12", "25", "IVAN IVANOV", "123");
        paymentPage.verifySuccessNotification();

        // SQL-проверка
        String actualStatus = DbHelper.getPaymentStatus();
        assertEquals("APPROVED", actualStatus);
    }


    @Test
    void shouldBuyTourByCreditSuccessfully() {
        open("http://localhost:8080");
        TourPage tourPage = new TourPage();
        CreditPage creditPage = tourPage.payByCredit();

        // Карта со статусом APPROVED
        creditPage.fillCardInfo("4444 4444 4444 4441", "12", "25", "IVAN IVANOV", "123");
        creditPage.verifySuccessNotification();

        // SQL-проверка
        String actualStatus = DbHelper.getCreditStatus();
        assertEquals("APPROVED", actualStatus);
    }
}
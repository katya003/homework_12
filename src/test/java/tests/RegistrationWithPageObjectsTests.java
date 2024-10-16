package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;

public class RegistrationWithPageObjectsTests extends TestBase {
    String firstName = "Kate";
    String lastName = "Mar";
    String userEmail = "kate@kate.ru";
    String gender = "Male";
    String userNumber = "8933333333";
    String dayOfBirth = "03";
    String monthOfBirth = "March";
    String yearOfBirth = "1992";
    String subjectsOne = "History";
    String hobbiesOne = "Sports";
    String pictureName = "test.jpg";
    String address = "test";
    String state = "NCR";
    String city = "Delhi";

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @Tag("fullFormTest")
    @DisplayName("Проверка заполнения формы")
    public void allFullFormTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть форму", () -> {
            registrationPage.openPage()
                    .removeBanner();
        });
        step("Заполнить форму", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(userNumber)
                    .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                    .setSubject(subjectsOne)
                    .setHobby(hobbiesOne)
                    .uploadPicture(pictureName)
                    .setAddress(address)
                    .setState(state)
                    .setCity(city)
                    .clickSubmit();
        });
        step("Проверить форму", () -> {

            registrationPage.checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Student Email", userEmail)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", userNumber)
                    .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                    .checkResult("Subjects", subjectsOne)
                    .checkResult("Hobbies", hobbiesOne)
                    .checkResult("Picture", pictureName)
                    .checkResult("Address", address)
                    .checkResult("State and City", state + " " + city);
        });
    }
}

package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleWebTest {

    static {
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {
        open("https://www.google.com/");
    }

    @CsvSource(value = {
            "https://selenide.org |  Selenide  | 45.34  | true",
            "https://junit.org    |  JUnit 5   | 34.56  | false"
    },
    delimiter = '|')
//    @CsvFileSource(resources = "/successfulSearchTextTest.csv")
    @Tags({
            @Tag("smoke"), // BLOCKER
            @Tag("web")
    })
    @DisplayName("Проверка поиска в google")
    @ParameterizedTest(name = "В поисковой выдаче google присутствует url {0} для запроса {1}")
    void successfulSearchTextTest(String url, String searchQuery, double i, boolean b) {
        $("[name=q]")
                .setValue(searchQuery)
                .pressEnter();



        $("[id=search]").shouldHave(text(url));
    }



    @ValueSource(
            strings = {"Selenide", "JUnit 5"}
    )
//    @CsvFileSource(resources = "/successfulSearchTextTest.csv")
    @Tags({
            @Tag("smoke"), // BLOCKER
            @Tag("web")
    })
    @DisplayName("Проверка поиска в google")
    @ParameterizedTest(name = "В поисковая выдача google не пустая для запроса {0}")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("[name=q]")
                .setValue(searchQuery)
                .pressEnter();


        $$("[id=search] div").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    static Stream<Arguments> selenideSiteLocaleTest() {
        return Stream.of(
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")),
                Arguments.of(Locale.RU, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"))
        );
    }

    @MethodSource
    @ParameterizedTest
    void selenideSiteLocaleTest(Locale locale, List<String> expectedButtons) {
        open();

    }

}

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class TestLocalization {
    LocalizationServiceImpl localization;

    @BeforeAll
    public static void startedAll() {
        System.out.println("Tests started");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @BeforeEach
    public void started() {
        System.out.println("test started");
        localization = new LocalizationServiceImpl();
    }

    @AfterEach
    public void finished() {
        System.out.println("test completed");
    }

    @Test
    public void testByIp() {
        Country country = Country.USA;

        String expected = "Welcome";

        String result = localization.locale(country);

        Assertions.assertEquals(expected, result);
    }


}

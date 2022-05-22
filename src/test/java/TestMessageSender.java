import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TestMessageSender {
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
    }

    @AfterEach
    public void finished() {
        System.out.println();
        System.out.println("test completed");
    }

    @ParameterizedTest
    @MethodSource("getIp")
    public void rusMessage(String ip, String expected) {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        Location location = Mockito.mock(Location.class);
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(headers);
        Assertions.assertEquals(expected, result);

    }

    static Stream<Arguments> getIp() {
        return Stream.of(Arguments.of("96.44.183.149", "Welcome"), Arguments.of("72.0.32.11", "Добро пожаловать"),
                Arguments.of("96.33.111.444", "Welcome"));
    }

}

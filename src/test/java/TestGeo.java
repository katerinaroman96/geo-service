import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

public class TestGeo {
    GeoServiceImpl geo;

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
        geo = new GeoServiceImpl();
    }

    @AfterEach
    public void finished() {
        System.out.println("test completed");
    }

    @Test
    public void testByIp() {
        String ip = "172.0.32.11";
        Country expected = Country.RUSSIA;

        Country result = geo.byIp(ip).getCountry();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testByCoordinates() {
        Assertions.assertThrows(RuntimeException.class,
                () -> geo.byCoordinates(56, 38));
    }

}

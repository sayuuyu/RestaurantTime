package restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import outils.Carte;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantALaCarteTest {
    private final int intervalleReservationEnMin = 30;
    private final int nbJoursEnAvance = 7;
    private RestaurantALaCarte myRestaurantALaCarte;
    private Carte carte; // Assuming the Carte class exists
    private Calendar[][] horaireOuverture;
    private int[] nbPersonnes;

    @BeforeEach
    void setUp() {

        carte = new Carte(null, 0);
        horaireOuverture = new Calendar[7][2];
        nbPersonnes = new int[]{2, 4, 6};
        for (int i = 0; i < 7; i++) {
            horaireOuverture[i][0] = new GregorianCalendar(2024, Calendar.JANUARY, 1, 9, 0);
            horaireOuverture[i][1] = new GregorianCalendar(2024, Calendar.JANUARY, 1, 17, 0);
        }
        myRestaurantALaCarte = new RestaurantALaCarte("Chez Sayu", carte, "123 Main Street", "A cozy place", horaireOuverture, nbPersonnes, intervalleReservationEnMin, nbJoursEnAvance);
    }

    @Test
    void jourLibres() {
        int nbPersonnes = 4;
        int[] libres = myRestaurantALaCarte.jourLibres(nbPersonnes);
        assertNotNull(libres);
        assertTrue(libres.length > 0);
    }

    @Test
    void horairesLibres() {
        int[] informations = new int[]{1, 2, 0};
        Calendar[] horaires = myRestaurantALaCarte.horairesLibres(informations);
        assertNotNull(horaires);
        assertTrue(horaires.length > 0);
    }

    @Test
    void tablesLibres() {
        int nbPersonnes = 4;
        int iDate = 1;
        int[] libres = myRestaurantALaCarte.tablesLibres(nbPersonnes, iDate);
        assertNotNull(libres);
        assertTrue(libres.length > 0);
    }

    @Test
    void annuler() {
        myRestaurantALaCarte.reserverRestaurant(4, "123", 0, 1, 0);
        assertTrue(myRestaurantALaCarte.annuler("123"));
    }

    @Test
    void getHorairesLibres() {
        int[] info = new int[]{1, 2, 0};
        int[] libres = myRestaurantALaCarte.getHorairesLibres(info);
        assertNotNull(libres);
        assertTrue(libres.length > 0);
    }

    @Test
    void reserverRestaurant() {
        myRestaurantALaCarte.reserverRestaurant(4, "123", 0, 1, 0);
        assertEquals(1, myRestaurantALaCarte.getReservations().length);
    }
}

package outils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {
    private Agenda agenda;

    @BeforeEach
    void setUp() {
        // Initialize hours
        Calendar[] hours = new Calendar[2];
        hours[0] = Calendar.getInstance();
        hours[0].set(Calendar.HOUR_OF_DAY, 9);
        hours[1] = Calendar.getInstance();
        hours[1].set(Calendar.HOUR_OF_DAY, 10);

        agenda = new Agenda(hours,  10);
    }

    @Test
    void reinitialiserAgenda() {
        // Initialize with new hours
        Calendar[] newHours = new Calendar[3];
        newHours[0] = Calendar.getInstance();
        newHours[0].set(Calendar.HOUR_OF_DAY, 11);
        newHours[1] = Calendar.getInstance();
        newHours[1].set(Calendar.HOUR_OF_DAY, 12);
        // Intentionally leaving a null value to test the class's robustness
        newHours[2] = null;

        agenda.reinitiliaserAgenda(newHours);

        Calendar[] updatedHours = agenda.getHoraires();
        assertArrayEquals(new Calendar[] { newHours[0], newHours[1] }, updatedHours, "The hours should be updated to new hours, excluding null values.");
    }

    @Test
    void estLibre() {
        assertTrue(agenda.estLibre(5), "Should return true as there is enough space.");
        assertFalse(agenda.estLibre(15), "Should return false as there is not enough space.");
    }

    @Test
    void annulerReservation() {
        InformationsReservations reservation = agenda.reserver(5, "12345", 0, 0, 0);
        assertNotNull(reservation, "Reservation should be successfully made before cancellation.");

        assertTrue(agenda.annulerReservation(reservation), "Reservation should be cancelled successfully.");
    }

    @Test
    void reserver() {
        InformationsReservations reservation = agenda.reserver(5, "12345", 0, 0, 0);
        assertNotNull(reservation, "Reservation should be created and not null.");
    }
}

package outils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    Table table;
    Calendar[] horaires;
    InformationsReservations info;

    @BeforeEach
    void setUp() {
        Calendar moinsUn = Calendar.getInstance();
        moinsUn.add(Calendar.HOUR_OF_DAY, -1);
        Calendar zero = Calendar.getInstance();
        zero.add(Calendar.HOUR_OF_DAY, 0);
        Calendar plusUn = Calendar.getInstance();
        plusUn.add(Calendar.HOUR_OF_DAY, 1);
        Calendar PlusDeux = Calendar.getInstance();
        PlusDeux.add(Calendar.HOUR_OF_DAY, 2);
        horaires = new Calendar[]{moinsUn, zero, plusUn, PlusDeux};
        table = new Table(4, horaires);
        info = table.reserver(4, "code123", 1, 0, 0);
    }

    @Test
    void getHorairesLibres() {
        assertNotNull(table.getHorairesLibres(4), "Should return non-null for matching number of people");
        assertNull(table.getHorairesLibres(5), "Should return null for non-matching number of people");
    }

    @Test
    void reinitiliaserTab() {
        Calendar[] newHoraires = new Calendar[]{ /* initialize with some different values */};
        table.reinitiliaserTab(newHoraires);
        assertArrayEquals(newHoraires, table.getHoraires(), "Horaires should be updated");
    }

    @Test
    void annulerReservation() {
        assertTrue(table.annulerReservation(info), "Should return true when canceling an existing reservation");
        assertFalse(table.annulerReservation(info), "Should return false when canceling a non-existing reservation");
    }

    @Test
    void getIndiceHorairesLibres() {
        assertNotNull(table.getIndiceHorairesLibres(4), "Should return non-null indices for free hours");
    }

    @Test
    void reserver() {
        assertNotNull(info, "Reservation should be successful and return info");
    }

    @Test
    void estLibre() {
        assertTrue(table.estLibre(4), "Should return true if table is free for given number of people");
        assertFalse(table.estLibre(5), "Should return false if table is not free for given number of people");
    }

}
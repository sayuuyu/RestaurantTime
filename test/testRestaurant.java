import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import outils.InformationsReservations;
import restaurant.*;

public class testRestaurant {
	private Calendar[][] horairesouverture;
	private RestaurantALaCarte restaurantALaCarte;
	private RestaurantBuffet restaurantBuffet;
	
	private int intervalleReservation;
	private InformationsReservations[] reservations;

	@Before
	public void setUp() throws Exception {
		int i;
		//int j, k, heureouv, heureferm;
		//Random r = new Random();
		for (i=0; i<6; i++) { //ouverture tous les jours à la même heure sauf le dimanche + ouverture mercredi soir
			horairesouverture[i][0].set(2024, 1, i+1, 11, 30);
			horairesouverture[i][1].set(2024, 1, i+1, 15, 00);
			if (i==2) {
				horairesouverture[i][2].set(2024, 1, i+1, 19, 0);
				horairesouverture[i][3].set(2024, 1, i+1, 23, 0);
			}
			
			/*j = r.nextInt(2);
			if (j==0) { // Le restaurant n'ouvre que le soir
				heureouv = r.nextInt(18, 20); //ouverture entre 18 et 20h exclu
				heureferm = r.nextInt(21, 24); //fermeture entre 21 et minuit exclu
				horairesouverture[i][0].set(2024, 1, i+1, heureouv, r.nextInt(60));
				horairesouverture[i][1].set(2024, 1, i+1, heureferm, r.nextInt(60));
			}
			
			if (j==1) { // Le restaurant ouvre le midi et le soir
				heureouv = r.nextInt(10, 13); //ouverture entre 10 et 13h
				heureferm = r.nextInt(14, 17); //fermeture entre 14 et 17h
				horairesouverture[i][0].set(2024, 1, i+1, heureouv, r.nextInt(60));
				horairesouverture[i][1].set(2024, 1, i+1, heureferm, r.nextInt(60));
				heureouv = r.nextInt(18, 20); //ouverture entre 18 et 20h exclu
				heureferm = r.nextInt(21, 24); //fermeture entre 21 et minuit exclu
				horairesouverture[i][2].set(2024, 1, i+1, heureouv, r.nextInt(60));
				horairesouverture[i][3].set(2024, 1, i+1, heureferm, r.nextInt(60));
				
			}*/
		}
		intervalleReservation = 25;
	}

	@Test
	public void testInitialisationAgendaSemaine() {
		restaurantALaCarte.initialisationAgendaSemaine(intervalleReservation);
	}

	@Test
	public void testADeLaReservation() {
		restaurantBuffet.aDeLaReservation("GtH5y7tu8");
	}

	@Test
	public void testJourLibres() {
		fail("Not yet implemented");
	}

	@Test
	public void testHorairesLibres() {
		fail("Not yet implemented");
	}

	@Test
	public void testActualiser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnnuler() {
		fail("Not yet implemented");
	}

}

package control;

import restaurant.Restaurant;

import java.util.Calendar;
import java.util.Random;

public class ControlReservation<T extends Restaurant> {
    //dataClient[0] i jour
    //dataClient{1] nbPersonnes
    //dataClient[2] iHoraire
    //dataClient[3] i tabOuAgenda

    protected T restaurant;
    protected Random random;


    public ControlReservation(T restaurant) {
        this.restaurant = restaurant;
        this.random = new Random();
    }

    public int[] getIndiceJoursLibres(int nbPersonnes) {
        return this.restaurant.jourLibres(nbPersonnes);
    }

    public String[] getJoursLibres(int nbPersonnes) {
        int[] tableauxindices = this.getIndiceJoursLibres(nbPersonnes);
        String[] joursLibres = new String[tableauxindices.length];
        Calendar jour;
        StringBuilder string;
        for (int i = 0; i < tableauxindices.length; i++) {
            string = new StringBuilder();
            jour = (Calendar) this.restaurant.getDates()[i].clone();
            string.append(jour.get(Calendar.DATE)).append("/").append(Calendar.MONTH);
            joursLibres[i] = string.toString();
        }
        return joursLibres;
    }

    public int[] getIndiceHorairesLibres(int[] dataClient) {
        return this.restaurant.getHorairesLibres(dataClient);
    }

    public String[] getHorairesLibres(int[] dataClient) {
        StringBuilder horaire = new StringBuilder();
        Calendar[] horaires = this.restaurant.horairesLibres(dataClient);
        String[] horairesLibres = new String[horaires.length];
        for (int i = 0; i < horaires.length; i++) {
            horaire = new StringBuilder();
            horaire.append(" ").append(horaires[i].get(Calendar.HOUR_OF_DAY));
            horaire.append(":").append(horaires[i].get(Calendar.MINUTE));
            horairesLibres[i] = horaire.toString();
        }
        return horairesLibres;
    }

    public String makeReservation(int[] dataClient, String userInput) {
        StringBuilder code = new StringBuilder();
        code.append(userInput);
        code.append(this.random.nextInt(500));

        this.restaurant.reserverRestaurant(dataClient[1], code.toString(), dataClient[2], dataClient[0], dataClient[3]);
        System.out.println(this.restaurant.getReservations[0].getCodeReservation());
        return code.toString();
    }


}

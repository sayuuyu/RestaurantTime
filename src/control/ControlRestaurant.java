package control;

import restaurant.Restaurant;

import java.util.Calendar;

public class ControlRestaurant {
    private final Restaurant restaurant;

    public ControlRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return this.restaurant.getMenu().getDescription();
    }

    public String getNom() {
        return this.restaurant.getNom();
    }

    public String getLocalisation() {
        return this.restaurant.getMenu().getLocalisation();
    }

    public String getHorairesOuverture() {
        StringBuilder horaires = new StringBuilder();
        int ouverture;
        Calendar debut, fin;
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    horaires.append("Lundi");
                case 1:
                    horaires.append("Mardi");
                case 2:
                    horaires.append("Mercredi");
                case 3:
                    horaires.append("Jeudi");
                case 4:
                    horaires.append("Vendredi");
                case 5:
                    horaires.append("Samedi");
                case 6:
                    horaires.append("Dimanche");
            }
            horaires.append("\n");
            Calendar[] horairesOuvertures;
            horairesOuvertures = restaurant.getMenu().getHoraireOuverture()[i];
            if (horairesOuvertures != null) {
                for (int j = 0; j < horairesOuvertures.length && horairesOuvertures[j] != null; j += 2) {
                    ouverture = 0;
                    debut = horairesOuvertures[j];
                    fin = horairesOuvertures[j + 1];
                    horaires.append(" ").append(debut.get(Calendar.HOUR_OF_DAY));
                    horaires.append(":").append(debut.get(Calendar.MINUTE)).append(" - ");
                    horaires.append(fin.get(Calendar.HOUR_OF_DAY));
                    horaires.append(":").append(fin.get(Calendar.MINUTE)).append(" \n ");
                }
            }
        }
        return horaires.toString();
    }

    public String getCarte() {
        return this.restaurant.getMenu().getCarte().afficherCarte();
    }
}

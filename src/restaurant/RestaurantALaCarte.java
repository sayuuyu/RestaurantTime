package restaurant;

import outils.Carte;
import outils.InformationsReservations;
import outils.Table;

import java.util.Calendar;

public class RestaurantALaCarte extends Restaurant {
    private final Table[][] tables;

    public RestaurantALaCarte(String nomRestaurant, Carte carte, String localisation, String description, Calendar[][] horaireOuverture, int[] nbPersonnes, int intervalleReservationEnMin, int nbJoursEnAvance) {
        super(nomRestaurant, intervalleReservationEnMin, carte, description, localisation, horaireOuverture);
        int taille = nbPersonnes.length;
        this.tables = new Table[nbJoursEnAvance][taille];
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, 1);
        for (int i = 0; i < nbJoursEnAvance; i++) {
            for (int j = 0; j < taille; j++)
                this.tables[i][j] = new Table(nbPersonnes[j], this.agendaDeLaSemaine[today.get(Calendar.DAY_OF_WEEK) - 1]);
            today.add(Calendar.DATE, 1);
        }
        initialisationDates(nbJoursEnAvance
        );

    }


    @Override
    public int[] jourLibres(int nbPersonnes) {
        int taille = 0;
        for (int iJour = 0; iJour < this.dates.length; iJour++) {
            for (int iTab = 0; iTab < this.tables[0].length; iTab++) {
                if (this.tables[iJour][iTab].estLibre(nbPersonnes)) {
                    taille++;
                    break;
                }
            }
        }
        if (taille == 0) return null;
        int[] joursLibres = new int[taille];
        int iCalendrier = 0;
        for (int iJour = this.indexDernierFoisActualiser; iCalendrier < taille; iJour = (iJour + 1) % this.dates.length) {
            for (int iTab = 0; iTab < this.tables[0].length && iCalendrier < taille; iTab++)
                if (this.tables[iJour][iTab].estLibre(nbPersonnes)) {
                    joursLibres[iCalendrier] = iJour;
                    iCalendrier++;
                }
        }
        return joursLibres;
    }

    //info[0] l'index de la date
    //info1 l'index de l'horaire
    //info[2] l'index de la table
    @Override
    public Calendar[] horairesLibres(int[] informations) {
        return this.tables[informations[0]][informations[2]].getHorairesLibres(informations[1]);
    }

    public int[] tablesLibres(int nbPersonnes, int iDate) {
        int taille = 0;
        for (int i = 0; i < this.tables[0].length; i++)
            if (tables[iDate][i].estLibre(nbPersonnes))
                taille++;
        int[] iTablesLibres = new int[taille];
        int iTab = 0;
        for (int i = 0; iTab < taille; i++)
            if (tables[iDate][i].estLibre(nbPersonnes)) {
                iTablesLibres[iTab] = i;
                iTab++;
            }
        return iTablesLibres;
    }

    @Override
    public void actualiser() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, 1);
        while (today.after(this.dates[this.indexDernierFoisActualiser])) {
            Calendar nouveau = this.dates[(this.indexDernierFoisActualiser - 1) % this.dates.length];
            nouveau.add(Calendar.DATE, 1);
            for (int i = 0; i < this.tables[0].length; i++) {
                this.tables[this.indexDernierFoisActualiser][i].reinitiliaser(this.agendaDeLaSemaine[nouveau.get(Calendar.DAY_OF_WEEK) - 1]);
            }
            this.indexDernierFoisActualiser = (this.indexDernierFoisActualiser + 1) % this.dates.length;
        }
    }

    @Override
    public Boolean annuler(String code) {
        int i = this.aDeLaReservation(code);
        if (i == this.reservations.length) return false;
        InformationsReservations info = this.reservations[i];
        this.delReservation(i);
        return this.tables[info.getDate()][info.getiReservation()[2]].annulerReservation(info);
    }


    public int[] getHorairesLibres(int[] info) {
        return this.tables[info[0]][info[2]].getIndiceHorairesLibres(info[1]);
    }

    @Override
    public void reserverRestaurant(int nbPersonnes, String code, int iHoraire, int iDate, int iTabOuAg) {
        addReservation(this.tables[iDate][iTabOuAg].reserver(nbPersonnes, code, iHoraire, iDate, iTabOuAg));
    }
}

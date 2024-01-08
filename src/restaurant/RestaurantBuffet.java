package restaurant;

import outils.Agenda;
import outils.Carte;
import outils.InformationsReservations;

import java.util.Calendar;

public class RestaurantBuffet extends Restaurant {
    private final Agenda[] agenda;

    public RestaurantBuffet(String nomRestaurant, Carte carte, String localisation, String description, Calendar[][] horaireOuverture, int nbJoursEnAvance, int[] nbPersonnes, int intervalleReservation) {
        super(nomRestaurant, intervalleReservation, carte, description, localisation, horaireOuverture);
        this.agenda = new Agenda[nbJoursEnAvance];
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, 1);
        for (int iJ = 0; iJ < nbJoursEnAvance; iJ++) {
            this.agenda[iJ] = new Agenda(this.agendaDeLaSemaine[today.get(Calendar.DAY_OF_WEEK) - 1], nbPersonnes[iJ]);
        }
        today.add(Calendar.DATE, 1);
    }

    @Override
    public void reserverRestaurant(int nbPersonnes, String code, int iHoraire, int iDate, int iTabOuAg) {
        this.agenda[iDate].reserver(nbPersonnes, code, iHoraire, iDate, iTabOuAg);

    }

    @Override
    public int[] jourLibres(int nbPersonnes) {
        int tailleJoursLibres = 0;
        int iJ = 0;
        int[] joursLibres;
        for (int i = 0; i < agenda.length; i++)
            if (agenda[i].estLibre(nbPersonnes)) {
                tailleJoursLibres++;
            }
        joursLibres = new int[tailleJoursLibres];
        for (int i = this.indexDernierFoisActualiser; iJ != tailleJoursLibres; i = (i + 1) % this.dates.length) {
            if (agenda[i].estLibre(nbPersonnes)) {
                joursLibres[iJ] = i;
                iJ++;
            }
        }
        return joursLibres;
    }

    @Override
    public Calendar[] horairesLibres(int[] informations) {
        return this.agenda[informations[0]].getHorairesLibres(informations[1]);
    }

    @Override
    public void actualiser() {
        Calendar demain = Calendar.getInstance();
        Calendar avant;
        demain.add(Calendar.DATE, 1);
        while (this.dates[this.indexDernierFoisActualiser].before(demain)) {
            avant = (Calendar) this.dates[(this.indexDernierFoisActualiser - 1) % this.dates.length].clone();
            avant.add(Calendar.DATE, 1);
            //this.agenda[this.indexDernierFoisActualiser] = new Agenda(avant, this.agenda[this.indexDernierFoisActualiser].getNbPersonnesMax);
            this.dates[this.indexDernierFoisActualiser] = avant;
            for (int i = 0; i < this.reservations.length; i++) {
                if (this.reservations[i].getDate() == this.indexDernierFoisActualiser)
                    this.delReservation(i);
            }
            this.indexDernierFoisActualiser = (this.indexDernierFoisActualiser + 1) % this.dates.length;
        }

    }

    @Override
    public Boolean annuler(String code) {
        int iReservationRestaurant = this.aDeLaReservation(code);
        if (iReservationRestaurant != this.reservations.length) {
            InformationsReservations info = this.reservations[iReservationRestaurant];
            return this.agenda[info.getDate()].annulerReservation(info);
        }
        return false;
    }

    public int[] getHorairesLibres(int[] info) {
        return this.agenda[info[0]].getIndiceHorairesLibres(info[1]);
    }


}

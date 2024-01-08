package restaurant;

import outils.Carte;
import outils.InformationsReservations;

import java.util.Calendar;

public abstract class Restaurant {
    public InformationsReservations[] getReservations;
    protected String nomRestaurant;
    protected MenuRestaurant menu;
    protected int indexDernierFoisActualiser;
    protected Calendar[] dates;
    protected Calendar[][] agendaDeLaSemaine;
    protected InformationsReservations[] reservations;


    public Restaurant(String nomRestaurant, int intervalleReservation, Carte carte, String localisation, String description, Calendar[][] horaireOuverture) {
        this.nomRestaurant = nomRestaurant;
        this.indexDernierFoisActualiser = 0;
        this.menu = new MenuRestaurant(carte, description, localisation, horaireOuverture);
        this.initialisationAgendaSemaine(intervalleReservation);
        this.reservations = new InformationsReservations[0];
    }

    public String getNom() {
        return nomRestaurant;
    }

    public MenuRestaurant getMenu() {
        return menu;
    }

    public Calendar[] getDates() {
        return dates;
    }

    public InformationsReservations[] getReservations() {
        return reservations;
    }

    public void initialisationDates(int nbJoursEnAvance) {
        this.dates = new Calendar[nbJoursEnAvance];
        Calendar demain = Calendar.getInstance();
        demain.add(Calendar.DATE, 1);
        for (int i = 0; i < nbJoursEnAvance; i++) {
            this.dates[i] = (Calendar) demain.clone();
            demain.add(Calendar.DATE, 1);
        }
    }

    public void initialisationAgendaSemaine(int intervalleReservation) {
        int maxHoraire = 0;
        int maxJour, ouverture;
        Calendar debut, fin;
        Calendar[][] horaireOuverture = this.menu.getHoraireOuverture();
        for (int iJour = 0; iJour < 7; iJour++) {
            maxJour = 0;
            ouverture = 0;
            while (ouverture < horaireOuverture[iJour].length && horaireOuverture[iJour][ouverture] != null) {
                debut = (Calendar) horaireOuverture[iJour][ouverture].clone();
                fin = (Calendar) horaireOuverture[iJour][ouverture + 1].clone();
                maxJour += ((fin.get(Calendar.HOUR_OF_DAY) - debut.get(Calendar.HOUR_OF_DAY)) * 60 + fin.get(Calendar.MINUTE) - debut.get(Calendar.MINUTE)) / intervalleReservation;
                ouverture += 2;
            }
            if (maxHoraire < maxJour)
                maxHoraire = maxJour;
        }
        this.agendaDeLaSemaine = new Calendar[7][maxHoraire];
        int iHoraire;
        for (int iJour = 0; iJour < 7; iJour++) {
            maxJour = 0;
            ouverture = 0;
            iHoraire = 0;
            while (ouverture < horaireOuverture[iJour].length && horaireOuverture[iJour][ouverture] != null) {
                debut = (Calendar) horaireOuverture[iJour][ouverture].clone();
                fin = (Calendar) horaireOuverture[iJour][ouverture + 1].clone();
                for (; debut.before(fin); debut.add(Calendar.MINUTE, intervalleReservation))
                    this.agendaDeLaSemaine[iJour][iHoraire] = (Calendar) debut.clone();
                ouverture += 2;
            }
        }
    }

    public int aDeLaReservation(String codeReservation) {
        int i = 0;
        for (; i < this.reservations.length; i++) {
            if (this.reservations[i].getCodeReservation().equals(codeReservation)) break;
        }
        return i;
    }

    public void delReservation(int i) {
        InformationsReservations[] info = new InformationsReservations[this.reservations.length - 1];
        if (i >= 0) System.arraycopy(this.reservations, 0, info, 0, i);
        if (this.reservations.length - 1 - i >= 0)
            System.arraycopy(this.reservations, i + 1, info, i, this.reservations.length - 1 - i);
        this.reservations = new InformationsReservations[info.length];
        this.reservations = info;
    }

    public void addReservation(InformationsReservations reservation) {
        InformationsReservations[] info = new InformationsReservations[this.reservations.length + 1];
        int j = 0;
        for (; j < this.reservations.length; j++) info[j] = this.reservations[j];
        info[j] = reservation;
        this.reservations = new InformationsReservations[info.length];
        this.reservations = info;
    }

    public String afficherDescription() {
        return this.menu.getDescription();
    }

    public abstract void reserverRestaurant(int nbPersonnes, String code, int iHoraire, int iDate, int iTabOuAg);

    public abstract int[] jourLibres(int nbPersonnes);

    public abstract Calendar[] horairesLibres(int[] informations);

    public abstract void actualiser();

    public abstract Boolean annuler(String code);

    public abstract int[] getHorairesLibres(int[] info);


    public static class MenuRestaurant {
        private Carte carte;
        private String localisation;
        private String description;
        private Calendar[][] horaireOuverture;

        public MenuRestaurant(Carte carte, String localisation, String description,
                              Calendar[][] horaireOuverture) {
            this.carte = carte;
            this.localisation = localisation;
            this.description = description;
            this.horaireOuverture = horaireOuverture;
        }


        public Carte getCarte() {
            return carte;
        }

        public void setCarte(Carte carte) {
            this.carte = carte;
        }

        public String getLocalisation() {
            return localisation;
        }

        public void setLocalisation(String localisation) {
            this.localisation = localisation;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Calendar[][] getHoraireOuverture() {
            return horaireOuverture;
        }

        public void setHoraireOuverture(Calendar[][] horaireOuverture) {
            this.horaireOuverture = horaireOuverture;
        }

        public void addElementCarte(String[] element) {
            carte.addElementDansCarte(element);
        }

        public void delElementCarte(String element) {
            int iDel = carte.getIElement(element);
            carte.delElementDansCarte(iDel);
        }

        public void changeElementCarte(String element, String[] newElement) {
            int iChange = carte.getIElement(element);
            carte.changeElementDansCarte(iChange, newElement);
        }


    }

}
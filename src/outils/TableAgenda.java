package outils;

import java.util.Calendar;

public abstract class TableAgenda {
    protected Calendar[] horaires;

    public TableAgenda(Calendar[] horaires) {
        int i = 0;
        while (i < horaires.length && horaires[i] != null) {
            i++;
        }
        Calendar[] newhoraires = new Calendar[i];
        System.arraycopy(horaires, 0, newhoraires, 0, i);
        this.horaires = newhoraires;
    }

    public void reinitiliaser(Calendar[] nouveauxHoraires) {
        int i = 0;
        while (i < nouveauxHoraires.length && nouveauxHoraires[i] != null) i++;
        Calendar[] newhoraires = new Calendar[i];
        System.arraycopy(nouveauxHoraires, 0, newhoraires, 0, i);
        this.horaires = newhoraires;
    }

    public Calendar[] getHoraires() {
        return horaires;
    }

    public abstract Calendar[] getHorairesLibres(int nbPersonnes);

    public abstract Boolean estLibre(int nbPersonnes);

    public abstract int[] getIndiceHorairesLibres(int nbPersonnes);

    public abstract InformationsReservations reserver(int nbPersonnes, String code, int iHoraire, int iDate, int iTabOuAg);

    public abstract Boolean annulerReservation(InformationsReservations info);

}

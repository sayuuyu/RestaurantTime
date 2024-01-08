package outils;

import java.util.Arrays;
import java.util.Calendar;

public class Agenda extends TableAgenda {

    private final int nbMaxPersonnes;
    private final int[] nbPersonnesReservees;
    private InformationsReservations[][] informationsReservations;


    public Agenda(Calendar[] horaires, int nbMaxPersonnes) {
        super(horaires);
        this.nbMaxPersonnes = nbMaxPersonnes;
        this.nbPersonnesReservees = new int[this.horaires.length];
        Arrays.fill(this.nbPersonnesReservees, 0);
        this.informationsReservations = new InformationsReservations[this.horaires.length][nbMaxPersonnes];
    }

    public InformationsReservations[][] getInformationsReservations() {
        return informationsReservations;
    }

    public void reinitiliaserAgenda(Calendar[] newHours) {
        this.reinitiliaser(newHours);
        this.informationsReservations = new InformationsReservations[this.horaires.length][nbMaxPersonnes];
    }

    public Calendar[] getHorairesLibres(int nbPersonnes) {
        int tailleHorairesLibres = 0, i = 0;
        for (; i < horaires.length; i++) {
            if (this.nbPersonnesReservees[i] + nbPersonnes <= this.nbMaxPersonnes)
                tailleHorairesLibres++;
        }
        if (tailleHorairesLibres == 0) return null;
        Calendar[] horairesLibres = new Calendar[tailleHorairesLibres];
        int iHoraire;
        i = 0;
        iHoraire = 0;
        while (iHoraire != tailleHorairesLibres) {
            if (this.nbPersonnesReservees[i] + nbPersonnes <= this.nbMaxPersonnes) {
                horairesLibres[iHoraire] = horaires[i];
                iHoraire++;
            }
            i++;
        }
        return horairesLibres;
    }

    public Boolean estLibre(int nbPersonnes) {
        for (int i = 0; i < horaires.length; i++)
            if (this.nbPersonnesReservees[i] + nbPersonnes <= this.nbMaxPersonnes)
                return true;
        return false;
    }

    public Boolean annulerReservation(InformationsReservations info) {
        if (informationsReservations[info.getHoraire()][info.getiReservation()[2]] == null) return false;
        if (this.informationsReservations[info.getHoraire()][info.getiReservation()[2]].getCodeReservation().equals(info.getCodeReservation())) {
            int nbPersonnes = info.getNbPersonnes();
            for (int iHoraire = info.getiReservation()[0]; iHoraire < info.getiReservation()[1]; iHoraire++) {
                this.nbPersonnesReservees[iHoraire] -= nbPersonnes;
            }
            informationsReservations[info.getHoraire()][info.getiReservation()[2]] = null;
            return true;
        }
        return false;
    }

    @Override
    public int[] getIndiceHorairesLibres(int info) {
        int tailleHorairesLibres = 0;
        int nbPersonnes = info;
        int i = 0;
        for (; i < this.horaires.length; i++) {
            if (this.nbPersonnesReservees[i] + nbPersonnes <= this.nbMaxPersonnes)
                tailleHorairesLibres++;
        }
        if (tailleHorairesLibres == 0) return null;
        int[] horairesLibres = new int[tailleHorairesLibres];
        int iHoraire;
        i = 0;
        iHoraire = 0;
        while (iHoraire != tailleHorairesLibres) {
            if (this.nbPersonnesReservees[i] + nbPersonnes <= this.nbMaxPersonnes) {
                horairesLibres[iHoraire] = i;
                iHoraire++;
            }
            i++;
        }
        return horairesLibres;
    }

    @Override
    public InformationsReservations reserver(int nbPersonnes, String code, int iHoraire, int iDate, int iTabOuAg) {
        int[] iDetH = {iDate, iHoraire};
        int[] iReservation = new int[4];
        Calendar debut, fin;
        iReservation[3] = iTabOuAg;
        debut = (Calendar) horaires[iHoraire].clone();
        fin = (Calendar) horaires[iHoraire].clone();
        debut.add(Calendar.HOUR_OF_DAY, -1);
        fin.add(Calendar.HOUR_OF_DAY, 1);
        int start = iHoraire;
        while (start > 0 && debut.before(horaires[start]))
            start--;
        int end;
        for (end = start; end < horaires.length && fin.after(horaires[end]); end++) {
            this.nbPersonnesReservees[end] += nbPersonnes;
        }
        iReservation[1] = end;
        iReservation[2] = 0;
        while (this.informationsReservations[iHoraire][iReservation[1]] != null)
            iReservation[2]++;
        this.informationsReservations[iDetH[1]][iReservation[2]] = new InformationsReservations(code, iDetH, nbPersonnes, iReservation);
        return this.informationsReservations[iDetH[1]][iReservation[2]];
    }
}

package outils;

import java.util.Calendar;

public class Table extends TableAgenda {
    private final int nbPersonnes;
    private int[] occupe;//0:libre,1:occupe,2:doublement occupe
    private InformationsReservations[] informationsReservation;

    public Table(int nbPersonnes, Calendar[] horaires) {
        super(horaires);
        int taille = this.horaires.length;
        this.nbPersonnes = nbPersonnes;
        this.informationsReservation = new InformationsReservations[taille];
        this.occupe = new int[taille];
        for (int i = 0; i < taille; i++) {
            occupe[i] = 0;
        }
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    @Override
    public Calendar[] getHorairesLibres(int nbPersonnes) {
        int tailleTab = 0;
        if (this.nbPersonnes == nbPersonnes) {
            for (int i = 0; i < this.occupe.length; i++)
                if (occupe[i] == 0)
                    tailleTab++;
            if (tailleTab == 0)
                return null;
            Calendar[] horairesLibres = new Calendar[tailleTab];
            int iHoraire = 0;
            for (int i = 0; i < this.occupe.length; i++) {
                if (occupe[i] == 0) {
                    horairesLibres[iHoraire] = this.horaires[i];
                    iHoraire++;
                }
                if (iHoraire == tailleTab)
                    break;
            }
            return horairesLibres;
        }
        return null;
    }

    public void reinitiliaserTab(Calendar[] nouveauxHoraires) {
        this.reinitiliaser(nouveauxHoraires);
        this.informationsReservation = new InformationsReservations[this.horaires.length];
        this.occupe = new int[this.horaires.length];
        for (int i = 0; i < this.occupe.length; i++)
            occupe[i] = 0;
    }


    @Override
    public Boolean annulerReservation(InformationsReservations info) {
        if (this.informationsReservation[info.getHoraire()] == null) {
            return false;
        }
        if (this.informationsReservation[info.getHoraire()].getCodeReservation().equals(info.getCodeReservation())) {
            for (int i = info.getiReservation()[0]; i < info.getiReservation()[1]; i++) {
                occupe[i]--;
            }
            this.informationsReservation[info.getHoraire()] = null;
            return true;
        }
        return false;
    }

    @Override
    public int[] getIndiceHorairesLibres(int info) {
        int tailleTab = 0;
        if (this.nbPersonnes == info) {
            for (int i = 0; i < this.occupe.length; i++)
                if (occupe[i] == 0)
                    tailleTab++;
            int[] indiceHorairesLibres = new int[tailleTab];
            if (tailleTab == 0)
                return null;
            int indice = 0;
            for (int i = 0; i < this.occupe.length && indice < tailleTab; i++)
                if (occupe[i] == 0) {
                    indiceHorairesLibres[indice] = i;
                    indice++;
                }
            return indiceHorairesLibres;
        }
        return null;
    }

    @Override
    public InformationsReservations reserver(int nbPersonnes, String code, int iHoraire, int iDate, int iTabOuAg) {
        int[] iDetH = {iDate, iHoraire};
        int[] iReservation = new int[3];
        Calendar debut, fin;
        int iDebut, iFin;
        debut = (Calendar) horaires[iHoraire].clone();
        fin = (Calendar) horaires[iHoraire].clone();
        debut.add(Calendar.HOUR_OF_DAY, -1);
        fin.add(Calendar.HOUR_OF_DAY, 1);
        iDebut = iHoraire;
        while (iDebut > 0 && horaires[iDebut].after(debut)) iDebut--;
        iReservation[0] = iDebut;
        iReservation[2] = iTabOuAg;
        for (iFin = iDebut; iFin < horaires.length && fin.after(horaires[iFin]); iFin++) {
            this.occupe[iFin]++;
        }
        iReservation[1] = iFin;
        this.informationsReservation[iHoraire] = new InformationsReservations(code, iDetH, nbPersonnes, iReservation);
        System.out.println(this.informationsReservation[iHoraire].getCodeReservation());
        return this.informationsReservation[iHoraire];
    }

    @Override
    public Boolean estLibre(int nbPersonnes) {
        if (this.nbPersonnes != nbPersonnes)
            return false;
        for (int i = 0; i < occupe.length; i++) {
            if (occupe[i] == 0) return true;
        }
        return false;
    }


}


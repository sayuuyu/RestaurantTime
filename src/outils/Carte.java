package outils;

public class Carte {
    private int nbElementsDansCarte;
    private String[][] laCarte;

    public Carte(String[][] laCarte, int nbElementsDansCarte) {
        this.nbElementsDansCarte = nbElementsDansCarte;
        this.laCarte = new String[nbElementsDansCarte][3];
        this.laCarte = laCarte;
    }

    public String afficherCarte() {
        StringBuilder affichage = new StringBuilder();
        String type;
        for (int i = 0; i < this.nbElementsDansCarte; ) {
            type = this.laCarte[i][0];
            affichage.append(type).append("\n");
            while (i < this.nbElementsDansCarte && this.laCarte[i][0].equals(type)) {
                affichage.append(" -").append(this.laCarte[i][1]).append("  ");
                affichage.append(this.laCarte[i][2]).append("\n\n");
                i++;
            }
        }
        return affichage.toString();
    }

    public Boolean addElementDansCarte(String[] elementAdd) {
        String[][] newCarte = new String[nbElementsDansCarte + 1][3];
        int i = 0;

        for (; i < nbElementsDansCarte; i++) {
            if (laCarte[i][1].equals(elementAdd[1]))
                return false;
            newCarte[i] = laCarte[i];
        }
        newCarte[nbElementsDansCarte] = elementAdd;
        laCarte = newCarte;
        nbElementsDansCarte++;
        return true;
    }

    public int getIElement(String nom) {
        int i = 0;
        for (; i < this.nbElementsDansCarte; i++)
            if (this.laCarte[i][1].equals(nom)) {
                break;
            }
        return i;
    }

    public void delElementDansCarte(int iDel) throws ArrayIndexOutOfBoundsException {
        if (iDel == this.nbElementsDansCarte)
            throw new ArrayIndexOutOfBoundsException("le nom de l'element dans la carte n'existe pas,impossible de le deleter");
        else {
            nbElementsDansCarte--;
            for (int i = iDel; i < this.nbElementsDansCarte; i++)
                laCarte[i] = laCarte[i + 1];

        }
    }

    public void changeElementDansCarte(int iChange, String[] elementChange) throws ArrayIndexOutOfBoundsException {
        if (iChange == this.nbElementsDansCarte)
            throw new ArrayIndexOutOfBoundsException("le nom de l'element dans la carte n'existe pas,impossible de le changer");
        else {
            laCarte[iChange] = elementChange;
        }
    }

}
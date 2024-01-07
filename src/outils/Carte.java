package outils;

public class Carte{
	private int nbElementsDansCarte;
	private String[][] laCarte;

	public Carte(String[][] laCarte, int nbElementsDansCarte) {
		this.nbElementsDansCarte=nbElementsDansCarte;
		this.laCarte = new String[nbElementsDansCarte][3];
		this.laCarte = laCarte;
	}
	
	public void addElementDansCarte(String[] elementAdd) {
		String[][] newCarte = new String[nbElementsDansCarte+1][3];
		int i = 0;
		//on parcourt l'ancien tableau jusqu'a trouver le type de ce qu'on cherche
		for(;i<nbElementsDansCarte && elementAdd[0]!=laCarte[i][0];i++){
			newCarte[i]=laCarte[i];
		}
		//on ajoute le nouveau element dans la carte
		for(int j = 0;j<3;j++) {
			newCarte[i][j]=elementAdd[j];
		}
		//et on continue l'ajout des anciens elements au nouveau tableau
		for(;i<nbElementsDansCarte;i++) {
			newCarte[i+1]=laCarte[i];
		}
		nbElementsDansCarte++;
	}
	
	public int getIElement(String nom) {
		int i = 0;
		for(;i<this.nbElementsDansCarte ;i++)
			if(this.laCarte[i][1]==nom){
				break;
			}
		return i;
	}
	
	public void delElementDansCarte(int iDel) throws ArrayIndexOutOfBoundsException{
		if(iDel==this.nbElementsDansCarte)
			throw new ArrayIndexOutOfBoundsException("le nom de l'element dans la carte n'existe pas,impossible de le deleter");
		else{
			nbElementsDansCarte--;
			for(int i = iDel;i<this.nbElementsDansCarte;i++)
				laCarte[i]=laCarte[i+1];
		}
	}
	
	public void changeElementDansCarte(int iChange, String[] elementChange) throws ArrayIndexOutOfBoundsException{
		if(iChange==this.nbElementsDansCarte)
			throw new ArrayIndexOutOfBoundsException("le nom de l'element dans la carte n'existe pas,impossible de le changer");
		else {
			laCarte[iChange]=elementChange;
		}
		
	}
		
	
	
}
package outils;

import java.util.Calendar;

public class Table extends TableAgenda{
	private int[] occupe;//0:libre,1:occupe,2:doublement occupe
	private int nbPersonnes;
	private InformationsReservations[] informationsReservation;
	private int iTab;
	
	public Table(int nbPersonnes, String emplacement,Calendar[] horaires,int iTab) {
		this.iTab=iTab;
		super(horaires,emplacement);
		int taille=this.horaires.length;
		this.nbPersonnes=nbPersonnes;
		this.informationsReservation=new InformationsReservations[taille];
		this.occupe=new int[taille];
		for(int i = 0;i<taille;i++) {
			occupe[i]=0;
		}
	}
	
	public int getNbPersonnes() {
		return nbPersonnes;
	}

	@Override
	public Calendar[] getHorairesLibres(int nbPersonnes) {
		int tailleTab=0;
		if(this.nbPersonnes==nbPersonnes) {
			for(int i = 0;i<this.occupe.length;i++)
				if(occupe[i]==0)
					tailleTab++;
			if(tailleTab==0)
				return null;
			Calendar[] horairesLibres = new Calendar[tailleTab];
			int iHoraire=0;
			for(int i = 0;i<this.occupe.length;i++){
				if(occupe[i]==0) {
					horairesLibres[iHoraire]=this.horaires[i];
					iHoraire++;
				}
				if(iHoraire==tailleTab)
					break;
			}
			return horairesLibres;
		}
		return null;
	}

	public void reinitiliaserTab(Calendar[] nouveauxHoraires) {
		this.reinitiliaser(nouveauxHoraires);
		this.informationsReservation=new InformationsReservations[this.horaires.length];
		this.occupe=new int[this.horaires.length];
		for(int i = 0;i<this.occupe.length;i++)
			occupe[i]=0;
	}


	@Override
	public Boolean annulerReservation(InformationsReservations info) {
		if(this.informationsReservation[info.getHoraire()].getCodeReservation().equals(info.getCodeReservation())) {
			for(int i = info.getiReservation()[0];i<info.getiReservation()[1];i++) {
				occupe[i]--;
			}
			this.informationsReservation[info.getHoraire()]=null;
			return true;
		}
		return false;
	}

	@Override
	public InformationsReservations reserver(int nbPersonnes, String code, int iHoraire, int iDate, int iTabOuAg) {
		int[] iDetH= {iDate,iHoraire};
		int[] iReservation=new int[3];
		Calendar debut,fin;
		debut = fin = horaires[iHoraire];
		debut.add(Calendar.HOUR_OF_DAY,-1);
		fin.add(Calendar.HOUR_OF_DAY,1);
		for(;iHoraire>-1 && debut.before(horaires[iHoraire]);iHoraire--);
		iReservation[0]=iHoraire++;
		iReservation[2]=iTabOuAg;
		for(;iHoraire<horaires.length && fin.after(horaires[iHoraire]);iHoraire++) {
			this.occupe[iHoraire]++;
		}
		iReservation[1]=iHoraire;
		this.informationsReservation[iHoraire]=new InformationsReservations(code, iDetH, nbPersonnes,iReservation);
		return this.informationsReservation[iHoraire];
	}

	@Override
	public Boolean estLibre(int nbPersonnes,String emplacement) {
		if(this.nbPersonnes!=nbPersonnes || this.emplacement.equals(emplacement))
			return false;
		for(int i = 0;i<occupe.length;i++) {
			if(occupe[i]==0)return true;
		}
		return false;
	}
	
	
}


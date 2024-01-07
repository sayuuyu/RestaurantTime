package outils;

import java.util.Calendar;

public class Agenda extends TableAgenda{
	private int nbMaxPersonnes;
	private int[] nbPersonnesReservees;
	private InformationsReservations[][] informationsReservations;
	
	public Agenda(Calendar[] horaires, String emplacement, int nbMaxPersonnes) {
		super(horaires,emplacement);
		this.nbMaxPersonnes = nbMaxPersonnes;
		this.nbPersonnesReservees=new int[this.horaires.length];
		for(int i = 0;i<this.nbPersonnesReservees.length;i++) {
			this.nbPersonnesReservees[i]=0;
		}
		this.informationsReservations =new InformationsReservations[this.horaires.length][nbMaxPersonnes];
	}
	
	public void reinitiliaserAgenda(Calendar[] newHours) {
		this.reinitiliaser(newHours);
		this.informationsReservations =new InformationsReservations[this.horaires.length][nbMaxPersonnes];
	}
	public Calendar[] getHorairesLibres(int nbPersonnes) {
		int tailleHorairesLibres=0,i=0;
		for(;i<horaires.length;i++) {
			if(this.nbPersonnesReservees[i]+nbPersonnes<=this.nbMaxPersonnes)
				tailleHorairesLibres++;
		}
		if(tailleHorairesLibres==0)return null;
		Calendar[] horairesLibres=new Calendar[tailleHorairesLibres];
		int iHoraire;
		i = iHoraire = 0;
		while(iHoraire!=tailleHorairesLibres) {
			if(this.nbPersonnesReservees[i]+nbPersonnes<=this.nbMaxPersonnes) {
				horairesLibres[iHoraire]=horaires[i];
				iHoraire++;
			}
			i++;
		}
		return horairesLibres;
	}
	
	public Boolean estLibre(int nbPersonnes,String emplacement) {
		if(this.emplacement.equals(emplacement)) {
			for(int i =0;i<horaires.length;i++)
				if(this.nbPersonnesReservees[i]+nbPersonnes<=this.nbMaxPersonnes)
					return true;
		}
		return false;
	}
	
	public Boolean annulerReservation(InformationsReservations info) {
		if(informationsReservations[info.getHoraire()][info.getiReservation()[2]].getCodeReservation().equals(info.getCodeReservation())) {
			int nbPersonnes =info.getNbPersonnes();
			for(int iHoraire=info.getiReservation()[0];iHoraire<info.getiReservation()[1];iHoraire++) {
				this.nbPersonnesReservees[iHoraire]-=nbPersonnes;
			}
			informationsReservations[info.getHoraire()][info.getiReservation()[2]]=null;
			return true;
		}
		return false;
	}

	@Override
	public InformationsReservations reserver(int nbPersonnes, String code, int iHoraire, int iDate, int iTabOuAg) {
		int[] iDetH= {iDate,iHoraire};
		int[] iReservation=new int[4];
		Calendar debut,fin;
		iReservation[3]=iTabOuAg;
		debut = fin = horaires[iHoraire];
		debut.add(Calendar.HOUR_OF_DAY,-1);
		fin.add(Calendar.HOUR_OF_DAY,1);
		for(;iHoraire>-1 && debut.before(horaires[iHoraire]);iHoraire--);
		iReservation[0]=iHoraire++;
		iReservation[2]=0;
		while(this.informationsReservations[iHoraire][iReservation[1]]!=null)
			iReservation[2]++;
		for(;iHoraire<horaires.length && fin.after(horaires[iHoraire]);iHoraire++) {
			this.nbPersonnesReservees[iHoraire]+=nbPersonnes;
		}
		iReservation[1]=iHoraire;
		this.informationsReservations[iDetH[1]][iReservation[2]]=new InformationsReservations(code, iDetH, nbPersonnes,iReservation);
		return this.informationsReservations[iDetH[1]][iReservation[2]];
	}
	
	
}

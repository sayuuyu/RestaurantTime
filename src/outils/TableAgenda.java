package outils;

import java.util.Calendar;

public abstract class TableAgenda {
	protected Calendar[] horaires;
	protected String emplacement;
	
	public TableAgenda(Calendar[] horaires,String emplacement) {
		int i =0;
		while(horaires[i]!=null)i++;
		Calendar[] newhoraires=new Calendar[i];
		for(int j=0;j<horaires.length;j++) {
			newhoraires[j]=horaires[j];
		}
		this.horaires=newhoraires;
		this.emplacement=emplacement;
	}
	
	public void reinitiliaser(Calendar[] nouveauxHoraires) {
		int i = 0;
		while(nouveauxHoraires[i]!=null)i++;
		Calendar[] newhoraires=new Calendar[i];
		for(int j=0;j<horaires.length;j++) {
			newhoraires[j]=nouveauxHoraires[j];
		}
		this.horaires=newhoraires;
	}
	
	public String getEmplacement() {
		return emplacement;
	}
	public Calendar[] getHoraires() {
		return horaires;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public abstract Boolean estLibre(int nbPersonnes,String emplacement);
	public abstract Calendar[] getHorairesLibres(int nbPersonnes);
	public abstract InformationsReservations reserver(int nbPersonnes,String code,int iHoraire,int iDate,int iTabOuAg);
	public abstract Boolean annulerReservation(InformationsReservations info);
}

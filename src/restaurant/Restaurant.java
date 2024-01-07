package restaurant;

import java.util.Calendar;

import outils.Carte;
import outils.InformationsReservations;
import outils.TableAgenda;

abstract class Restaurant{
	protected int indexDernierFoisActualiser;
	protected Calendar[] dates;
	protected Calendar[][] agendaDeLaSemaine;
	protected InformationsReservations[] reservations;
	protected MenuRestaurant menu;
	
	protected static class MenuRestaurant{
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

		public String getLocalisation() {
			return localisation;
		}

		public String getDescription() {
			return description;
		}

		public Calendar[][] getHoraireOuverture() {
			return horaireOuverture;
		}

		public void setCarte(Carte carte) {
			this.carte = carte;
		}
		
		public void addElementCarte(String[] element){
			carte.addElementDansCarte(element);
		}
		
		public void delElementCarte(String element) {
			int iDel = carte.getIElement(element);
			carte.delElementDansCarte(iDel);
		}
		
		public void changeElementCarte(String element,String[] newElement) {
			int iChange = carte.getIElement(element);
			carte.changeElementDansCarte(iChange, newElement);
		}

		public void setLocalisation(String localisation) {
			this.localisation = localisation;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setHoraireOuverture(Calendar[][] horaireOuverture) {
			this.horaireOuverture = horaireOuverture;
		}
		
	}
	
	public Restaurant(int intervalleReservation) {
		this.indexDernierFoisActualiser=0;
		this.initialisationAgendaSemaine(intervalleReservation);
	}
	
	public void initialisationAgendaSemaine(int intervalleReservation) {
		int maxHoraire=0;
		int maxJour,ouverture;
		Calendar debut,fin;
		Calendar[][] horaireOuverture=this.menu.getHoraireOuverture();
		for(int iJour=0;iJour<7;iJour++) {
			maxJour=0;
			ouverture=0;
			while(horaireOuverture[iJour][ouverture]!=null){
				debut = horaireOuverture[iJour][ouverture];
				fin = horaireOuverture[iJour][ouverture+1];
				maxJour+=((fin.get(Calendar.HOUR_OF_DAY)-debut.get(Calendar.HOUR_OF_DAY))*60+fin.get(Calendar.MINUTE)-debut.get(Calendar.MINUTE))/intervalleReservation;
				ouverture+=2;
			}
			if(maxHoraire<maxJour)
				maxHoraire=maxJour;
		}
		this.agendaDeLaSemaine=new Calendar[7][maxHoraire];
		int iHoraire=0;
		for(int iJour=0;iJour<7;iJour++) {
			maxJour=ouverture = iHoraire= 0;
			while(horaireOuverture[iJour][ouverture]!=null){
				debut = horaireOuverture[iJour][ouverture];
				fin = horaireOuverture[iJour][ouverture+1];
				for(;debut.before(fin);debut.add(Calendar.MINUTE,intervalleReservation))
					this.agendaDeLaSemaine[iJour][iHoraire]=debut;
				ouverture+=2;
			}
		}
	}
	
	public int aDeLaReservation(String codeReservation) {
		int i=0;
		for(; i<this.reservations.length; i++) {
			if(this.reservations==null)return this.reservations.length;
			if(this.reservations[i].getCodeReservation().equals(codeReservation))return i;
		}
		return i;
	}
	
	//todo
	public <T extends TableAgenda> void choisir(T[] type, int nbPersonnes,String code,int iHoraire,int iDate,int iTabOuAg) {
		type[iTabOuAg].reserver(nbPersonnes,code,iHoraire,iDate,iTabOuAg);
	}
	public abstract Calendar[] jourLibres(int nbPersonnes,String emplacement);
	public abstract Calendar[] horairesLibres(int[] informations);
	public abstract void actualiser();
	public abstract Boolean annuler(String code);
	
}
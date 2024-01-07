package restaurant;

import java.util.Calendar;

import outils.Agenda;
import outils.InformationsReservations;
import outils.TableAgenda;

public class RestaurantBuffet extends Restaurant{
	private Agenda[][] agenda;
	 
	public RestaurantBuffet(int nbJoursEnAvance,String[] emplacements,int[] nbPersonnes,int intervalleReservation) {
		super(intervalleReservation);
		this.agenda=new Agenda[nbJoursEnAvance][emplacements.length];
		Calendar today =Calendar.getInstance();
		today.add(Calendar.DATE,1);
		for(int iJ=0;iJ<nbJoursEnAvance;iJ++) { 
			for(int i = 0;i<emplacements.length;i++) {
				this.agenda[iJ][i]=new Agenda(this.agendaDeLaSemaine[today.get(Calendar.DAY_OF_WEEK)], emplacements[i], nbPersonnes[i]);
			}
			today.add(Calendar.DATE,1);
		}
	}
	
	@Override
	public Calendar[] jourLibres(int nbPersonnes, String emplacement) {
		int iEmplacement = this.indiceEmplacement(emplacement);
		int tailleJoursLibres = 0;
		int iJ = 0;
		Calendar[] joursLibres;
		for(int i=0;i<agenda.length;i++)
			if(agenda[i][iEmplacement].estLibre(nbPersonnes)) {
				tailleJoursLibres++;
			}
		joursLibres = new Calendar[tailleJoursLibres];
		for(int i=0;i<agenda.length && iJ!=tailleJoursLibres;i++) {
			if(agenda[i][iEmplacement].estLibre(nbPersonnes)) {
				joursLibres[iJ]=this.dates[i];
				iJ++;
			}
		}
		return joursLibres;
	}
	
	public int indiceEmplacement(String emplacement) {
		int iEmplacement = 0;
		for(;iEmplacement<agenda[0].length;iEmplacement++) {
			if(agenda[0][iEmplacement].getEmplacement().equals(emplacement))
				return iEmplacement;
		}
		return iEmplacement;
	}
	
	@Override
	public Calendar[] horairesLibres(int[] informations) {
		return this.agenda[informations[0]][informations[1]].getHorairesLibres(informations[3]);
	}

	@Override
	public void actualiser() {
		Calendar aujourdhui=Calendar.getInstance();
		Calendar avant;
		aujourdhui.add(Calendar.DATE,1);
		while(this.dates[this.indexDernierFoisActualiser].before(aujourdhui)) {
			avant=this.dates[(this.indexDernierFoisActualiser-1)%this.dates.length];
			avant.add(Calendar.DATE,1);
			this.dates[this.indexDernierFoisActualiser]=avant;
			this.indexDernierFoisActualiser=(this.indexDernierFoisActualiser+1)%this.dates.length;
		}
		//todo annuler les reservations pour le jour aussi
	}

	@Override
	public Boolean annuler(String code) {
		int iReservationRestaurant = this.aDeLaReservation(code);
		int iEmplacement = 0;
		if(iReservationRestaurant!=this.reservations.length){
			InformationsReservations info = this.reservations[iReservationRestaurant];
			while(!this.agenda[info.getDate()][iEmplacement].annulerReservation(info))
				iEmplacement++;
			return true;
		}
		return false;
	}

	

}

package restaurant;

import java.util.Calendar;

import outils.InformationsReservations;
import outils.Table;
import outils.TableAgenda;

public class RestaurantALaCarte extends Restaurant {
	private Table[][] tables;
	
	public RestaurantALaCarte(int[] nbPersonnes,String[] emplacement,int intervalleReservationEnMin,int nbJoursEnAvance) {
		super(intervalleReservationEnMin);
		int taille=nbPersonnes.length;
		this.tables=new Table[nbJoursEnAvance][taille];
		Calendar today =Calendar.getInstance();
		today.add(Calendar.DATE,1);
		for(int i =0;i<nbJoursEnAvance;i++,today.add(Calendar.DATE, 1)) {
			for(int j=0;j<taille;j++)
				this.tables[i][j]=new Table(nbPersonnes[j], emplacement[j], this.agendaDeLaSemaine[today.get(Calendar.DAY_OF_WEEK)],j);
		}
	}

	@Override
	
	public int[] jourLibres(int nbPersonnes, String emplacement) {
		int taille=0;
		for(int iJour=0;iJour<this.dates.length;iJour++) {
			for(int iTab = 0;iTab<this.tables[0].length;iTab++) {
				if(this.tables[iJour][iTab].estLibre(nbPersonnes, emplacement)) {
					taille++;
					break;
				}
			}
		}
		if(taille ==0)return null;
		int[] joursLibres = new int[taille];
		int iCalendrier=0;
		for(int iJour=this.indexDernierFoisActualiser;iCalendrier<taille;iJour = (iJour+1)%this.dates.length) {
			for(int iTab = 0;iTab<this.tables[0].length && iCalendrier<taille;iTab++)
				if(this.tables[iJour][iTab].estLibre(nbPersonnes, emplacement)) {
					joursLibres[iCalendrier]=iJour;
					iCalendrier++;
				}
		}
		return joursLibres;
	}

	@Override
	//info[0] l'index de la date
	//info[1] l'index de la table
	public Calendar[] horairesLibres(int[] informations) {
		return this.tables[informations[0]][informations[2]].getHorairesLibres(informations[1]);
	}
	
	public int[] tablesLibres(int[] informations,String emplacement) {
		int taille=0;
		for(int i = 0;i<this.tables[0].length;i++) 
			if(tables[informations[0]][i].estLibre(informations[1],emplacement)) 
				taille++;
		int[] iTablesLibres = new int[taille];
		int iTab = 0;
		for(int i = 0;iTab<taille;i++) 
			if(tables[informations[0]][i].estLibre(informations[1],emplacement)) { 
				iTablesLibres[iTab]=i;
				iTab++;
			}			
		return iTablesLibres;
	}
 
	@Override
	public void actualiser() {
		Calendar today=Calendar.getInstance();
		today.add(Calendar.DATE, 1);
		while(today.after(this.dates[this.indexDernierFoisActualiser])) {
			Calendar nouveau = this.dates[(this.indexDernierFoisActualiser-1)%this.dates.length];
			nouveau.add(Calendar.DATE, 1);
			for(int i = 0;i<this.tables[0].length;i++) {
				this.tables[this.indexDernierFoisActualiser][i].reinitiliaser(this.agendaDeLaSemaine[nouveau.get(Calendar.DAY_OF_WEEK)]);
			}
			this.indexDernierFoisActualiser=(this.indexDernierFoisActualiser+1)%this.dates.length;
		}
	}

	@Override
	public Boolean annuler(String code) {
		int i = this.aDeLaReservation(code);
		if(i==this.reservations.length)return false;
		return true;
		
	}

	@Override
	public <T extends TableAgenda> T affichage(int nbPersonnes, int[] informationsIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}

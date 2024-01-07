package outils;

public class InformationsReservations {
	private String codeReservation;
	private int[] iDateEtHoraire;
	private int nbPersonnes;
	private int[] iReservation;
	public InformationsReservations(String codeReservation, int[] iDateEtHoraire, int nbPersonnes, int[] iReservation) {
		this.codeReservation = codeReservation;
		this.iDateEtHoraire = iDateEtHoraire;
		this.nbPersonnes = nbPersonnes;
		this.iReservation = iReservation;
	}
	
	public int getDate() {
		return this.iDateEtHoraire[0];
	}
	
	public int getHoraire() {
		return this.iDateEtHoraire[1];
	}
	
	public String getCodeReservation() {
		return codeReservation;
	}

	public int getNbPersonnes() {
		return nbPersonnes;
	}
	public void setCodeReservation(String codeReservation) {
		this.codeReservation = codeReservation;
	}

	public void setNbPersonnes(int nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}
	public int[] getiReservation() {
		return iReservation;
	}
	public void setiReservation(int[] iReservation) {
		this.iReservation = iReservation;
	}
	
}

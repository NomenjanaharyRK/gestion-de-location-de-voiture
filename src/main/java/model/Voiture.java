package model;

import java.io.Serializable;

public class Voiture implements Serializable {
	private static final long serialVersionUID = -6823559567834135841L;
	private Long id;
	private String marque;
	private String modele;
	private String couleur;
	private int annee;
	private Double prixJournalier;
	private boolean status;
	private String illustration;
	private String description;
	private int nbPlace;
	
//	description
//	illustration
//	nombreDePlace
//	nombreDeLocation
	public Voiture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voiture(String marque, String modele, String couleur, Double prixJournalier, int annee, String illustration,String description, int nbPlace) {
		super();
		this.marque = marque;
		this.modele = modele;
		this.couleur = couleur;
		this.prixJournalier = prixJournalier;
		this.annee = annee;
		this.illustration = illustration;
		this.status = true;
		this.description = description;
		this.nbPlace = nbPlace;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public Double getPrixJournalier() {
		return prixJournalier;
	}

	public void setPrixJournalier(Double prixJournalier) {
		this.prixJournalier = prixJournalier;
	}
	
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return marque + " " + modele;
	}

	public String getIllustration() {
		return illustration;
	}

	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}
	
}

package model;

import java.io.Serializable;
import java.sql.Date;

public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Voiture voiture;
	private Client client;
	private Date dateDebut;
	private Date dateFin;
	private Double prix_total;
	private boolean status;
	
	public Location() {
		super();
	}

	public Location(Voiture voiture, Client client, Date dateDebut, Date dateFin, Double prix_total) {
		super();
		this.voiture = voiture;
		this.client = client;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prix_total = prix_total;
		this.status = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Double getPrix_total() {
		return prix_total;
	}

	public void setPrix_total(Double prix_total) {
		this.prix_total = prix_total;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}

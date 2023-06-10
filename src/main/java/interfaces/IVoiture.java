package interfaces;

import java.util.List;

import model.Voiture;

public interface IVoiture {
	
	public Voiture findOne(Long id);
	public List<Voiture> findAll();
	public List<Voiture> findByMotCle(String motCle);
	public List<Voiture> findAllDisponible();
	public Voiture create(Voiture voiture);
	public Voiture update(Voiture voiture);
	public void delete(Long id);
	public void rent(Long id);
	public void setDisponible(Long id);
}
